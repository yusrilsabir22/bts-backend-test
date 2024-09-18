package com.example.btsbackendtest.ChecklistItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistItemResponseDto {
    private String id;
    private String name;
    private Boolean status;
}
