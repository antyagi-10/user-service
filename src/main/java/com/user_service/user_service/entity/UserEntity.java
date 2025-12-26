package com.user_service.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class UserEntity {

    public enum Role{
        Admin,
        Employee,
        Engineer
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" , nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Email
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @OneToMany(mappedBy = "user",  cascade = CascadeType.ALL)
    private List<TokenEntity> token;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
