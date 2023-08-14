package com.example.restfull.Domain.Form;

import lombok.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SignupForm {
    private String email;
    private String pw;
    private String name;
    private String contact;

}
