package com.example.restfull.Domain.Model;

import com.example.restfull.Domain.Form.SignupForm;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotBlank
    @Email(message = "이메일 형식을 맞추세요.")
    private String email;
    @NotBlank
    @Pattern(regexp="(?=.*\\W)(?=\\S+$)", message = "특수문자 포함한 8글자 이상의 비밀번호를 입력하시오.")
    private String pw;
    @NotBlank
    private String name;
    @NotBlank
    private String contact;
    private String verification_code;//인증코드
    private boolean certified;//인증됨


}




