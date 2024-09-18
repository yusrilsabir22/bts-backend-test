package com.example.btsbackendtest.checklist;

import com.example.btsbackendtest.ChecklistItem.ChecklistItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/checklist")
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;
    private final ChecklistItemService checklistItemService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('user:read')")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(checklistService.getChecklistsByUser());
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('user:create')")
    public ResponseEntity<?> create(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(checklistService.create(requestDto.getName()));
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('user:remove')")
    public ResponseEntity<?> remove(@PathVariable("id") String id) {
        return ResponseEntity.ok(checklistService.remove(id));
    }

    @GetMapping(path = "/{id}/item")
    public ResponseEntity<?> findAllItem(@PathVariable("id") String id) {
        return ResponseEntity.ok(checklistItemService.findChecklistItem(id));
    }
}
