package com.jwt.application.authentication;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.jwt.application.util.validation.UniquePhoneNumber;
import com.jwt.application.util.validation.UniqueUserEmail;
import com.jwt.application.util.validation.UniqueUsername;
import com.jwt.application.util.validation.ValidateUserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    @NotEmpty(message = "First Name is required.")
    @Size(min = 3,max = 20,message = "First Name must be between 2 nad 20 symbols.")
    private String firstName;
    @NotEmpty(message = "Last Name is required.")
    @Size(min = 3,max = 20,message = "Last Name must be between 2 nad 20 symbols.")
    private String lastName;
    @NotEmpty(message = "User email should be provided.")
    @Email(message = "User email should be valid.")
    @UniqueUserEmail(message = "User email is already taken!.")
    private String email;
    @NotEmpty(message = "Password is required.")
    @Size(min = 5,message = "Password must be at least 5 symbols.")
    private String password;
    @NotEmpty(message = "Username can't be empty.")
    @Size(min = 2,max = 20,message = "Must be between 2 and 20 symbols.")
    @UniqueUsername(message = "Username is already taken.")
    private String username;
    @NotEmpty(message = "Phone is required.")
    @Pattern(regexp = "[0-9]\\d{1,20}",message = "Phone is invalid.")
    @UniquePhoneNumber(message = "Phone number is already taken!")
    private String phone;
    @Min(value = 10,message = "Minimum age is 10.")
    @Max(value = 99,message = "Maximum age is 99.")
    private Integer age;
    @NotEmpty(message = "Role is required.")
    @ValidateUserRole(message = "User role must be 'User' or 'Admin'")
    private String role;

    @JsonSetter("role")
    public void setRole(String role) {
        this.role = role != null ? role.toUpperCase() : null;
    }
}