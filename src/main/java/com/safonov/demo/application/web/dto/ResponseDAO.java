package com.safonov.demo.application.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Общая обертка для всех ответов в контроллерах
 */
@Accessors(chain = true)
@Data
public class ResponseDAO<T> extends BaseDTO {
    @JsonProperty("data")
    private T data;
    @JsonProperty("error")
    private ErrorMessageResponseDTO error;

    private ResponseDAO(T data) {
        this.data = data;
    }
    private ResponseDAO(){}
    public static <T> ResponseDAO<T> buildSuccessResponse(T data){
        return new ResponseDAO<>(data);
    }
    public static <T> ResponseDAO<T> buildSuccessResponse(){
        return new ResponseDAO<>();
    }

    public static <T> ResponseDAO<T> buildErrorResponse(T data, String errorMessage, Integer errorCode){
        return new ResponseDAO<>(data).setError(
                new ErrorMessageResponseDTO().setMessage(errorMessage).setCode(errorCode)
        );
    }
    public static <T> ResponseDAO<T> buildErrorResponse(String errorMessage, Integer errorCode){
        return new ResponseDAO<T>().setError(
                new ErrorMessageResponseDTO().setMessage(errorMessage).setCode(errorCode)
        );
    }
}
