package com.example.restfull.Domain.Form;

import com.example.restfull.Domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberForm {
    private String modifiedPw;
    private String modifiedName;
    private String modifiedContact;

    public static Member from (SignupForm from){
        return Member.builder()
                .pw(from.getPw())
                .name(from.getName())
                .contact(from.getContact())
                .build();
    }

}
