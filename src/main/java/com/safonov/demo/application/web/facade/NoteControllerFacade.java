package com.safonov.demo.application.web.facade;

import com.safonov.demo.application.web.dto.NoteDTO;
import com.safonov.demo.application.web.dto.ResponseDAO;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Set;

public interface NoteControllerFacade {
    /**
     * Создать запись
     * @param principal
     * @param noteDTO
     * @return NoteDTO
     */
    ResponseDAO<NoteDTO> createNote(Principal principal, NoteDTO noteDTO);

    /**
     * Удалить запись
     * @param principal
     * @param noteDTO
     */
    ResponseDAO deleteNote(Principal principal, NoteDTO noteDTO);

    /**
     * Изменить содержимое записи или правила доступа к ней
     * @param principal
     * @param noteDTO
     * @return NoteDTO
     */
    ResponseDAO<NoteDTO> updateNote(Principal principal, NoteDTO noteDTO);

    /**
     * Получить запись по идентификатору
     * @param principal
     * @param noteID
     * @return NoteDTO
     */
    ResponseDAO<NoteDTO> getNoteByID(Principal principal, String noteID);

    /**
     * Получить все записи для пользователя постранично
     * @param principal
     * @param page
     * @param pageSize
     * @return Set<NoteDTO>
     */
    ResponseDAO<Set<NoteDTO>> getAllNotesForUser(Principal principal, String page, String pageSize);
}
