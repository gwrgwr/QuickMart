package com.example.quickmart.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    public UserEntity(String name, String email, String username, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @PrePersist
    public void prePersist() {
        setTimestamps();
        encryptPassword();
    }

    private void setTimestamps() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    private void encryptPassword() {
        if (this.password != null) {
            this.password = new BCryptPasswordEncoder().encode(this.password);
        }
    }


    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDate.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (role) {
            case ADMIN -> List.of(
                    () -> "SCOPE_ADMIN",
                    () -> "SCOPE_CLIENT",
                    () -> "SCOPE_SELLER");
            case CLIENT -> List.of(
                    () -> "SCOPE_CLIENT"
            );
            case SELLER -> List.of(
                    () -> "SCOPE_SELLER");
        };
    }

    @Override
    public String getUsername() {
        return username;
    }
}
