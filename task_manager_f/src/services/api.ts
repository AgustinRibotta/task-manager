const API_BASE_URL = 'http://localhost:8080/api/v1';

class ApiService {
  private getAuthHeaders(): HeadersInit {
    const token = localStorage.getItem('token');
    if (!token) return { 'Content-Type': 'application/json' };
    return {
      'Content-Type': 'application/json',
      ...(token && { 'Authorization': `Bearer ${token}` })
    };
  }

  private async handleResponse<T>(response: Response): Promise<T> {
    if (!response.ok) {
      const error = await response.text();
      throw new Error(error || `HTTP ${response.status}`);
    }
    
    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
      return response.json();
    }
    
    return response.text() as unknown as T;
  }

  // Authentication
  async login(credentials: { username: string; password: string }) {
    const response = await fetch(`${API_BASE_URL}/auth/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(credentials)
    });
    return this.handleResponse<{ jwtToken: string }>(response);
  }

  async register(userData: any) {
    const response = await fetch(`${API_BASE_URL}/auth/users`, {
      method: 'POST',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(userData)
    });
    return this.handleResponse(response);
  }

  // Users
  async getUsers() {
    const response = await fetch(`${API_BASE_URL}/auth/users`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any[]>(response);
  }

  async getUser(id: number) {
    const response = await fetch(`${API_BASE_URL}/auth/users/${id}`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any>(response);
  }

  async updateUser(id: number, userData: any) {
    const response = await fetch(`${API_BASE_URL}/auth/users/${id}`, {
      method: 'PUT',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(userData)
    });
    return this.handleResponse(response);
  }

  async deleteUser(id: number) {
    const response = await fetch(`${API_BASE_URL}/auth/users/${id}`, {
      method: 'DELETE',
      headers: this.getAuthHeaders()
    });
    return this.handleResponse(response);
  }

  // Roles
  async getRoles() {
    const response = await fetch(`${API_BASE_URL}/roles`, {
      headers: this.getAuthHeaders(),
    });
    return this.handleResponse<any[]>(response);
  }

  async createRole(roleData: { name: string }) {
    const response = await fetch(`${API_BASE_URL}/roles`, {
      method: 'POST',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(roleData)
    });
    return this.handleResponse(response);
  }

  // Projects
  async getAllProjects() {
    const response = await fetch(`${API_BASE_URL}/projects/all`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any[]>(response);
  }

  async getProject(id: number) {
    const response = await fetch(`${API_BASE_URL}/projects/project/${id}`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any>(response);
  }

  async createProject(projectData: any) {
    const response = await fetch(`${API_BASE_URL}/projects/project/new`, {
      method: 'POST',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(projectData)
    });
    return this.handleResponse(response);
  }

  async updateProject(id: number, projectData: any) {
    const response = await fetch(`${API_BASE_URL}/projects/project/${id}`, {
      method: 'PUT',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(projectData)
    });
    return this.handleResponse(response);
  }

  async deleteProject(id: number) {
    const response = await fetch(`${API_BASE_URL}/projects/project/${id}`, {
      method: 'DELETE',
      headers: this.getAuthHeaders()
    });
    return this.handleResponse(response);
  }

  async getMyProjects(userId: number) {
    const response = await fetch(`${API_BASE_URL}/projects/my/${userId}`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any[]>(response);
  }

  async getProjectsByRole(user: { id: number; roleDtos: { name: string }[] }) {
    const isAdmin = user.roleDtos.some(r => r.name === 'ROLE_ADMIN');
    
    if (isAdmin) {
      return this.getAllProjects(); 
    }
    
    return this.getMyProjects(user.id); 
  }

  // Tasks
  async getAllTasks() {
    const response = await fetch(`${API_BASE_URL}/tasks/all`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any[]>(response);
  }

  async getTask(id: number) {
    const response = await fetch(`${API_BASE_URL}/tasks/task/${id}`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any>(response);
  }

  async createTask(taskData: any) {
    const response = await fetch(`${API_BASE_URL}/tasks/task/new`, {
      method: 'POST',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(taskData)
    });
    return this.handleResponse(response);
  }

  async updateTask(id: number, taskData: any) {
    const response = await fetch(`${API_BASE_URL}/tasks/task/${id}`, {
      method: 'PUT',
      headers: this.getAuthHeaders(),
      body: JSON.stringify(taskData)
    });
    return this.handleResponse(response);
  }

  async deleteTask(id: number) {
    const response = await fetch(`${API_BASE_URL}/tasks/task/${id}`, {
      method: 'DELETE',
      headers: this.getAuthHeaders()
    });
    return this.handleResponse(response);
  }

    async getMyTask(userId: number) {
    const response = await fetch(`${API_BASE_URL}/tasks/my/${userId}`, {
      headers: this.getAuthHeaders()
    });
    return this.handleResponse<any[]>(response);
  }

  async getTaskByRole(user: { id: number; roleDtos: { name: string }[] }) {
    const isAdmin = user.roleDtos.some(r => r.name === 'ROLE_ADMIN');
    
    if (isAdmin) {
      return this.getAllTasks(); 
    }
    
    return this.getMyTask(user.id); 
  }

}



export const apiService = new ApiService();