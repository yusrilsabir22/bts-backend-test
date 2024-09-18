package com.example.btsbackendtest.ChecklistItem;

import com.example.btsbackendtest.checklist.Checklist;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChecklistItemService {

    private final ChecklistItemRepository checklistItemRepository;

    public List<ChecklistItemResponseDto> findChecklistItem(String checklistId) {
        return checklistItemRepository.findChecklistItemByChecklist(checklistId).stream()
                .map(checklistItem -> new ChecklistItemResponseDto(checklistItem.getId(), checklistItem.getName(), checklistItem.isStatus())).toList();

    }

    public ChecklistItemResponseDto findChecklistItemById(String checklistId, String checklistItemId) throws Exception {
        var item = checklistItemRepository.findByChecklistByCheckItemId(checklistId, checklistItemId);
        if(item.isEmpty()) {
            throw new Exception("Checklist item with id " + checklistItemId + " not found");
        }
        return new ChecklistItemResponseDto(item.get().getId(), item.get().getName(), item.get().isStatus());
    }

    public void updateStatusChecklistItem(String checklistId, String checklistItemId) {
        checklistItemRepository.updateChecklistItemStatus(checklistId, checklistItemId);
    }

    public boolean createChecklistItem(String name, Checklist checklist) {
        var checklistItem = new ChecklistItem(name, checklist);
        checklistItemRepository.save(checklistItem);
        return true;
    }
}
