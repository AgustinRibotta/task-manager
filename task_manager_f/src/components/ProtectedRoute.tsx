import React from 'react';
import { useAuth } from '../contexts/AuthContext';

interface ProtectedRouteProps {
  children: React.ReactNode;
  requiredRoles?: string[];
  fallback?: React.ReactNode;
}

export const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ 
  children, 
  requiredRoles = [], 
  fallback 
}) => {
  const { isAuthenticated, hasRole } = useAuth();

  if (!isAuthenticated) {
    return fallback || (
      <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-slate-50 to-slate-100">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-slate-800 mb-2">Access Denied</h2>
          <p className="text-slate-600">Please log in to continue</p>
        </div>
      </div>
    );
  }

  if (requiredRoles.length > 0 && !requiredRoles.some(role => hasRole(role))) {
    return fallback || (
      <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-slate-50 to-slate-100">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-slate-800 mb-2">Insufficient Permissions</h2>
          <p className="text-slate-600">You don't have permission to access this page</p>
        </div>
      </div>
    );
  }

  return <>{children}</>;
};