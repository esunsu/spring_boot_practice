package com.example.restfull.Controller;


import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Exception.MemberException;
import com.example.restfull.Service.LogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LogInController {
    private final LogInService loginService;

    @PostMapping("/user")
    public ResponseEntity<String> loginCheck(@RequestParam String email, String pw){
        if(loginService.LoginMethod(email, pw)){
            return ResponseEntity.ok("로그인에 성공하였습니다.");
        }else {
            return ResponseEntity.ok("회원이 아닙니다.");
        }
    }

}
