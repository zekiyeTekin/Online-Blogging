package com.zekiyetekin.onlineblogging.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zekiyetekin.onlineblogging.enumuration.ResponseMessageEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {

    private Integer code;

    private String codeMessage;

    private Boolean success;

    @Enumerated(EnumType.STRING)
    private ResponseMessageEnum message;

    private T data;
}
