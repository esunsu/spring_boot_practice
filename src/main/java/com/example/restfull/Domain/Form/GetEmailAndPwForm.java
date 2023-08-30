package com.example.restfull.Domain.Form;

import com.example.restfull.Domain.Entity.Member;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEmailAndPwForm {
    private String email;
    private String pw;

    public static Member from (GetEmailAndPwForm from){
        return Member.builder()
                .email(from.getEmail())
                .pw(from.getPw())
                .build();
    }
}
