import React, { useEffect, useState } from 'react';
import { apiService } from '../services/api';
import { Task } from '../types';

interface TaskDetailsProps {
  taskId: number;
  onClose: () => void;
}



export const TaskDetails: React.FC<TaskDetailsProps> = ({ taskId, onClose }) => {
  const [task, setTask] = useState<Task | null>(null);

  useEffect(() => {
    const fetchTask = async () => {
      try {
        const data = await apiService.getTask(taskId);
        const formattedTask: Task = {
          id: data.id,
          name: data.name,
          description: data.description,
          status: data.status,
          project: data.projectDto || undefined,
          assignedUsers: data.userSummaryDto || [], 
          createdAt: data.createdAt,
          updatedAt: data.updatedAt,
        };
        setTask(formattedTask);
      } catch (err) {
        console.error('Failed to load task:', err);
      }
    };
    fetchTask();
  }, [taskId]);

  if (!task) return <div className="p-4">Loading...</div>;

  return (
    <div className="fixed inset-0 bg-black/50 flex justify-center items-center p-4 z-50">
      <div className="bg-white p-6 rounded-xl w-full max-w-lg">
        <h2 className="text-xl font-bold mb-2">{task.name}</h2>
        <p className="text-slate-700 mb-4">{task.description}</p>
        <p className="mb-2"><strong>Status:</strong> {task.status}</p>
        <p><strong>Assigned Users:</strong></p>
        <ul className="list-disc list-inside">
          {task.assignedUsers.length > 0 ? (
            task.assignedUsers.map(u => <li key={u.id}>{u.username}</li>)
          ) : (
            <li>No users assigned</li>
          )}
        </ul>

        <button
          onClick={onClose}
          className="mt-6 px-4 py-2 bg-gray-200 rounded hover:bg-gray-300"
        >
          Close
        </button>
      </div>
    </div>
  );
};
