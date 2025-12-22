package com.user_service.user_service.repository;

import com.user_service.user_service.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(
            "UPDATE UserEntity u " +
                    "SET u.username = COALESCE(:username, u.username),"+
                    "u.email = COALESCE(:email, u.email), " +
                    "u.role = COALESCE(:role, u.role), " +
                    "u.password_hash = COALESCE(:password , u.password_hash)," +
                    "u.updated_at = CURRENT_TIMESTAMP " +
                    "WHERE u.Id = :id"
    )
    int updateUser(
            @Param("id") Integer id,
            @Param("username") String username,
            @Param("email") String email,
            @Param("role") UserEntity.Role role,
            @Param("password") String password
    );
}
