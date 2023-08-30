package com.example.restfull.Service;

import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Domain.Form.GetEmailAndPwForm;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MemberDeleteService {
    private final MemberRepos memberRepos;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public Member deleteMember(GetEmailAndPwForm getEmailAndPwForm){
        Member member = memberRepos.findByEmail(getEmailAndPwForm.getEmail())
                .orElseThrow(
                        ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
                );
        if(passwordEncoder.matches(getEmailAndPwForm.getPw(), member.getPw())){
            memberRepos.delete(member);
            return member;
        }
        else{
            throw new MemberException(ErrorCode.NOT_CORRECT_PASSWORD);
        }
    }

}
