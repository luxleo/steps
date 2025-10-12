import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Plus, Trash2, ChevronRight } from 'lucide-react';
import type { Project } from '../types';

interface ProjectSidebarProps {
    projects: Project[];
    selectedProjectId: string;
    onSelectProject: (projectId: string) => void;
    onAddProject: (title: string) => void;
    onDeleteProject: (projectId: string) => void;
}

export const ProjectSidebar = ({
    projects,
    selectedProjectId,
    onSelectProject,
    onAddProject,
    onDeleteProject
}: ProjectSidebarProps) => {
    const [newProjectTitle, setNewProjectTitle] = useState('');

    const handleAddProject = () => {
        if (!newProjectTitle.trim()) return;
        onAddProject(newProjectTitle);
        setNewProjectTitle('');
    };

    return (
        <div className="w-64 bg-white dark:bg-neutral-950 border-r border-neutral-200 dark:border-neutral-800 flex flex-col">
            <div className="p-4 border-b border-neutral-200 dark:border-neutral-800">
                <h1 className="text-2xl font-bold text-neutral-900 dark:text-neutral-100">Projects</h1>
            </div>
            
            <ScrollArea className="flex-1 p-2">
                <div className="space-y-1">
                    {projects.map((project) => (
                        <div
                            key={project.id}
                            className={`flex items-center justify-between p-3 rounded-lg cursor-pointer transition-colors group ${
                                selectedProjectId === project.id
                                    ? 'bg-neutral-100 dark:bg-neutral-800'
                                    : 'hover:bg-neutral-50 dark:hover:bg-neutral-900'
                            }`}
                            onClick={() => onSelectProject(project.id)}
                        >
                            <div className="flex items-center gap-3 flex-1">
                                <div className={`w-3 h-3 rounded-full ${project.color}`} />
                                <span className="font-medium text-neutral-700 dark:text-neutral-300">{project.title}</span>
                            </div>
                            {selectedProjectId === project.id && (
                                <ChevronRight className="w-4 h-4 text-neutral-400 dark:text-neutral-500" />
                            )}
                            {projects.length > 1 && (
                                <button
                                    onClick={(e) => {
                                        e.stopPropagation();
                                        onDeleteProject(project.id);
                                    }}
                                    className="opacity-0 group-hover:opacity-100 p-1 hover:bg-neutral-200 dark:hover:bg-neutral-700 rounded transition-opacity"
                                >
                                    <Trash2 className="w-3 h-3 text-neutral-500 dark:text-neutral-400" />
                                </button>
                            )}
                        </div>
                    ))}
                </div>
            </ScrollArea>

            <div className="p-4 border-t border-neutral-200 dark:border-neutral-800">
                <div className="flex gap-2">
                    <Input
                        placeholder="New project..."
                        value={newProjectTitle}
                        onChange={(e) => setNewProjectTitle(e.target.value)}
                        onKeyDown={(e) => e.key === 'Enter' && handleAddProject()}
                        className="text-sm"
                    />
                    <Button size="sm" onClick={handleAddProject}>
                        <Plus className="w-4 h-4" />
                    </Button>
                </div>
            </div>
        </div>
    );
};
