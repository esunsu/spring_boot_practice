package com.example.restfull.Domain.Model;

import com.example.restfull.Domain.Form.SignupForm;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    private String email;
    private String pw;
    private String name;
    private String contact;

    public static Member from (SignupForm from){
        return Member.builder()
                .email(from.getEmail())
                .pw(from.getPw())
                .name(from.getName())
                .contact(from.getContact())
                .build();
    }
}




