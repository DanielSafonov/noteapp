package com.safonov.demo.application.web.controller;

import com.safonov.demo.application.common.Constants;
import com.safonov.demo.application.common.aspects.logger.Loggable;
import com.safonov.demo.application.web.dto.NoteDTO;
import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.facade.NoteControllerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

/**
 * Контроллер для работы с записями и правами доступа к ним
 */
@RequestMapping("/note")
@RestController
public class NoteController {
    @Autowired
    private NoteControllerFacade noteControllerFacade;

    @Loggable
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO<NoteDTO> createNote(
            Principal principal,
            @RequestBody NoteDTO noteDTO
    ){
        return noteControllerFacade.createNote(principal, noteDTO);
    }

    @Loggable
    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE,
                   produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO deleteNote(
            Principal principal,
            @RequestBody NoteDTO noteDTO //FIXME: Или тут должен быть PathVariable?
    ){
        return noteControllerFacade.deleteNote(principal, noteDTO);
    }

    @Loggable
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO<NoteDTO> updateNote(
            Principal principal,
            @RequestBody NoteDTO noteDTO
    ){
        return noteControllerFacade.updateNote(principal, noteDTO);
    }

    @Loggable
    @GetMapping(value = "/get/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDAO<NoteDTO> getNoteByID(
            Principal principal,
            @PathVariable(name = "id") String id
    ){
        return noteControllerFacade.getNoteByID(principal, id);
    }

    @Loggable
    @GetMapping(value = "/get/all", consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE) //FIXME: Как правильно назвать маппинг?
    public ResponseDAO<Set<NoteDTO>> getAllNotesForUser(
            Principal principal,
            @RequestParam(name = "page", defaultValue = "1") String page,
            @RequestParam(name = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) String pageSize
    ){
        return noteControllerFacade.getAllNotesForUser(principal, page, pageSize);
    }
}
