package com.example.restfull.Service;


import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    private final MemberRepos memberRepos;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public List<String> readMember(String email, String pw){

        Member member = memberRepos.findByEmail(email)
                .orElseThrow(
                        ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
                );
        if(passwordEncoder.matches(pw, member.getPw())){
            List<String> memberList = new ArrayList<String>();
            memberList.add("이메일 : "+member.getEmail());
            memberList.add("이름 : "+member.getName());
            memberList.add("전화번호 : "+member.getContact());
            return memberList;
        }
        else{
            throw new MemberException(ErrorCode.NOT_CORRECT_PASSWORD);
        }

    }
}
