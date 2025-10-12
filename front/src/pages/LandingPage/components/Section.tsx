import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Plus, Trash2 } from 'lucide-react';
import type { Section as SectionType, Task as TaskType } from '../types';
import { Task } from './Task';

interface SectionProps {
    section: SectionType;
    tasks: TaskType[];
    onDeleteSection: (sectionId: string) => void;
    onAddTask: (sectionId: string, title: string) => void;
    onToggleTask: (taskId: string) => void;
    onDeleteTask: (taskId: string) => void;
    addingSectionId: string | null;
    setAddingSectionId: (id: string | null) => void;
}

export const Section = ({
    section,
    tasks,
    onDeleteSection,
    onAddTask,
    onToggleTask,
    onDeleteTask,
    addingSectionId,
    setAddingSectionId
}: SectionProps) => {
    const [newTaskTitle, setNewTaskTitle] = useState('');

    const handleAddTask = () => {
        if (!newTaskTitle.trim()) return;
        onAddTask(section.id, newTaskTitle);
        setNewTaskTitle('');
        setAddingSectionId(null);
    };

    return (
        <Card className="shadow-sm bg-white dark:bg-neutral-950 border-neutral-200 dark:border-neutral-800">
            <CardHeader className="pb-3">
                <div className="flex items-center justify-between">
                    <div className="flex items-center gap-3">
                        <CardTitle className="text-lg text-neutral-900 dark:text-neutral-100">{section.title}</CardTitle>
                        <Badge variant="outline" className="text-xs">
                            {tasks.length}
                        </Badge>
                    </div>
                    <Button
                        variant="ghost"
                        size="sm"
                        onClick={() => onDeleteSection(section.id)}
                        className="h-8 w-8 p-0"
                    >
                        <Trash2 className="w-4 h-4" />
                    </Button>
                </div>
            </CardHeader>
            <CardContent className="space-y-2">
                {tasks.map((task) => (
                    <Task
                        key={task.id}
                        task={task}
                        onToggle={onToggleTask}
                        onDelete={onDeleteTask}
                    />
                ))}
                
                {addingSectionId === section.id ? (
                    <div className="flex gap-2 p-2">
                        <Input
                            placeholder="Task title..."
                            value={newTaskTitle}
                            onChange={(e) => setNewTaskTitle(e.target.value)}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') handleAddTask();
                                if (e.key === 'Escape') {
                                    setAddingSectionId(null);
                                    setNewTaskTitle('');
                                }
                            }}
                            autoFocus
                            className="text-sm"
                        />
                        <Button size="sm" onClick={handleAddTask}>
                            Add
                        </Button>
                        <Button 
                            size="sm" 
                            variant="ghost" 
                            onClick={() => {
                                setAddingSectionId(null);
                                setNewTaskTitle('');
                            }}
                        >
                            Cancel
                        </Button>
                    </div>
                ) : (
                    <Button
                        variant="ghost"
                        size="sm"
                        onClick={() => setAddingSectionId(section.id)}
                        className="w-full justify-start text-neutral-500 dark:text-neutral-400"
                    >
                        <Plus className="w-4 h-4 mr-2" />
                        Add task
                    </Button>
                )}
            </CardContent>
        </Card>
    );
};
