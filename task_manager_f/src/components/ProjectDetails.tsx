import React, { useState, useEffect } from 'react';
import { apiService } from '../services/api';
import { Project } from './ProjectsPage'; 
import { useAuth } from '../contexts/AuthContext';
import { TaskForm } from './TaskForm';
import { Task } from '../types';
import { TaskDetails } from './TaskDetails';

interface ProjectDetailsProps {
  projectId: number;
  onClose: () => void;
}

export const ProjectDetails: React.FC<ProjectDetailsProps> = ({ projectId, onClose }) => {
  const [project, setProject] = useState<Project | null>(null);
  const [loading, setLoading] = useState(false);

  // Task modals
  const [selectedTaskId, setSelectedTaskId] = useState<number | null>(null);
  const [editingTask, setEditingTask] = useState<Task | null>(null);
  const [showTaskForm, setShowTaskForm] = useState(false);

  const { user } = useAuth();

  // Fetch project
  const fetchProject = async () => {
    if (!user) return;
    setLoading(true);
    try {
      const projects: Project[] = await apiService.getProjectsByRole(user);
      const selectedProject = projects.find(p => p.id === projectId) || null;
      setProject(selectedProject);
    } catch (err) {
      console.error('Failed to load project:', err);
      setProject(null);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProject();
  }, [user, projectId]);

  if (loading || !project)
    return <div className="flex justify-center items-center h-32">Loading...</div>;

  return (
    <div className="fixed inset-0 bg-black/50 flex justify-center items-center p-4 z-50">
      <div className="bg-white p-6 rounded-xl w-full max-w-2xl max-h-[90vh] overflow-y-auto">
        {/* Project Info */}
        <h2 className="text-2xl font-bold mb-4">{project.name}</h2>
        <p className="text-slate-700">{project.description}</p>

        {/* Members */}
        <h3 className="mt-6 font-semibold text-slate-800">Members</h3>
        <ul className="list-disc list-inside mt-2 text-slate-600">
          {project.usersDtos && project.usersDtos.length > 0 ? (
            project.usersDtos.map(u => <li key={u.id}>{u.username}</li>)
          ) : (
            <li>No members assigned</li>
          )}
        </ul>

        {/* Tasks */}
        <h3 className="mt-6 font-semibold text-slate-800">Tasks</h3>
        {project.tasksDtos && project.tasksDtos.length > 0 ? (
          <ul className="mt-2 space-y-2">
            {project.tasksDtos.map(t => (
              <li
                key={t.id}
                className="flex justify-between items-center bg-slate-50 px-3 py-2 rounded-lg border border-slate-200"
              >
                <span className="text-slate-700">{t.name}</span>
                <div className="flex space-x-2">
                  <button
                    onClick={() => setSelectedTaskId(t.id)}
                    className="px-2 py-1 text-xs bg-blue-500 text-white rounded hover:bg-blue-600 transition"
                  >
                    View
                  </button>
                  <button
                    onClick={() => {
                      setEditingTask(t);
                      setShowTaskForm(true);
                    }}
                    className="px-2 py-1 text-xs bg-emerald-500 text-white rounded hover:bg-emerald-600 transition"
                  >
                    Edit
                  </button>
                </div>
              </li>
            ))}
          </ul>
        ) : (
          <p className="mt-2 text-slate-600">No tasks yet</p>
        )}

        {/* Create Task */}
        <div className="mt-6 flex justify-end gap-3">
          <button
            onClick={() => {
              setEditingTask(null);
              setShowTaskForm(true);
            }}
            className="px-4 py-2 bg-emerald-500 text-white rounded-lg hover:bg-emerald-600 transition"
          >
            + New Task
          </button>

          <button
            onClick={onClose}
            className="px-4 py-2 bg-gray-200 rounded-lg hover:bg-gray-300 transition"
          >
            Close
          </button>
        </div>
      </div>

      {/* Task Modals */}
      {selectedTaskId && (
        <TaskDetails taskId={selectedTaskId} onClose={() => setSelectedTaskId(null)} />
      )}

      {showTaskForm && (
        <TaskForm
          projectId={project.id}
          task={editingTask}
          onSuccess={() => {
            setShowTaskForm(false);
            setEditingTask(null);
            fetchProject(); // refresh project after changes
          }}
          onCancel={() => {
            setShowTaskForm(false);
            setEditingTask(null);
          }}
        />
      )}
    </div>
  );
};