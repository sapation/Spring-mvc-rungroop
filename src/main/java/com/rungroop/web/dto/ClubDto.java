package com.rungroop.web.dto;


import com.rungroop.web.models.Event;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.security.cert.CertPathBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ClubDto {
    private long id;
    @NotEmpty(message = "Club title should not be empty")
    private String title;
    @NotEmpty(message = "Photo link should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> events;
}
