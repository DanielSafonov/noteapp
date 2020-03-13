package com.safonov.demo.application.model.repository;

import com.safonov.demo.application.common.Constants;
import com.safonov.demo.application.model.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Transactional(readOnly = true)
    @Query(value = "select * from " + Constants.DB_SCHEMA + ".PERMISSION where" +
                   "    (note_id = :noteID and user_id = :userID ) " +
                   "    or (note_id = :noteID and PERMIT_ALL = TRUE)", nativeQuery = true)
    Optional<Permission> findByNoteIdAndUserIdOrForAll(Long noteID, Long userID);
}