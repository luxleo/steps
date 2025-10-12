import type { Meta, StoryObj } from '@storybook/react-vite';
import { fn } from 'storybook/test';
import { AddSectionButton } from './AddSectionButton';

const meta = {
    title: 'LandingPage/AddSectionButton',
    component: AddSectionButton,
    parameters: {
        layout: 'padded',
    },
    tags: ['autodocs'],
    argTypes: {
        onAddSection: {
            action: 'section added',
            type: 'function',
        },
        showForm: {
            control: 'boolean',
            description: 'Controls whether the form is visible',
        },
        setShowForm: {
            action: 'form visibility changed',
            type: 'function',
        },
    },
    args: {
        onAddSection: fn(),
        setShowForm: fn(),
    },
} satisfies Meta<typeof AddSectionButton>;

export default meta;
type Story = StoryObj<typeof meta>;

export const CollapsedLine: Story = {
    args: {
        showForm: false,
    },
};

export const FormExpanded: Story = {
    args: {
        showForm: true,
    },
};
