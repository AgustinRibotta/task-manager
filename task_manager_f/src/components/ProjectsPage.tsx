import React, { useState, useEffect } from 'react';
import { Plus, Edit3, Trash2, Users, Calendar, FolderOpen, CheckSquare } from 'lucide-react';
import { useAuth } from '../contexts/AuthContext';
import { apiService } from '../services/api';
import { ProjectForm } from './ProjectForm';
import { ProjectDetails } from './ProjectDetails';
import { Task } from '../types';

export interface User {
  id: number;
  username: string;
  email?: string;
}

export interface Project {
  id: number;
  name: string;
  description: string;
  usersDtos: User[];
  tasksDtos: Task[];
  createdAt?: string;
}

export const ProjectsPage: React.FC = () => {
  const [projects, setProjects] = useState<Project[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [showForm, setShowForm] = useState(false);
  const [editingProject, setEditingProject] = useState<Project | null>(null);
  const [error, setError] = useState('');
  const { isAdmin, user } = useAuth();
  const [selectedProjectId, setSelectedProjectId] = useState<number | null>(null);

  useEffect(() => {
    loadProjects();
  }, []);


  const loadProjects = async () => {
    if (!user) return;

    try {
      setIsLoading(true);
      const data: Project[] = await apiService.getProjectsByRole(user);
      setProjects(data);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to load projects');
    } finally {
      setIsLoading(false);
    }
  };
  
  const handleDelete = async (id: number) => {
    if (!confirm('Are you sure you want to delete this project?')) return;
    
    try {
      await apiService.deleteProject(id);
      await loadProjects();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to delete project');
    }
  };

  const handleViewProject = (projectId: number) => {
    setSelectedProjectId(projectId);
  };

  const handleFormSuccess = () => {
    setShowForm(false);
    setEditingProject(null);
    loadProjects();
  };

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
      <div className="flex justify-between items-center">
        <div>
          <h1 className="text-3xl font-bold text-slate-800">Projects</h1>
          <p className="text-slate-600 mt-1">Manage and organize your projects</p>
        </div>
        {isAdmin && (
          <button
            onClick={() => setShowForm(true)}
            className="flex items-center space-x-2 bg-gradient-to-r from-blue-500 to-emerald-500 text-white px-4 py-2 rounded-lg hover:from-blue-600 hover:to-emerald-600 transition-all duration-200 hover:scale-105"
          >
            <Plus className="w-4 h-4" />
            <span>New Project</span>
          </button>
        )}
      </div>

      {/* Error Message */}
      {error && (
        <div className="bg-red-50 border border-red-200 rounded-lg p-4 text-red-700">
          {error}
        </div>
      )}

      {/* Projects Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {projects.map((project) => (
          <div
            key={project.id}
            className="bg-white/60 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:bg-white/80 transition-all duration-300 hover:scale-105 hover:shadow-lg group"
          >
            <div className="flex justify-between items-start mb-4">
              <div className="flex-1">
                <h3 className="text-lg font-semibold text-slate-800 mb-2 group-hover:text-blue-600 transition-colors">
                  {project.name}
                </h3>
                <p className="text-slate-600 text-sm line-clamp-2">{project.description}</p>
              </div>
              {isAdmin && (
                <div className="flex space-x-1 opacity-0 group-hover:opacity-100 transition-opacity duration-200">
                  <button
                    onClick={() => {
                      setEditingProject(project);
                      setShowForm(true);
                    }}
                    className="p-2 text-slate-400 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                  >
                    <Edit3 className="w-4 h-4" />
                  </button>

                  <button
                    onClick={() => handleDelete(project.id)}
                    className="p-2 text-slate-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                  >
                    <Trash2 className="w-4 h-4" />
                  </button>
                </div>
              )}
            </div>

            <div className="flex items-center justify-between mt-4 pt-4 border-t border-slate-200">
              <div className="flex items-center space-x-2 text-sm text-slate-600">
                <Users className="w-4 h-4" />
                <span>{project.usersDtos?.length || 0} members</span>
              </div>
              <div className="flex items-center space-x-2 text-sm text-slate-600">
                <CheckSquare className="w-4 h-4" />   {/* instead of Users */}
                <span>{project.tasksDtos?.length || 0} tasks</span>
              </div>
              <button
                  onClick={() => handleViewProject(project.id)}
                  className="px-3 py-1 text-sm bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition"
                  >
                  View Details
              </button>
              {project.createdAt && (
                <div className="flex items-center space-x-2 text-sm text-slate-600">
                  <Calendar className="w-4 h-4" />
                  <span>{new Date(project.createdAt).toLocaleDateString()}</span>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>

      {/* Empty State */}
      {projects.length === 0 && (
        <div className="text-center py-12">
          <div className="w-16 h-16 bg-slate-100 rounded-full flex items-center justify-center mx-auto mb-4">
            <FolderOpen className="w-8 h-8 text-slate-400" />
          </div>
          <h3 className="text-lg font-medium text-slate-800 mb-2">No projects yet</h3>
          <p className="text-slate-600 mb-4">Get started by creating your first project</p>
          
          {isAdmin && (
            <button
              onClick={() => setShowForm(true)}
              className="bg-gradient-to-r from-blue-500 to-emerald-500 text-white px-6 py-2 rounded-lg hover:from-blue-600 hover:to-emerald-600 transition-all duration-200"
            >
              Create Project
            </button>
          )}
        </div>
      )}

      {/* Project Form Modal */}
      {showForm && (
        <ProjectForm
          project={editingProject}
          onSuccess={handleFormSuccess}
          onCancel={() => {
            setShowForm(false);
            setEditingProject(null);
          }}
        />
      )}

      {/* Project Details Modal */}
      {selectedProjectId && (
      <ProjectDetails
        projectId={selectedProjectId}
        onClose={() => setSelectedProjectId(null)}
      />
      )}
    </div>
  );
};