import React, { useState, useEffect } from 'react';
import { X, Save, Shield } from 'lucide-react';
import { apiService } from '../services/api';

interface User {
  id?: number;
  username: string;
  email: string;
  roles: any[];
}

interface UserFormProps {
  user?: User | null;
  onSuccess: () => void;
  onCancel: () => void;
}

export const UserForm: React.FC<UserFormProps> = ({ user, onSuccess, onCancel }) => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    selectedRoles: [] as number[]
  });
  const [roles, setRoles] = useState<any[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    loadRoles();
    if (user) {
      setFormData({
        username: user.username,
        email: user.email,
        password: '',
        selectedRoles: user.roles?.map(r => r.id) || []
      });
    }
  }, [user]);

  const loadRoles = async () => {
    try {
      const data = await apiService.getRoles(); 
      setRoles(data); 
    } catch (err) {
      console.error('Failed to load roles:', err);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsLoading(true);
    setError('');

    try {
      const userData = {
        username: formData.username,
        email: formData.email,
        ...(formData.password && { password: formData.password }),
        roleDtos: formData.selectedRoles.map(id => ({ id }))
      };

      if (user?.id) {
        await apiService.updateUser(user.id, userData);
      } else {
        if (!formData.password) {
          setError('Password is required for new users');
          setIsLoading(false);
          return;
        }
        await apiService.register(userData);
      }
      onSuccess();
    } catch (err) {
      setError(err instanceof Error ? err.message : 'Failed to save user');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 backdrop-blur-sm flex items-center justify-center p-4 z-50">
      <div className="bg-white rounded-2xl shadow-2xl w-full max-w-md">
        {/* Header */}
        <div className="flex items-center justify-between p-6 border-b border-slate-200">
          <h2 className="text-2xl font-bold text-slate-800">
            {user ? 'Edit User' : 'New User'}
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

          <div>
            <label htmlFor="username" className="block text-sm font-medium text-slate-700 mb-2">
              Username
            </label>
            <input
              id="username"
              type="text"
              value={formData.username}
              onChange={(e) => setFormData(prev => ({ ...prev, username: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              placeholder="Enter username"
              required
            />
          </div>

          <div>
            <label htmlFor="email" className="block text-sm font-medium text-slate-700 mb-2">
              Email
            </label>
            <input
              id="email"
              type="email"
              value={formData.email}
              onChange={(e) => setFormData(prev => ({ ...prev, email: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              placeholder="Enter email"
              required
            />
          </div>

          <div>
            <label htmlFor="password" className="block text-sm font-medium text-slate-700 mb-2">
              Password {user && <span className="text-slate-500">(leave blank to keep current)</span>}
            </label>
            <input
              id="password"
              type="password"
              value={formData.password}
              onChange={(e) => setFormData(prev => ({ ...prev, password: e.target.value }))}
              className="w-full px-4 py-3 border border-slate-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent transition-all duration-200"
              placeholder="Enter password"
              {...(!user && { required: true })}
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-slate-700 mb-3">
              <Shield className="w-4 h-4 inline mr-2" />
              Roles
            </label>
            <div className="space-y-2 border border-slate-300 rounded-lg p-3 max-h-32 overflow-y-auto">
              {roles.map((role) => (
                <label key={role.id} className="flex items-center space-x-3 p-2 hover:bg-slate-50 rounded-lg cursor-pointer">
                  <input
                    type="checkbox"
                    checked={formData.selectedRoles.includes(role.id)}
                    onChange={(e) => {
                      if (e.target.checked) {
                        setFormData(prev => ({
                          ...prev,
                          selectedRoles: [...prev.selectedRoles, role.id]
                        }));
                      } else {
                        setFormData(prev => ({
                          ...prev,
                          selectedRoles: prev.selectedRoles.filter(id => id !== role.id)
                        }));
                      }
                    }}
                    className="rounded border-slate-300 text-blue-600 focus:ring-blue-500"
                  />
                  <span className="text-sm font-medium text-slate-800">
                    {role.name?.replace('ROLE_', '')}
                  </span>
                </label>
              ))}
            </div>
          </div>

          {/* Form Actions */}
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
              <span>{isLoading ? 'Saving...' : 'Save User'}</span>
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};