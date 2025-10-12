import { Checkbox } from '@/components/ui/checkbox';
import { Trash2 } from 'lucide-react';
import type { Task as TaskType } from '../types';

interface TaskProps {
    task: TaskType;
    onToggle: (taskId: string) => void;
    onDelete: (taskId: string) => void;
}

export const Task = ({ task, onToggle, onDelete }: TaskProps) => {
    return (
        <div className="flex items-center gap-3 p-3 rounded-lg border border-neutral-200 dark:border-neutral-800 bg-white dark:bg-neutral-900 hover:bg-neutral-50 dark:hover:bg-neutral-800 transition-colors group">
            <Checkbox
                checked={task.completed}
                onCheckedChange={() => onToggle(task.id)}
            />
            <span className={`flex-1 ${task.completed ? 'line-through text-neutral-400 dark:text-neutral-500' : 'text-neutral-700 dark:text-neutral-300'}`}>
                {task.title}
            </span>
            <button
                onClick={() => onDelete(task.id)}
                className="opacity-0 group-hover:opacity-100 p-1 hover:bg-neutral-200 dark:hover:bg-neutral-700 rounded transition-opacity"
            >
                <Trash2 className="w-4 h-4 text-neutral-500 dark:text-neutral-400" />
            </button>
        </div>
    );
};
