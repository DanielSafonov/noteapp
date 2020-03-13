package com.safonov.demo.application.web.facade.impl;

import com.safonov.demo.application.web.dto.NoteDTO;
import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.facade.NoteControllerFacade;
import lombok.extern.slf4j.Slf4j;

import java.security.Principal;
import java.util.Set;

@Slf4j
public class NoteControllerFacadeImpl implements NoteControllerFacade {
    /**
     * Создать запись
     *
     * @param principal
     * @param noteDTO
     * @return ResponseDAO<NoteDTO>
     */
    @Override
    public ResponseDAO<NoteDTO> createNote(Principal principal, NoteDTO noteDTO) {
        return null;
    }

    /**
     * Удалить запись
     *
     * @param principal
     * @param noteDTO
     * @return ResponseDAO
     */
    @Override
    public ResponseDAO deleteNote(Principal principal, NoteDTO noteDTO) {
        return null;
    }

    /**
     * Изменить содержимое записи или правила доступа к ней
     *
     * @param principal
     * @param noteDTO
     * @return ResponseDAO<NoteDTO>
     */
    @Override
    public ResponseDAO<NoteDTO> updateNote(Principal principal, NoteDTO noteDTO) {
        return null;
    }

    /**
     * Получить запись по идентификатору
     *
     * @param principal
     * @param noteID
     * @return ResponseDAO<NoteDTO>
     */
    @Override
    public ResponseDAO<NoteDTO> getNoteByID(Principal principal, String noteID) {
        return null;
    }

    /**
     * Получить все записи для пользователя постранично
     *
     * @param principal
     * @param page
     * @param pageSize
     * @return ResponseDAO<Set<NoteDTO>>
     */
    @Override
    public ResponseDAO<Set<NoteDTO>> getAllNotesForUser(Principal principal, String page, String pageSize) {
        return null;
    }
}
