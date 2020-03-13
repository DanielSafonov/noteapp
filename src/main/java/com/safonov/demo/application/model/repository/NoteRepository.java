package com.safonov.demo.application.model.repository;

import com.safonov.demo.application.model.entity.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("select n from Note n where n.author.id = :userID " +
           " or n.id in (select p.note.id from Permission p " +
           "    where p.user.id = :userID or p.permitAll = true)")
    Page<Note> findAllForUser(Long userID, Pageable pageable);
}
