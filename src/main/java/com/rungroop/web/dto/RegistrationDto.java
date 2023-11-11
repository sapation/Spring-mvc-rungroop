package com.rungroop.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    @Email(message = "please enter a valid email")
    private String email;
    @NotEmpty
    private String password;
}
