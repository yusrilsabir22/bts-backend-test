package com.example.btsbackendtest.checklist;

import com.example.btsbackendtest.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChecklistRepository extends JpaRepository<Checklist, String> {

    @Query("SELECT c FROM Checklist c WHERE c.id = :id")
    Optional<Checklist> findChecklistById(String id);

    @Query("SELECT c FROM Checklist c WHERE c.user = :user")
    List<Checklist> findChecklistsByUser(User user);

    @Query("DELETE FROM Checklist c WHERE c.id = :id AND c.user = :user")
    void removeChecklistById(String id, User user);

    List<Checklist> findByUserId(String userId);
}
