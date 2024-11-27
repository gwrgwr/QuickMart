package com.example.quickmart.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String id;

    private String name;

    private String user;

    private String password;

    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(role.getRole(), "CLIENT")) {
            return List.of(() -> "SCOPE_CLIENT");
        }
        if(Objects.equals(role.getRole(), "SELLER")) {
            return List.of(() -> "SCOPE_SELLER");
        }
        return List.of(() -> "SCOPE_GUEST");
    }

    @Override
    public String getUsername() {
        return "";
    }
}
