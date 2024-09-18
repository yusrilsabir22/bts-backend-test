package com.example.btsbackendtest.checklist;

import com.example.btsbackendtest.ChecklistItem.ChecklistItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ChecklistResponseDto {
    private String id;
    private String name;
    private List<ChecklistItemResponseDto> items;
}
