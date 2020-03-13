package com.safonov.demo.application.web.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * DTO для передачи сообщения об ошибке
 */
@Accessors(chain = true)
@Data
public class ErrorMessageResponseDTO extends BaseDTO {
    @JsonProperty("message")
    private String message; //Текст сообщения об ошибке
    @JsonProperty("code")
    private Integer code; //Код ошибки
}
