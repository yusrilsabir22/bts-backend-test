package com.example.btsbackendtest.checklist;


import com.example.btsbackendtest.user.User;
import com.example.btsbackendtest.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChecklistService {

    private final ChecklistRepository checklistRepository;

    public Optional<Checklist> getChecklist(String id) {
        return checklistRepository.findChecklistById(id);
    }

    public List<Checklist> getChecklistsByUser() {
        var session = Utils.getUserSession();
        // should be return UserResponseDTO
        return checklistRepository.findChecklistsByUser(session);
    }

    public String create(String name) {
        var session = Utils.getUserSession();
        var newCheck = checklistRepository.save(new Checklist(name, session));
        return "ok";
    }

    public String remove(String id) {
        var session = Utils.getUserSession();
        checklistRepository.removeChecklistById(id, session);
        return "ok";
    }
}
