package com.safonov.demo;

import com.safonov.demo.application.web.dto.NoteDTO;
import com.safonov.demo.application.web.dto.ResponseDAO;
import com.safonov.demo.application.web.dto.UserDTO;
import com.safonov.demo.application.web.facade.NoteControllerFacade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Set;

public class NoteFacadeTest extends GenericTest{
    @Autowired
    private NoteControllerFacade noteFacade;

    private Principal getPrincipal(){
        return () -> "user";
    }

    @Test
    public void createNote(){
        ResponseDAO<NoteDTO> result =
                noteFacade.createNote(
                        getPrincipal(),
                        new NoteDTO()
                                .setName("The first note")
                                .setContent("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
                                            "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim " +
                                            "veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea " +
                                            "commodo consequat. ")
                                .setAuthor(new UserDTO().setId(1L))
        );
        System.out.println(result.toString());
        assert(result.getData() != null && result.getError() == null);
    }

    @Test
    public void getAll(){
        ResponseDAO<Set<NoteDTO>> result =
                noteFacade.getAllNotesForUser(getPrincipal(), "0", "100");
        System.out.println(result.toString());
        assert(result.getData().size() != 0);
    }
}
