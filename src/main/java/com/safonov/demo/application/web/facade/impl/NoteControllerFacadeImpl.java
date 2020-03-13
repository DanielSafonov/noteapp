package com.safonov.demo.application.web.facade.impl;

import com.safonov.demo.application.model.entity.Note;
import com.safonov.demo.application.model.entity.User;
import com.safonov.demo.application.service.impl.NoteServiceImpl;
import com.safonov.demo.application.service.impl.UserServiceImpl;
import com.safonov.demo.application.web.converter.NoteConverter;
import com.safonov.demo.application.web.dto.NoteDTO;
import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.facade.NoteControllerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.security.Principal;
import java.util.Set;

@Slf4j
@Component
public class NoteControllerFacadeImpl implements NoteControllerFacade {
    @Autowired
    private NoteServiceImpl noteService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * Создать запись
     *
     * @param principal
     * @param noteDTO
     * @return ResponseDAO<NoteDTO>
     */
    @Transactional
    @Override
    public ResponseDAO<NoteDTO> createNote(Principal principal, NoteDTO noteDTO) {
        try{
            User noteAuthor = userService
                    .getUserByID(new User().setUsername(principal.getName()), noteDTO.getAuthor().getId()); //TODO: костыль
            noteService.createNote(
                    new User().setUsername(principal.getName()),
                    NoteConverter.toEntity(noteDTO)
                            .setAuthor(noteAuthor)
            );
            return ResponseDAO.buildSuccessResponse(noteDTO);
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(noteDTO, e.getMessage(), 500);
        }
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
        try{
            noteService.deleteNote(
                    new User().setUsername(principal.getName()),
                    noteDTO.getId()
            );
            return ResponseDAO.buildSuccessResponse(noteDTO);
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(noteDTO, e.getMessage(), 500);
        }
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
        try{
            noteService.updateNote(
                    new User().setUsername(principal.getName()),
                    NoteConverter.toEntity(noteDTO),
                    null//FIXME:
            );
            return ResponseDAO.buildSuccessResponse(noteDTO);
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(noteDTO, e.getMessage(), 500);
        }
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
        try{
            Note note = noteService.getNoteByID(
                    new User().setUsername(principal.getName()),
                    Long.parseLong(noteID)
            );
            return ResponseDAO.buildSuccessResponse(NoteConverter.toDTO(note));
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(e.getMessage(), 500);
        }
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
        try{
            Set<Note> notes = noteService.getAllNotesForUser(
                    userService.getUserByUsername(new User().setUsername(principal.getName()), principal.getName()),
                    PageRequest.of(Integer.parseInt(page), Integer.parseInt(pageSize))
            );
            return ResponseDAO.buildSuccessResponse(NoteConverter.toDTO(notes));
        } catch (Exception e){
            //TODO: Exception Handling
            return ResponseDAO.buildErrorResponse(e.getMessage(), 500);
        }
    }
}
