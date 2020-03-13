package com.safonov.demo.application.web.converter;

import com.safonov.demo.application.model.entity.Note;
import com.safonov.demo.application.web.dto.NoteDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class NoteConverter {
    public static NoteDTO toDTO(Note note){
        return new NoteDTO()
                .setId(note.getId())
                .setName(note.getName())
                .setContent(note.getContent())
                .setAuthor(UserConverter.toDTO(note.getAuthor()));
    }

    public static Note toEntity(NoteDTO noteDTO){
        return new Note()
                .setId(noteDTO.getId())
                .setName(noteDTO.getName())
                .setContent(noteDTO.getContent())
                .setAuthor(UserConverter.toEntity(noteDTO.getAuthor()));
    }

    public static Set<NoteDTO> toDTO(Set<Note> notes){
        return notes
                .stream()
                .map(NoteConverter::toDTO)
                .collect(Collectors.toSet());
    }
}
