package com.user_service.user_service.repository;

import com.user_service.user_service.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {

    @Query("""
        SELECT t
        FROM TokenEntity t
        INNER JOIN t.user u
        WHERE t.token = :token
          AND t.expired = false
          AND t.revoked = false
    """)
    Optional<TokenEntity> findValidToken(String token);

    @Query("""
        SELECT t
        FROM TokenEntity t
        INNER JOIN t.user u
        WHERE u.id = :userId
          AND t.expired = false
          AND t.revoked = false
    """)
    List<TokenEntity> findAllValidTokens(Integer userId);

}
