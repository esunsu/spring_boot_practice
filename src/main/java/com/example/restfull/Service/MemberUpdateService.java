package com.example.restfull.Service;

import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Domain.Form.UpdateMemberForm;
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
public class MemberUpdateService {
    private final MemberRepos memberRepos;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public Member updateMember(String email, String pw, UpdateMemberForm updateMemberForm){
        String encrypt_pw = "";
        Member member = memberRepos.findByEmail(email)
                .orElseThrow(
                        ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
                );
        if(passwordEncoder.matches(pw, member.getPw())) {
            if (!(updateMemberForm.getModifiedPw().length() < 8)) {
                encrypt_pw = passwordEncoder.encode(updateMemberForm.getModifiedPw());
                member.setPw(encrypt_pw);
                member.setName(updateMemberForm.getModifiedName());
                member.setContact(updateMemberForm.getModifiedContact());
                return member;
            } else {
                throw new MemberException(ErrorCode.NOT_VALID_PASSWORD);
            }
        }
        else{
            throw new MemberException(ErrorCode.NOT_CORRECT_PASSWORD);
        }

    }
}
