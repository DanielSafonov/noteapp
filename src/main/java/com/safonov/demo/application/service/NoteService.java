package com.safonov.demo.application.service;

import com.safonov.demo.application.model.entity.Note;
import com.safonov.demo.application.model.entity.Permission;
import com.safonov.demo.application.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface NoteService {
    Note createNote(User user, Note note);
    void deleteNote(User user, Long noteID);
    Note updateNote(User user, Note note, Set<Permission> permissions);
    Note getNoteByID(User user, Long noteID);
    Set<Note> getAllNotesForUser(User user, Pageable pageable);
}
