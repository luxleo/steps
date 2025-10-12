import type { Meta, StoryObj } from '@storybook/react-vite';
import { fn } from 'storybook/test';
import { Section } from './Section';

const meta = {
    title: 'LandingPage/Section',
    component: Section,
    parameters: {
        layout: 'padded',
    },
    tags: ['autodocs'],
    argTypes: {
        section: {
            description: 'Section object containing id, title, and projectId',
        },
        tasks: {
            description: 'Array of tasks belonging to this section',
        },
        onDeleteSection: {
            action: 'section deleted',
            type: 'function',
            description: 'Callback function when section is deleted',
        },
        onAddTask: {
            action: 'task added',
            type: 'function',
            description: 'Callback function when a new task is added',
        },
        onToggleTask: {
            action: 'task toggled',
            type: 'function',
            description: 'Callback function when task checkbox is toggled',
        },
        onDeleteTask: {
            action: 'task deleted',
            type: 'function',
            description: 'Callback function when task is deleted',
        },
        addingSectionId: {
            control: 'text',
            description: 'ID of the section currently showing the add task form',
        },
        setAddingSectionId: {
            action: 'adding section id changed',
            type: 'function',
            description: 'Callback function to set which section is showing the add task form',
        },
    },
    args: {
        onDeleteSection: fn(),
        onAddTask: fn(),
        onToggleTask: fn(),
        onDeleteTask: fn(),
        setAddingSectionId: fn(),
    },
} satisfies Meta<typeof Section>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Empty: Story = {
    args: {
        section: {
            id: '1',
            title: 'To Do',
            projectId: '1',
        },
        tasks: [],
        addingSectionId: null,
    },
};

export const WithTasks: Story = {
    args: {
        section: {
            id: '1',
            title: 'To Do',
            projectId: '1',
        },
        tasks: [
            {
                id: '1',
                title: 'Buy groceries',
                completed: false,
                sectionId: '1',
                projectId: '1',
            },
            {
                id: '2',
                title: 'Read a book',
                completed: false,
                sectionId: '1',
                projectId: '1',
            },
            {
                id: '3',
                title: 'Exercise for 30 minutes',
                completed: false,
                sectionId: '1',
                projectId: '1',
            },
        ],
        addingSectionId: null,
    },
};

export const WithMixedTasks: Story = {
    args: {
        section: {
            id: '2',
            title: 'In Progress',
            projectId: '1',
        },
        tasks: [
            {
                id: '4',
                title: 'Write project documentation',
                completed: false,
                sectionId: '2',
                projectId: '1',
            },
            {
                id: '5',
                title: 'Review pull requests',
                completed: true,
                sectionId: '2',
                projectId: '1',
            },
            {
                id: '6',
                title: 'Fix bug in authentication',
                completed: false,
                sectionId: '2',
                projectId: '1',
            },
            {
                id: '7',
                title: 'Update dependencies',
                completed: true,
                sectionId: '2',
                projectId: '1',
            },
        ],
        addingSectionId: null,
    },
};

export const AddingTask: Story = {
    args: {
        section: {
            id: '3',
            title: 'Done',
            projectId: '1',
        },
        tasks: [
            {
                id: '8',
                title: 'Complete onboarding',
                completed: true,
                sectionId: '3',
                projectId: '1',
            },
        ],
        addingSectionId: '3',
    },
};

export const ManyTasks: Story = {
    args: {
        section: {
            id: '4',
            title: 'Backlog',
            projectId: '1',
        },
        tasks: [
            {
                id: '9',
                title: 'Task 1',
                completed: false,
                sectionId: '4',
                projectId: '1',
            },
            {
                id: '10',
                title: 'Task 2',
                completed: true,
                sectionId: '4',
                projectId: '1',
            },
            {
                id: '11',
                title: 'Task 3',
                completed: false,
                sectionId: '4',
                projectId: '1',
            },
            {
                id: '12',
                title: 'Task 4',
                completed: false,
                sectionId: '4',
                projectId: '1',
            },
            {
                id: '13',
                title: 'Task 5',
                completed: true,
                sectionId: '4',
                projectId: '1',
            },
            {
                id: '14',
                title: 'Task 6',
                completed: false,
                sectionId: '4',
                projectId: '1',
            },
        ],
        addingSectionId: null,
    },
};
