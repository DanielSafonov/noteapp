package com.safonov.demo.application.service.impl;

import com.safonov.demo.application.common.enums.ActionEnum;
import com.safonov.demo.application.common.exception.NoteException;
import com.safonov.demo.application.common.exception.PermissionException;
import com.safonov.demo.application.model.entity.Note;
import com.safonov.demo.application.model.entity.Permission;
import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.model.repository.NoteRepository;
import com.safonov.demo.application.model.repository.PermissionRepository;
import com.safonov.demo.application.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

@Slf4j
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Note createNote(User currentUser, Note note) {
        try{
            return noteRepository.save(note);
        } catch (Exception e){
            throw new NoteException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public void deleteNote(User currentUser, Long noteID) {
        try{
            Boolean permissionGranted = checkPermission(currentUser, noteID, ActionEnum.WRITE);

            if(!permissionGranted)
                throw new PermissionException(
                        "Action not allowed! User " + currentUser.getUsername()
                        + " couldn't delete note with id " + noteID
                );

            noteRepository.deleteById(noteID);
        } catch (Exception e){
            throw new NoteException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public Note updateNote(User currentUser, Note note, Set<Permission> permissions) {
        try{
            Boolean permissionGranted = checkPermission(currentUser, note.getId(), ActionEnum.WRITE);

            if(!permissionGranted)
                throw new PermissionException(
                        "Action not allowed! User " + currentUser.getUsername()
                        + " couldn't update note with id " + note.getId()
                );

            noteRepository.save(note);
            //TODO: UPDATE PERMISSIONS - delete, update, create
        } catch (Exception e){
            throw new NoteException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public Note getNoteByID(User currentUser, Long noteID) {
        try{
            Boolean permissionGranted = checkPermission(currentUser, noteID, ActionEnum.READ);

            if(!permissionGranted)
                throw new PermissionException(
                        "Action not allowed! User " + currentUser.getUsername()
                        + " couldn't view note with id " + noteID
                );

            return noteRepository.findById(noteID).get();
        } catch (Exception e){
            throw new NoteException(e.getMessage(), currentUser.getUsername());
        }
    }

    @Override
    public Set<Note> getAllNotesForUser(User currentUser, Pageable pageable) {
        try{
            return noteRepository.findAllForUser(currentUser.getId(), pageable);
        } catch (Exception e){
            throw new NoteException(e.getMessage(), currentUser.getUsername());
        }
    }

    /**
     * Проверить полномочия пользователя на выполнения действия в отношении записи
     * @param currentUser
     * @param noteID
     * @param actionEnum
     * @return
     */
    private Boolean checkPermission(User currentUser, Long noteID, ActionEnum actionEnum){
        if(noteID.equals(currentUser.getId()))
            return true;

        Optional<Permission> permission = permissionRepository.findByNoteIdAndUserIdOrForAll(noteID, currentUser.getId());
        if(permission.isEmpty())
            return false;

        switch (actionEnum){
            case READ:
                return permission.get().getReadPermission();
            case WRITE:
                return permission.get().getWritePermission();
            default:
                return false;
        }
    }
}
