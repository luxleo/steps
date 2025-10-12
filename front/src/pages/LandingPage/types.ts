// Types
export interface Task {
    id: string;
    title: string;
    completed: boolean;
    sectionId: string;
    projectId: string;
}

export interface Section {
    id: string;
    title: string;
    projectId: string;
}

export interface Project {
    id: string;
    title: string;
    color: string;
}
