package com.example.btsbackendtest.checklist;

import com.example.btsbackendtest.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checklist")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Getter
    private String id;

    @Column(name="name")
    @Getter
    private String name;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Checklist(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
