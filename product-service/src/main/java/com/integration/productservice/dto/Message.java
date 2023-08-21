package com.integration.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    private String title;
    private String description;
}
