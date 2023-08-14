package com.example.restfull.Controller;

import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor

public class SignupController {
    private final SignupService signupService;

    @PostMapping("/member")
    public ResponseEntity<String> signup(@RequestBody SignupForm form){
        signupService.Signup(form);
        return ResponseEntity.ok("회원가입에 성공하였습니다.");
    }
}
