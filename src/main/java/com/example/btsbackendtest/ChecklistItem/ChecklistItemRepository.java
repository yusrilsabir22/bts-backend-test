package com.example.btsbackendtest.ChecklistItem;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, String> {

    @Query("SELECT ci FROM ChecklistItem ci WHERE ci.checklist.id = :checklistId")
    List<ChecklistItem> findChecklistItemByChecklist(String checklistId);

    @Query("SELECT ci FROM ChecklistItem ci WHERE ci.checklist.id = :checklistId AND ci.id = :checklistItemId")
    Optional<ChecklistItem> findByChecklistByCheckItemId(String checklistId, String checklistItemId);

    @Modifying
    @Transactional
    @Query("UPDATE ChecklistItem ci SET ci.status = false WHERE ci.checklist.id = :checklistId AND ci.id = :checklistItemId")
    void updateChecklistItemStatus(String checklistId, String checklistItemId);
}
