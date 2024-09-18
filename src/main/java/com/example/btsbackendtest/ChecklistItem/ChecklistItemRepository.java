package com.example.btsbackendtest.ChecklistItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, String> {

    @Query("SELECT ci FROM ChecklistItem ci WHERE ci.checklist.id = :checklistId")
    Optional<ChecklistItem> findChecklistItemByChecklist(String checklistId);

}
