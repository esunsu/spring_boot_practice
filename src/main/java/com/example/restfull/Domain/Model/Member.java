package com.example.restfull.Domain.Model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
    @Column
    @NotBlank
    @Email(message = "이메일 형식을 맞추세요.")
    private String email;
    @Column
    @NotBlank
    @Pattern(regexp="^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$", message = "특수문자 포함한 8글자 이상의 비밀번호를 입력하시오.(사용가능 특수문자 : !@#$%^&*")
    private String pw;
    @Column
    @NotBlank
    private String name;
    @Column
    @NotBlank
    private String contact;
    @Column
    private String verification_code;//인증코드
    @Column
    private boolean certified;//인증됨


}




