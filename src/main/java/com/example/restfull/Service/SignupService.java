package com.example.restfull.Service;

import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Domain.Model.Member;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SignupService {
    private final MemberRepos memberRepos;
    private final PasswordEncoder passwordEncoder;


    public boolean isMemberExist(String email){
        return memberRepos.findByEmail(email).isPresent();
    }

    public Member Signup(SignupForm signupForm){
        String encrypt_pw = "" ;
        if(isMemberExist(signupForm.getEmail())){
            throw new MemberException(ErrorCode.ALREADY_EXSISTS_EMAIL);
        } // 중복체크
        else if(!(signupForm.getPw().length() <8)){
            //  패스워드 암호화
            Member member = signupForm.from(signupForm);
            encrypt_pw = passwordEncoder.encode(signupForm.getPw());
            member.setPw(encrypt_pw);
            return memberRepos.save(member);
        }else{
            throw new MemberException(ErrorCode.NOT_VALID_PASSWORD);
        }
    }

}
