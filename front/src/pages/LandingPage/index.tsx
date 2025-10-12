import { useState, useEffect } from 'react';
import { Badge } from '@/components/ui/badge';
import type { Task, Section, Project } from './types';
import { ProjectSidebar } from './components/ProjectSidebar';
import { Section as SectionComponent } from './components/Section';
import { AddSectionButton } from './components/AddSectionButton';

const Index = () => {
    // Initial data
    const [projects, setProjects] = useState<Project[]>([
        { id: '1', title: 'Personal', color: 'bg-blue-500' },
        { id: '2', title: 'Work', color: 'bg-green-500' },
        { id: '3', title: 'Shopping', color: 'bg-purple-500' },
    ]);

    const [sections, setSections] = useState<Section[]>([
        { id: '1', title: 'To Do', projectId: '1' },
        { id: '2', title: 'In Progress', projectId: '1' },
        { id: '3', title: 'Done', projectId: '1' },
    ]);

    const [tasks, setTasks] = useState<Task[]>([
        { id: '1', title: 'Buy groceries', completed: false, sectionId: '1', projectId: '1' },
        { id: '2', title: 'Read a book', completed: false, sectionId: '1', projectId: '1' },
        { id: '3', title: 'Exercise', completed: true, sectionId: '3', projectId: '1' },
    ]);

    const [selectedProjectId, setSelectedProjectId] = useState<string>('1');
    const [addingSectionId, setAddingSectionId] = useState<string | null>(null);
    const [showAddSectionForm, setShowAddSectionForm] = useState<boolean>(false);

    // Reset form states when project changes
    useEffect(() => {
        setAddingSectionId(null);
        setShowAddSectionForm(false);
    }, [selectedProjectId]);

    // Get filtered data for selected project
    const selectedProject = projects.find(p => p.id === selectedProjectId);
    const projectSections = sections.filter(s => s.projectId === selectedProjectId);
    const projectTasks = tasks.filter(t => t.projectId === selectedProjectId);

    // Handlers
    const addTask = (sectionId: string, title: string) => {
        const newTask: Task = {
            id: Date.now().toString(),
            title,
            completed: false,
            sectionId,
            projectId: selectedProjectId,
        };
        
        setTasks([...tasks, newTask]);
    };

    const toggleTask = (taskId: string) => {
        setTasks(tasks.map(task =>
            task.id === taskId ? { ...task, completed: !task.completed } : task
        ));
    };

    const deleteTask = (taskId: string) => {
        setTasks(tasks.filter(task => task.id !== taskId));
    };

    const addSection = (title: string) => {
        const newSection: Section = {
            id: Date.now().toString(),
            title,
            projectId: selectedProjectId,
        };
        
        setSections([...sections, newSection]);
    };

    const deleteSection = (sectionId: string) => {
        setSections(sections.filter(section => section.id !== sectionId));
        setTasks(tasks.filter(task => task.sectionId !== sectionId));
    };

    const addProject = (title: string) => {
        const colors = ['bg-red-500', 'bg-blue-500', 'bg-green-500', 'bg-yellow-500', 'bg-purple-500', 'bg-pink-500'];
        const newProject: Project = {
            id: Date.now().toString(),
            title,
            color: colors[Math.floor(Math.random() * colors.length)],
        };
        
        setProjects([...projects, newProject]);
    };

    const deleteProject = (projectId: string) => {
        if (projects.length <= 1) return; // Keep at least one project
        
        setProjects(projects.filter(project => project.id !== projectId));
        setSections(sections.filter(section => section.projectId !== projectId));
        setTasks(tasks.filter(task => task.projectId !== projectId));
        
        if (selectedProjectId === projectId) {
            setSelectedProjectId(projects.find(p => p.id !== projectId)?.id || projects[0].id);
        }
    };

    return (
        <div className="flex h-screen bg-white dark:bg-neutral-950">
            <ProjectSidebar
                projects={projects}
                selectedProjectId={selectedProjectId}
                onSelectProject={setSelectedProjectId}
                onAddProject={addProject}
                onDeleteProject={deleteProject}
            />

            {/* Main Content */}
            <div className="flex-1 flex flex-col overflow-y-auto">
                {/* Header */}
                <div className="bg-white dark:bg-neutral-950 border-b border-neutral-200 dark:border-neutral-800 p-6 sticky top-0 z-10">
                    <div className="flex items-center gap-3">
                        <div className={`w-4 h-4 rounded-full ${selectedProject?.color}`} />
                        <h2 className="text-3xl font-bold text-neutral-900 dark:text-neutral-100">{selectedProject?.title}</h2>
                        <Badge variant="secondary" className="ml-2">
                            {projectTasks.length} {projectTasks.length === 1 ? 'task' : 'tasks'}
                        </Badge>
                    </div>
                </div>

                {/* Sections and Tasks */}
                <div className="p-6">
                    <div className="space-y-6 max-w-4xl">
                        {projectSections.map((section) => {
                            const sectionTasks = projectTasks.filter(t => t.sectionId === section.id);
                            
                            return (
                                <SectionComponent
                                    key={section.id}
                                    section={section}
                                    tasks={sectionTasks}
                                    onDeleteSection={deleteSection}
                                    onAddTask={addTask}
                                    onToggleTask={toggleTask}
                                    onDeleteTask={deleteTask}
                                    addingSectionId={addingSectionId}
                                    setAddingSectionId={setAddingSectionId}
                                />
                            );
                        })}

                        <AddSectionButton
                            onAddSection={addSection}
                            showForm={showAddSectionForm}
                            setShowForm={setShowAddSectionForm}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Index;