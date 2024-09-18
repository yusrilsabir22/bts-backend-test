package com.example.btsbackendtest.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @Getter
    private String id;

    @Column(unique = true)
    @Getter
    @Setter
    private String email;

    @Column(unique = true)
    @Setter
    private String username;

    @Column
    @Setter
    private String password;

    @Getter
    @Enumerated(EnumType.STRING)
    private Profile profile;

    public User() {}

    public User(String email, String username, String password) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.profile = Profile.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profile.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
