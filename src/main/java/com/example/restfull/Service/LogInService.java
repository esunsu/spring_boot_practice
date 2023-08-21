package com.example.restfull.Service;


import com.example.restfull.Client.SendMailForm;
import com.example.restfull.Domain.Form.LogInForm;
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
public class LogInService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepos memberRepos;

    public boolean isMemberExist(String email, String pw){
        return memberRepos.findByEmailAndPw(email, pw).isPresent();
    }
    public boolean isEmailExist(String email){
        return memberRepos.findByEmail(email).isPresent();
    }
    public boolean LoginMethod(String email, String pw) {
        if(!isEmailExist(email)){
            throw new MemberException(ErrorCode.NOT_EXSISTS_MEMBER);
        }
        else{
            if(passwordEncoder.matches(pw, memberRepos.findByEmail(email).get().getPw())){
                return true;
            }
            else{
                throw new MemberException(ErrorCode.NOT_CORRECT_PASSWORD);
            }
        }
    }
}
