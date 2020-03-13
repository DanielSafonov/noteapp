package com.safonov.demo.application.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO extends BaseDTO {
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("Username")
    private String username;

    @JsonProperty("Password")
    private String password;
}
