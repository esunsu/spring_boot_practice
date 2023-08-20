package com.example.restfull.Domain.Form;

import com.example.restfull.Domain.Model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LogInForm {
    private String email;
    private String pw;

    public static Member from (LogInForm from){
        return Member.builder()
                .email(from.getEmail())
                .pw(from.getPw())
                .build();
    }
}
