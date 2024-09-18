package com.example.btsbackendtest.checklist;

import com.example.btsbackendtest.ChecklistItem.ChecklistItem;
import com.example.btsbackendtest.ChecklistItem.ChecklistItemRequestDto;
import com.example.btsbackendtest.ChecklistItem.ChecklistItemResponseDto;
import com.example.btsbackendtest.ChecklistItem.ChecklistItemService;
import com.example.btsbackendtest.models.GenericResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/checklist")
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;
    private final ChecklistItemService checklistItemService;

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('user:read')")
    public ResponseEntity<GenericResponse> findAll() {
        var results = checklistService.getChecklistsByUser();
        return ResponseEntity.ok(GenericResponse.success(results));
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('user:create')")
    public ResponseEntity<GenericResponse> create(@RequestBody RequestDto requestDto) {
        var result = checklistService.create(requestDto.getName());
        return ResponseEntity.ok(GenericResponse.success(result));
    }

    @DeleteMapping(path = "/{checklistId}")
    @PreAuthorize("hasAnyAuthority('user:remove')")
    public ResponseEntity<?> remove(@PathVariable("checklistId") String id) {
        return ResponseEntity.ok(checklistService.remove(id));
    }

    @GetMapping(path = "/{checklistId}/item")
    public GenericResponse<List<ChecklistItemResponseDto>> findAllItem(@PathVariable("checklistId") String id) {
        var items = checklistItemService.findChecklistItem(id);
        return GenericResponse.success(items);
    }

    @PostMapping(path="/{checklistId}/item")
    public ResponseEntity<GenericResponse> createItem(
            @PathVariable("checklistId") String id,
            @RequestBody @Valid ChecklistItemRequestDto body
    ) {
        var currentChecklist = checklistService.getChecklist(id);
        if(currentChecklist.isEmpty()) {
            return ResponseEntity.badRequest().body(GenericResponse.error(HttpStatus.BAD_REQUEST.value()));
        }
        var result = checklistItemService.createChecklistItem(body.getName(), currentChecklist.get());
        return ResponseEntity.ok(GenericResponse.success(result));
    }

    @GetMapping(path="/{checklistId}/item/{checklistItemId}")
    public ResponseEntity<GenericResponse> getItemById(
            @PathVariable("checklistId") String id,
            @PathVariable("checklistItemId") String itemId
    ) throws Exception {

        var result = checklistItemService.findChecklistItemById(id, itemId);
        return ResponseEntity.ok(GenericResponse.success(result));
    }

    @PutMapping(path="/{checklistId}/item/{checklistItemId}")
    public ResponseEntity<GenericResponse> updateStatusItemId(
            @PathVariable("checklistId") String id,
            @PathVariable("checklistItemId") String itemId
    ) {
        checklistItemService.updateStatusChecklistItem(id, itemId);
        return ResponseEntity.ok(GenericResponse.success("success"));
    }
}
