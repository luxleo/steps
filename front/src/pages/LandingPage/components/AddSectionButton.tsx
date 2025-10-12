import { useState } from 'react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Card, CardContent } from '@/components/ui/card';
import { Plus } from 'lucide-react';

interface AddSectionButtonProps {
    onAddSection: (title: string) => void;
    showForm: boolean;
    setShowForm: (show: boolean) => void;
}

export const AddSectionButton = ({ onAddSection, showForm, setShowForm }: AddSectionButtonProps) => {
    const [newSectionTitle, setNewSectionTitle] = useState('');
    const [isHovered, setIsHovered] = useState(false);

    const handleAddSection = () => {
        if (!newSectionTitle.trim()) return;
        onAddSection(newSectionTitle);
        setNewSectionTitle('');
        setShowForm(false);
    };

    if (showForm) {
        return (
            <Card className="shadow-sm border-dashed bg-white dark:bg-neutral-950 border-neutral-200 dark:border-neutral-800">
                <CardContent className="p-4">
                    <div className="flex gap-2">
                        <Input
                            placeholder="New section..."
                            value={newSectionTitle}
                            onChange={(e) => setNewSectionTitle(e.target.value)}
                            onKeyDown={(e) => {
                                if (e.key === 'Enter') handleAddSection();
                                if (e.key === 'Escape') {
                                    setShowForm(false);
                                    setNewSectionTitle('');
                                }
                            }}
                            autoFocus
                            className="text-sm"
                        />
                        <Button size="sm" onClick={handleAddSection}>
                            <Plus className="w-4 h-4 mr-2" />
                            Add Section
                        </Button>
                        <Button 
                            size="sm" 
                            variant="ghost" 
                            onClick={() => {
                                setShowForm(false);
                                setNewSectionTitle('');
                            }}
                        >
                            Cancel
                        </Button>
                    </div>
                </CardContent>
            </Card>
        );
    }

    return (
        <div
            className="relative cursor-pointer py-1.5"
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
            onClick={() => setShowForm(true)}
        >
            <div className="h-px bg-neutral-300 dark:bg-neutral-700" />
            {isHovered && (
                <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 bg-white dark:bg-neutral-950 px-4 py-1.5 rounded-md text-sm font-medium text-neutral-700 dark:text-neutral-300 whitespace-nowrap shadow-sm">
                    Add Section
                </div>
            )}
        </div>
    );
};
