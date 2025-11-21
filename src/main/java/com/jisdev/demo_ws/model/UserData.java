package com.jisdev.demo_ws.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserData {

    @NotNull(message = "First name cannot be null")
    String firstName;
    @NotNull(message = "Last name cannot be null")
    String lastName;
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    String email;
    @NotNull(message = "Password must have between 8 and 16 characters")
    @Size(min = 8, max = 16)
    String password;

}
