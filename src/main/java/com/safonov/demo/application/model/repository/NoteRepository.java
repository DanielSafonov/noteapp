package com.safonov.demo.application.model.repository;

import com.safonov.demo.application.common.Constants;
import com.safonov.demo.application.model.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Transactional(readOnly = true)
    @Query(value = "select * from " + Constants.DB_SCHEMA + ".NOTE where AUTHOR_ID = ?1 " +
                   "    or ID in (select NOTE_ID from " + Constants.DB_SCHEMA + ".PERMISSION " +
                   "        where (USER_ID = ?1 and READ = TRUE) or PERMIT_ALL = TRUE)", nativeQuery = true)
    Page<Note> findAllForUser(Long userID, Pageable pageable);
}
