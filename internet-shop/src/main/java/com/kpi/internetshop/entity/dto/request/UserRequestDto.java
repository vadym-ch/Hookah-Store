package com.kpi.internetshop.entity.dto.request;

import com.kpi.internetshop.annotation.EmailConstraint;
import com.kpi.internetshop.annotation.PasswordConstraint;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.validation.constraints.NotNull;

public class UserRequestDto {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @EmailConstraint
    @Unique
    private String email;
    @NotNull
    @PasswordConstraint
    private String password;

    public UserRequestDto(){
    }

    public UserRequestDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
