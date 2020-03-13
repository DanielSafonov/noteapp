package com.safonov.demo.application.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NoteDTO extends BaseDTO{
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("author")
    private UserDTO author;
}
