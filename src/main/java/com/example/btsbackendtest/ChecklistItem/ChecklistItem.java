package com.example.btsbackendtest.ChecklistItem;

import com.example.btsbackendtest.checklist.Checklist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checklist_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChecklistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "item_id")
    @ManyToOne
    private Checklist checklist;

    public ChecklistItem(String name, Checklist checklist) {
        this.name = name;
        this.checklist = checklist;
    }
}
