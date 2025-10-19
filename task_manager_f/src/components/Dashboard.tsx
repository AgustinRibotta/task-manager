import React from 'react';
import { useAuth } from '../contexts/AuthContext';
import { Users, FolderOpen, CheckSquare, TrendingUp } from 'lucide-react';

interface DashboardProps {
  onNavigate: (page: string) => void;
}

export const Dashboard: React.FC<DashboardProps> = ({ onNavigate }) => {
  const { user, isAdmin, isProjectManager } = useAuth();

  const cards = [
    {
      title: 'Projects',
      description: isAdmin ? 'Manage all projects' : 'View your projects',
      icon: FolderOpen,
      color: 'from-blue-500 to-blue-600',
      action: () => onNavigate('projects'),
      show: true
    },
    {
      title: 'Tasks',
      description: 'Manage and track tasks',
      icon: CheckSquare,
      color: 'from-emerald-500 to-emerald-600',
      action: () => onNavigate('tasks'),
      show: true
    },
    {
      title: 'Users',
      description: 'Manage system users',
      icon: Users,
      color: 'from-amber-500 to-amber-600',
      action: () => onNavigate('users'),
      show: isAdmin
    },
    {
      title: 'Analytics',
      description: 'View performance metrics',
      icon: TrendingUp,
      color: 'from-purple-500 to-purple-600',
      action: () => {},
      show: isAdmin || isProjectManager
    }
  ].filter(card => card.show);

  return (
    <div className="space-y-8">
      {/* Welcome Section */}
      <div className="bg-white/60 backdrop-blur-sm rounded-2xl p-8 border border-white/20">
        <h1 className="text-3xl font-bold text-slate-800 mb-2">
          Welcome back, {user?.username}!
        </h1>
        <p className="text-slate-600 text-lg">
          {isAdmin 
            ? 'You have full system access as an administrator.'
            : isProjectManager 
            ? 'Manage your projects and collaborate with your team.'
            : 'View your assigned tasks and project updates.'
          }
        </p>
      </div>

      {/* Quick Actions */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {cards.map((card) => {
          const Icon = card.icon;
          return (
            <div
              key={card.title}
              onClick={card.action}
              className="bg-white/60 backdrop-blur-sm rounded-xl p-6 border border-white/20 hover:bg-white/80 transition-all duration-300 cursor-pointer group hover:scale-105 hover:shadow-lg"
            >
              <div className={`w-12 h-12 bg-gradient-to-r ${card.color} rounded-lg flex items-center justify-center mb-4 group-hover:scale-110 transition-transform duration-300`}>
                <Icon className="w-6 h-6 text-white" />
              </div>
              <h3 className="text-lg font-semibold text-slate-800 mb-2">{card.title}</h3>
              <p className="text-slate-600 text-sm">{card.description}</p>
            </div>
          );
        })}
      </div>

      {/* Role Information */}
      <div className="bg-gradient-to-r from-blue-50 to-emerald-50 rounded-xl p-6 border border-blue-200">
        <h2 className="text-lg font-semibold text-slate-800 mb-4">Your Role & Permissions</h2>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div>
            <h3 className="font-medium text-slate-700 mb-2">Current Role</h3>
            <span className="inline-flex items-center px-3 py-1 rounded-full text-sm font-medium bg-blue-100 text-blue-800">
              {user?.roleDtos?.[0]?.name?.replace('ROLE_', '') ?? 'No role'}
            </span>
          </div>
          <div>
            <h3 className="font-medium text-slate-700 mb-2">Access Level</h3>
            <ul className="text-sm text-slate-600 space-y-1">
              {isAdmin && <li>• Full system administration</li>}
              {isProjectManager && <li>• Project and task management</li>}
              <li>• View assigned tasks</li>
              <li>• Update task status</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};