import type {Meta, StoryObj} from '@storybook/react-vite';
import {fn} from 'storybook/test';
import {Task} from './Task';

const meta = {
    title: 'LandingPage/Task',
    component: Task,
    parameters: {
        layout: 'centered',
    },
    tags: ['autodocs'],
    argTypes: {
        task: {
            description: 'Task object containing id, title, completed status, sectionId, and projectId',
        },
        onToggle: {
            action: 'toggled',
            type: 'function',
            description: 'Callback function when task checkbox is toggled',
        },
        onDelete: {
            action: 'deleted',
            type: 'function',
            description: 'Callback function when task is deleted',
        },
    },
    args: {
        onToggle: fn(),
        onDelete: fn(),
    },
} satisfies Meta<typeof Task>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Uncompleted: Story = {
    args: {
        task: {
            id: '1',
            title: 'Buy groceries',
            completed: false,
            sectionId: '1',
            projectId: '1',
        },
    },
};

export const Completed: Story = {
    args: {
        task: {
            id: '2',
            title: 'Read a book',
            completed: true,
            sectionId: '1',
            projectId: '1',
        },
    },
};

export const LongTitle: Story = {
    args: {
        task: {
            id: '3',
            title: 'This is a very long task title that demonstrates how the component handles text overflow and wrapping behavior in the UI',
            completed: false,
            sectionId: '1',
            projectId: '1',
        },
    },
};

export const CompletedLongTitle: Story = {
    args: {
        task: {
            id: '4',
            title: 'This is a completed task with a very long title to show the strikethrough effect on lengthy text content',
            completed: true,
            sectionId: '1',
            projectId: '1',
        },
    },
};
