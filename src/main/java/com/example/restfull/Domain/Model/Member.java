package com.example.restfull.Domain.Model;

import com.example.restfull.Domain.Form.SignupForm;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    @NotNull
    @Email(message = "이메일 형식을 맞추세요.")
    private String email;
    @NotNull
    private String pw;
    @NotNull
    private String name;
    @NotNull
    private String contact;
    private String verification_code;//인증코드
    private boolean certified;//인증됨


}




