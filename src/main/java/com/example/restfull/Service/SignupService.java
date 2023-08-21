package com.example.restfull.Service;

import com.example.restfull.Client.MailgunClient;
import com.example.restfull.Client.SendMailForm;
import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Domain.Model.Member;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor

public class SignupService {
    private final MemberRepos memberRepos;
    private final PasswordEncoder passwordEncoder;
    private final MailgunClient mailgunClient;
    public boolean isMemberExist(String email){
        return memberRepos.findByEmail(email).isPresent();
    }

    //public boolean isEmailVerify()
    private String getRandomCode(){
        return RandomStringUtils.random(10,true,true);
    }

    @Transactional
    public Member Signup(SignupForm signupForm) {
        String encrypt_pw = "";
        String randomCode = "";

        if (isMemberExist(signupForm.getEmail())) {
            throw new MemberException(ErrorCode.ALREADY_EXSISTS_EMAIL);
        } // 중복체크

        else if (!(signupForm.getPw().length() < 8)) {
            Member member = signupForm.from(signupForm);
            encrypt_pw = passwordEncoder.encode(signupForm.getPw());
            member.setPw(encrypt_pw);
            randomCode = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("test@naver.com")
                    .to(signupForm.getEmail())
                    .subjects("이메일 인증 테스트")
                    .text(MailText(signupForm.getEmail(), randomCode))
                    .build();
            mailgunClient.sendEmail(sendMailForm);
            member.setVerification_code(randomCode);
            member.setCertified(false);
            memberRepos.save(member);

            return member;

        }//  패스워드 암호화
        else {
            throw new MemberException(ErrorCode.NOT_VALID_PASSWORD);
        }
    }
    private String MailText(String email, String code) {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.append("Hello\n Please click this URL\n")
                .append("http://localhost:8080/signup/verify/?email=")
                .append(email)
                .append("&code=")
                .append(code)
                .toString();
    }
    @Transactional
    public Boolean verifyMember(String email, String code) {
        Member member = memberRepos.findByEmail(email).get();
        if(member.getVerification_code().equals(code)){
            member.setCertified(true);
            memberRepos.save(member);
            return true;
        }
        else{
            throw new MemberException(ErrorCode.NOT_EQUALS_CODE);
        }
   }
}
