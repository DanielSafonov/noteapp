package com.safonov.demo.application.model.repository;

import com.safonov.demo.application.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query("select p from Permission p where (p.note.id = :noteID and p.user.id = :userID ) " +
           "or (p.note.id = :noteID and p.permitAll = TRUE) ")
    Optional<Permission> findByNoteIdAndUserIdOrForAll(Long noteID, Long userID);
}
