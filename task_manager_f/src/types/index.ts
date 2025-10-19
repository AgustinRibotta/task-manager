export interface User {
  id: number;
  username: string;
  email: string;
  roleDtos: Role[];
}

export interface Role {
  id: number;
  name: string;
}

export interface Project {
  id: number;
  name: string;
  description: string;
  usersDtos: User[];
  tasksDtos: Task[]; 
  createdAt?: string;
  updatedAt?: string;
}

export interface Task {
  id: number;
  name: string;
  description: string;
  status: TaskStatus;
  project?: Project;
  assignedUsers: User[];
  createdAt?: string;
  updatedAt?: string;
}

export enum TaskStatus {
  TODO = 'TODO',
  IN_PROGRESS = 'IN_PROGRESS',
  DONE = 'DONE'
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
  roleDtos: { id: number }[];
}

export interface AuthResponse {
  token: string;
}

export interface CreateProjectRequest {
  name: string;
  description: string;
  usersDtos: { id: number }[];
}

export interface CreateTaskRequest {
  name: string;
  description: string;
  status: TaskStatus;
  userSummaryDto: { id: number }[];
  projectId?: number;
}

