package com.example.restfull.Service;

import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Domain.Model.Member;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SignupService {
    private final MemberRepos memberRepos;

    public boolean isMemberExist(String email, String pw){
        return memberRepos.findByEmailAndPw(email, pw).isPresent();
    }
    public Member Signup(SignupForm form){
        if(isMemberExist(form.getEmail(), form.getPw())){
            throw new MemberException(ErrorCode.ALREADY_EXSISTS_EMAIL_PW);
        }
        else{
            return memberRepos.save(Member.from(form));
        }
    }

}
