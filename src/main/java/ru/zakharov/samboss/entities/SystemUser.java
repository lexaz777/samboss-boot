package ru.zakharov.samboss.entities;


import ru.zakharov.samboss.validation.FieldMatch;
import ru.zakharov.samboss.validation.ValidEmail;

import javax.validation.constraints.*;
import java.util.Collection;

@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SystemUser {
    @NotNull(message = "not null check")
    @Size(min = 3, message = "username length must be greater than 2 symbols")
//    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 letters/digits")
    private String userName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String email;

    @NotNull(message = "is required")
    @Size(min = 4, message = "size incorrect")
    private String role;

//    @NotNull
//    @Min(value = 0, message = "value must be greater or equals than 0")
//    @Max(value = 10, message = "value must be lesser or equals than 10")
//    private Integer count;

    public SystemUser() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
