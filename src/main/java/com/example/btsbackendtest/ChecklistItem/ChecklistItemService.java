package com.example.btsbackendtest.ChecklistItem;

import com.example.btsbackendtest.checklist.Checklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChecklistItemService {

    private final ChecklistItemRepository checklistItemRepository;

    public Optional<ChecklistItem> findChecklistItem(String checklistId) {
        return checklistItemRepository.findChecklistItemByChecklist(checklistId);
    }

    public boolean createChecklistItem(String name, Checklist checklist) {
        var checklistItem = new ChecklistItem(name, checklist);
        return true;
    }
}
