package com.library.bookforyou.web.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class userDTO {
    @NotBlank(message = "{firstName.notBlank}")
    private String firstName;

    @NotBlank(message = "{lastName.notBlank}")
    private String lastName;

    @NotBlank(message = "{email.empty}")
    @Email(message = "{email.error}")
    private String email;

    @NotBlank(message = "{password.notBlank}")
    @Size(min=4, max = 8, message = "{password.size}")
    private String password;

    @NotBlank(message = "{password.confirmationNotBlank}")
    @Size(min=4, max = 8, message = "{password.size}")
    private String confirmPassword;
}
