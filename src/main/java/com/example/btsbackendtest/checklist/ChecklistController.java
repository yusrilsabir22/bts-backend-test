package com.example.btsbackendtest.checklist;

import com.example.btsbackendtest.ChecklistItem.ChecklistItemRequestDto;
import com.example.btsbackendtest.ChecklistItem.ChecklistItemService;
import jakarta.validation.Valid;
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

    @PostMapping(path="/{id}/item")
    public ResponseEntity<?> createItem(
            @PathVariable("id") String id,
            @RequestBody @Valid ChecklistItemRequestDto body
    ) {
        var currentChecklist = checklistService.getChecklist(id);
        if(currentChecklist.isEmpty()) {
            return ResponseEntity.badRequest().body("Bad Request");
        }
        return ResponseEntity.ok(checklistItemService.createChecklistItem(body.getName(), currentChecklist.get()));
    }
}
