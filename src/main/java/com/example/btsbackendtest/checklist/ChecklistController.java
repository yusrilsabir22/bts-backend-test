package com.example.btsbackendtest.checklist;

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

    @DeleteMapping()
    @PreAuthorize("hasAnyAuthority('user:remove')")
    public ResponseEntity<?> remove(@RequestParam("id") String id) {
        return ResponseEntity.ok(checklistService.remove(id));
    }
}
