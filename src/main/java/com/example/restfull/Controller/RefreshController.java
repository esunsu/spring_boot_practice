package com.example.restfull.Controller;

import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import com.example.restfull.Filter.JwtTokenProvider;
import com.example.restfull.Filter.Token;
import com.example.restfull.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class RefreshController {

    private final JwtService jwtService;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepos memberRepos;

    @PostMapping("/refresh")
    public ResponseEntity<Token> validateRefreshToken(@RequestBody HashMap<String, String> bodyJson){
        //log.info("refresh controller 실행");
        Map<String, String> map = jwtService.validateRefreshToken(bodyJson.get("refreshToken"));

        if(map.get("status").equals("402")){
            //Refresh Token이 유효하지 않음
            throw new MemberException(ErrorCode.NOT_VALID_REFRESHTOKEN);
        }

        //log.info("RefreshController - Refresh Token이 유효.");
        Member member = memberRepos.findByEmail(bodyJson.get("key"))
               .orElseThrow(
               ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
       );
        Token tokenDto = jwtTokenProvider.createAccessToken(member.getUsername(), member.getRoles());
        return ResponseEntity.ok(tokenDto);
    }

}

