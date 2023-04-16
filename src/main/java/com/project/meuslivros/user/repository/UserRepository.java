package com.project.meuslivros.user.repository;

import com.project.meuslivros.user.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(
        "" +
            "SELECT CASE WHEN COUNT(u) 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM UserEntity " +
            "WHERE u.email = ?1"
    )
    Boolean selectExistByEmail(String email);

    UserEntity findByEmail(String email);
}
