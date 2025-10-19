import React, { useState, useEffect } from 'react';
import { Plus, Edit3, Trash2, Clock, CheckCircle, AlertCircle } from 'lucide-react';
import { useAuth } from '../contexts/AuthContext';
import { apiService } from '../services/api';
import { TaskForm } from './TaskForm';

interface Task {
  id: number;
  name: string;
  description: string;
  status: string;
  assignedUsers: any[];
  projectDto ?: any;
}

export const TasksPage: React.FC = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [editingTask, setEditingTask] = useState<Task | null>(null);
  const [error, setError] = useState('');
  const [filterStatus, setFilterStatus] = useState<string>('ALL');
  const { user, isAdmin, isProjectManager } = useAuth();

  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = async () => {
    if (!user) return;

    try {
      setIsLoading(true);
      const data = await apiService.getTaskByRole(user);
      setTasks(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to load tasks');
    } finally {
      setIsLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    if (!confirm('Are you sure you want to delete this task?')) return;
    
    try {
      await apiService.deleteTask(id);
      await loadTasks();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to delete task');
    }
  };

  const handleFormSuccess = () => {
    setShowForm(false);
    setEditingTask(null);
    loadTasks();
  };

  const getStatusIcon = (status: string) => {
    switch (status) {
      case 'TODO':
        return <AlertCircle className="w-4 h-4 text-amber-500" />;
      case 'IN_PROGRESS':
        return <Clock className="w-4 h-4 text-blue-500" />;
      case 'DONE':
        return <CheckCircle className="w-4 h-4 text-emerald-500" />;
      default:
        return <AlertCircle className="w-4 h-4 text-slate-400" />;
    }
  };

  const getStatusColor = (status: string) => {
    switch (status) {
      case 'TODO':
        return 'bg-amber-100 text-amber-800 border-amber-200';
      case 'IN_PROGRESS':
        return 'bg-blue-100 text-blue-800 border-blue-200';
      case 'DONE':
        return 'bg-emerald-100 text-emerald-800 border-emerald-200';
      default:
        return 'bg-slate-100 text-slate-800 border-slate-200';
    }
  };

  const filteredTasks = filterStatus === 'ALL' 
    ? tasks 
    : tasks.filter(task => task.status === filterStatus);

  const canManageTasks = isAdmin || isProjectManager;

  if (isLoading) {
    return (
      <div className="flex items-center justify-center h-64">
        <div className="w-8 h-8 border-2 border-blue-500 border-t-transparent rounded-full animate-spin" />
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold text-slate-800">Tasks</h1>
          <p className="text-slate-600 mt-1">Track and manage your tasks</p>
        </div>
        
        <div className="flex items-center space-x-3">
          {/* Status Filter */}
          <select
            value={filterStatus}
            onChange={(e) => setFilterStatus(e.target.value)}
            className="px-3 py-2 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
          >
            <option value="ALL">All Tasks</option>
            <option value="TODO">To Do</option>
            <option value="IN_PROGRESS">In Progress</option>
            <option value="DONE">Done</option>
          </select>

          {canManageTasks && (
            <button
              onClick={() => setShowForm(true)}
              className="flex items-center space-x-2 bg-gradient-to-r from-blue-500 to-emerald-500 text-white px-4 py-2 rounded-lg hover:from-blue-600 hover:to-emerald-600 transition-all duration-200 hover:scale-105"
            >
              <Plus className="w-4 h-4" />
              <span>New Task</span>
            </button>
          )}
        </div>
      </div>

      {/* Error Message */}
      {error && (
        <div className="bg-red-50 border border-red-200 rounded-lg p-4 text-red-700">
          {error}
        </div>
      )}

      {/* Tasks Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-6">
        {filteredTasks.map((task) => (
          <div
            key={task.id}
            className="bg-white/60 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:bg-white/80 transition-all duration-300 hover:scale-105 hover:shadow-lg group"
          >
            <div className="flex justify-between items-start mb-4">
              <div className="flex-1">
                <h3 className="text-lg font-semibold text-slate-800 mb-2 group-hover:text-blue-600 transition-colors">
                  {task.name}
                </h3>
                <p className="text-slate-600 text-sm line-clamp-2 mb-3">{task.description}</p>
                
                {/* Status Badge */}
                <div className={`inline-flex items-center space-x-2 px-3 py-1 rounded-full text-xs font-medium border ${getStatusColor(task.status)}`}>
                  {getStatusIcon(task.status)}
                  <span>{task.status.replace('_', ' ')}</span>
                </div>
              </div>
              
              {canManageTasks && (
                <div className="flex space-x-1 opacity-0 group-hover:opacity-100 transition-opacity duration-200">
                  <button
                    onClick={() => {
                      setEditingTask(task);
                      setShowForm(true);
                    }}
                    className="p-2 text-slate-400 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                  >
                    <Edit3 className="w-4 h-4" />
                  </button>
                  <button
                    onClick={() => handleDelete(task.id)}
                    className="p-2 text-slate-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                  >
                    <Trash2 className="w-4 h-4" />
                  </button>
                </div>
              )}
            </div>

            {/* Project and Assignees */}
            <div className="space-y-2">
              {task.projectDto  && (
                <div className="text-xs text-slate-500">
                  Project: <span className="font-medium">{task.projectDto.name}</span>
                </div>
              )}
              {task.assignedUsers?.length > 0 && (
                <div className="text-xs text-slate-500">
                  Assigned to: {task.assignedUsers.map(u => u.username).join(', ')}
                </div>
              )}
            </div>
          </div>
        ))}
      </div>

      {/* Empty State */}
      {filteredTasks.length === 0 && (
        <div className="text-center py-12">
          <div className="w-16 h-16 bg-slate-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <CheckCircle className="w-8 h-8 text-slate-400" />
          </div>
          <h3 className="text-lg font-medium text-slate-800 mb-2">
            {filterStatus === 'ALL' ? 'No tasks yet' : `No ${filterStatus.replace('_', ' ').toLowerCase()} tasks`}
          </h3>
          <p className="text-slate-600 mb-4">
            {canManageTasks ? 'Create your first task to get started' : 'No tasks assigned to you yet'}
          </p>
          {canManageTasks && (
            <button
              onClick={() => setShowForm(true)}
              className="bg-gradient-to-r from-blue-500 to-emerald-500 text-white px-6 py-2 rounded-lg hover:from-blue-600 hover:to-emerald-600 transition-all duration-200"
            >
              Create Task
            </button>
          )}
        </div>
      )}

      {/* Task Form Modal */}
      {showForm && (
        <TaskForm
          task={editingTask}
          onSuccess={handleFormSuccess}
          onCancel={() => {
            setShowForm(false);
            setEditingTask(null);
          }}
        />
      )}
    </div>
  );
};