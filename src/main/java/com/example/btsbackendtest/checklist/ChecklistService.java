package com.example.btsbackendtest.checklist;


import com.example.btsbackendtest.ChecklistItem.ChecklistItemRepository;
import com.example.btsbackendtest.ChecklistItem.ChecklistItemResponseDto;
import com.example.btsbackendtest.user.User;
import com.example.btsbackendtest.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;
    private final ChecklistItemRepository checklistItemRepository;

    public Optional<Checklist> getChecklist(String id) {
        return checklistRepository.findChecklistById(id);
    }

    public List<ChecklistResponseDto> getChecklistsByUser() {
        var session = Utils.getUserSession();
        // should be return UserResponseDTO
        var checklists = checklistRepository.findByUserId(session.getId());

        return checklists.stream()
                .map(item -> {
                    System.out.println("ITEM: " + item.getId()+"."+item.getName());
                    var items = this.checklistItemRepository.findChecklistItemByChecklist(item.getId()).stream().map(
                            subItem -> {
                                System.out.println("ITEM: " + subItem.getId()+"."+subItem.getName()+"."+subItem.isStatus());
                                return new ChecklistItemResponseDto(subItem.getId(), subItem.getName(), subItem.isStatus());
                            }
                    ).collect(Collectors.toList());
                    return new ChecklistResponseDto(item.getId(), item.getName(), items);
                }).collect(Collectors.toList());
    }

    public ChecklistResponseDto create(String name) {
        var session = Utils.getUserSession();
        var newCheck = checklistRepository.save(new Checklist(name, session));
        return new ChecklistResponseDto(newCheck.getId(), newCheck.getName(), new ArrayList<>());
    }

    public String remove(String id) {
        var session = Utils.getUserSession();
        checklistRepository.deleteById(id);
        return "ok";
    }
}
