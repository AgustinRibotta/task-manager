import React, { useState, useEffect } from 'react';
import { X, Save, Users, FolderOpen } from 'lucide-react';
import { apiService } from '../services/api';
import { useAuth } from '../contexts/AuthContext';

interface Task {
  id?: number;
  name: string;
  description: string;
  status: string;
  assignedUsers: any[];
  project?: any;
}

interface TaskFormProps {
  projectId?: number;  
  task?: Task | null;
  onSuccess: () => void;
  onCancel: () => void;
}

export const TaskForm: React.FC<TaskFormProps> = ({ projectId, task, onSuccess, onCancel }) => {
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    status: 'TODO',
    selectedUsers: [] as number[],
    projectDto: projectId ? projectId.toString() : '' 
  });
  const [users, setUsers] = useState<any[]>([]);
  const [projects, setProjects] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');
  const { user: currentUser } = useAuth();

  useEffect(() => {
    const init = async () => {
      await loadUsers();
      await loadProjects();

      if (task) {
        setFormData({
          name: task.name || '',
          description: task.description || '',
          status: task.status || 'TODO',
          selectedUsers: task.assignedUsers?.map(u => u.id) || [],
          projectDto: task.project?.id?.toString() || projectId?.toString() || ''
        });
      } else {
        setFormData(prev => ({
          ...prev,
          projectDto: projectId?.toString() || ''
        }));
      }
    };

    init();
  }, [task, projectId]);

  const loadUsers = async () => {
    try {
      const data = await apiService.getUsers();
      setUsers(data);
    } catch (err) {
      console.error('Failed to load users:', err);
    }
  };

  const loadProjects = async () => {
    try {
      const allProjects = await apiService.getAllProjects();
      if (currentUser?.roleDtos?.some(r => r.name === 'ROLE_ADMIN')) {
        setProjects(allProjects);
      } else {
        const filtered = allProjects.filter((p: any) =>
          p.usersDtos?.some((u: any) => u.id === currentUser?.id)
        );
        setProjects(filtered);
      }
    } catch (err) {
      console.error('Failed to load projects:', err);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    setError('');

    try {
      const taskData = {
        name: formData.name,
        description: formData.description,
        status: formData.status,
        projectDto: { id: Number(formData.projectDto || projectId) }, // fallback to passed projectId
        userSummaryDto: formData.selectedUsers.map(id => ({ id }))
      };

      if (task?.id) {
        await apiService.updateTask(task.id, taskData);
      } else {
        await apiService.createTask(taskData);
      }
      onSuccess();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to save task');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 backdrop-blur-sm flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-2xl shadow-2xl w-full max-w-2xl max-h-[90vh] overflow-y-auto">
        {/* Header */}
        <div className="flex items-center justify-between p-6 border-b border-slate-200">
          <h2 className="text-2xl font-bold text-slate-800">
            {task ? 'Edit Task' : 'New Task'}
          </h2>
          <button
            onClick={onCancel}
            className="p-2 text-slate-400 hover:text-slate-600 hover:bg-slate-100 rounded-lg transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        {/* Form */}
        <form onSubmit={handleSubmit} className="p-6 space-y-6">
          {error && (
            <div className="bg-red-50 border border-red-200 rounded-lg p-4 text-red-700">
              {error}
            </div>
          )}

          {/* Project Selection */}
          <div>
            <label htmlFor="projectId" className="block text-sm font-medium text-slate-700 mb-2">
              <FolderOpen className="w-4 h-4 inline mr-2" />
              Select Project
            </label>
            <select
              id="projectId"
              value={formData.projectDto}
              onChange={(e) => setFormData(prev => ({ ...prev, projectDto: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              required
            >
              <option value="">-- Select a Project --</option>
              {projects.map((project) => (
                <option key={project.id} value={project.id}>
                  {project.name}
                </option>
              ))}
            </select>
          </div>

          {/* Task Name */}
          <div>
            <label htmlFor="name" className="block text-sm font-medium text-slate-700 mb-2">
              Task Name
            </label>
            <input
              id="name"
              type="text"
              value={formData.name}
              onChange={(e) => setFormData(prev => ({ ...prev, name: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              placeholder="Enter task name"
              required
            />
          </div>

          {/* Description */}
          <div>
            <label htmlFor="description" className="block text-sm font-medium text-slate-700 mb-2">
              Description
            </label>
            <textarea
              id="description"
              value={formData.description}
              onChange={(e) => setFormData(prev => ({ ...prev, description: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200 resize-none"
              rows={4}
              placeholder="Enter task description"
              required
            />
          </div>

          {/* Status */}
          <div>
            <label htmlFor="status" className="block text-sm font-medium text-slate-700 mb-2">
              Status
            </label>
            <select
              id="status"
              value={formData.status}
              onChange={(e) => setFormData(prev => ({ ...prev, status: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
            >
              <option value="TODO">To Do</option>
              <option value="IN_PROGRESS">In Progress</option>
              <option value="DONE">Done</option>
            </select>
          </div>

          {/* Assign Users */}
          <div>
            <label className="block text-sm font-medium text-slate-700 mb-3">
              <Users className="w-4 h-4 inline mr-2" />
              Assign Users
            </label>
            <div className="max-h-48 overflow-y-auto border border-slate-300 rounded-lg p-3 space-y-2">
              {users.map((user) => (
                <label key={user.id} className="flex items-center space-x-3 p-2 hover:bg-slate-50 rounded-lg cursor-pointer">
                  <input
                    type="checkbox"
                    checked={formData.selectedUsers.includes(user.id)}
                    onChange={(e) => {
                      if (e.target.checked) {
                        setFormData(prev => ({
                          ...prev,
                          selectedUsers: [...prev.selectedUsers, user.id]
                        }));
                      } else {
                        setFormData(prev => ({
                          ...prev,
                          selectedUsers: prev.selectedUsers.filter(id => id !== user.id)
                        }));
                      }
                    }}
                    className="rounded border-slate-300 text-blue-600 focus:ring-blue-500"
                  />
                  <div className="flex-1">
                    <span className="text-sm font-medium text-slate-800">{user.username}</span>
                    <span className="text-xs text-slate-500 ml-2">{user.email}</span>
                  </div>
                </label>
              ))}
            </div>
          </div>

          {/* Actions */}
          <div className="flex justify-end space-x-3 pt-4 border-t border-slate-200">
            <button
              type="button"
              onClick={onCancel}
              className="px-4 py-2 text-slate-600 hover:text-slate-800 hover:bg-slate-100 rounded-lg transition-colors"
            >
              Cancel
            </button>
            <button
              type="submit"
              disabled={isLoading}
              className="flex items-center space-x-2 bg-gradient-to-r from-blue-500 to-emerald-500 text-white px-6 py-2 rounded-lg hover:from-blue-600 hover:to-emerald-600 transition-all duration-200 disabled:opacity-50"
            >
              <Save className="w-4 h-4" />
              <span>{isLoading ? 'Saving...' : 'Save Task'}</span>
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
