package com.microservice.priceandinventoryservice.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class InternalApiResponse<T> {
    private Message message;
    private T payload;
    private boolean hasError;
    private List<String> errorMessages;
    private HttpStatus httpStatus;
}

