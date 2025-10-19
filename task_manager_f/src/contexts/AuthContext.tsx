import React, { createContext, useContext, useEffect, useState } from 'react';
import { User } from '../types';

interface AuthContextType {
  user: User | null;
  token: string | null;
  login: (response: { jwtToken: string }) => void;
  logout: () => void;
  isAuthenticated: boolean;
  hasRole: (roleName: string) => boolean;
  isAdmin: boolean;
  isProjectManager: boolean;
  isUser: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

interface AuthProviderProps {
  children: React.ReactNode;
}

function parseJWT(token: string): User | null {
  if (!token) return null;

  const parts = token.split('.');
  if (parts.length !== 3) return null; 
  
  try {
    const base64Url = parts[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
        .join('')
    );
    const payload = JSON.parse(jsonPayload);

    return {
      id: payload.userId || payload.sub,
      username: payload.sub || payload.username,
      email: payload.email || '',
      roleDtos: (payload.roles || []).map((r: string) => ({ name: r.startsWith('ROLE_') ? r : `ROLE_${r}` }))
    };
  } catch (error) {
    console.error('Failed to parse JWT:', error);
    return null;
  }
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [token, setToken] = useState<string | null>(localStorage.getItem('token'));
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    if (token) { 
      const parsedUser = parseJWT(token);
      setUser(parsedUser);
      console.log('Parsed User from JWT:', parsedUser);
    } else {
      setUser(null);
      console.log('No token found, user is null');
    }
  }, [token]);

  const login = (tokenOrResponse: string | { jwtToken: string }) => {
    const newToken = typeof tokenOrResponse === 'string' 
      ? tokenOrResponse 
      : tokenOrResponse?.jwtToken;

    if (!newToken) {
      console.log('Login failed: no token received');
      return;
    }

    localStorage.setItem('token', newToken);
    setToken(newToken);

    const parsedUser = parseJWT(newToken);
    setUser(parsedUser);
  };

  const logout = () => {
    localStorage.removeItem('token');
    setToken(null);
    setUser(null);
  };

  const hasRole = (roleName: string): boolean => {
    if (!user?.roleDtos) return false;
    return user.roleDtos.some(role => role.name === roleName || role.name === `ROLE_${roleName}`);
  };

  const isAuthenticated = !!token && !!user;
  const isAdmin = hasRole('ADMIN');
  const isProjectManager = hasRole('PROJECT_MANAGER');
  const isUser = hasRole('USER');

  const value: AuthContextType = {
    user,
    token,
    login,
    logout,
    isAuthenticated,
    hasRole,
    isAdmin,
    isProjectManager,
    isUser
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};