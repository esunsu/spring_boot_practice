package com.example.restfull.Service;


import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Domain.entity.Member;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import com.example.restfull.Filter.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepos memberRepos;
    private final JwtTokenProvider jwtTokenProvider;
    public String LoginMethod(String email, String pw) {
        Member member = memberRepos.findByEmail(email)
                .orElseThrow(
                        ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
                );
        if(passwordEncoder.matches(pw, member.getPw())){
            return jwtTokenProvider.createAccessToken(member.getUsername(), member.getRoles());
        }
        else{
            throw new MemberException(ErrorCode.NOT_CORRECT_PASSWORD);
        }

    }
}
