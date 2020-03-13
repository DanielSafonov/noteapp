package com.safonov.demo.application.service;

import com.safonov.demo.application.model.entity.Note;
import com.safonov.demo.application.model.entity.Permission;
import com.safonov.demo.application.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface NoteService {
    Note createNote(User currentUser, Note note);
    void deleteNote(User currentUser, Long noteID);
    Note updateNote(User currentUser, Note note, Set<Permission> permissions);
    Note getNoteByID(User currentUser, Long noteID);
    Set<Note> getAllNotesForUser(User currentUser, Pageable pageable);
}
