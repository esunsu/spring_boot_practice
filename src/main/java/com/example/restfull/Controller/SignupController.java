package com.example.restfull.Controller;

import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import com.example.restfull.Service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor

public class SignupController {
    private final SignupService signupService;

    @PostMapping("/member")
    public ResponseEntity<String> signup(@RequestBody @Valid SignupForm form) throws MemberException{
        signupService.Signup(form);
        return ResponseEntity.ok("회원가입에 성공하였습니다.");
    }
    @GetMapping("/verify")
    public ResponseEntity<Boolean> VerifyMember(@RequestParam String email , @RequestParam String code){
        return ResponseEntity.ok(signupService.verifyMember(email,code));
    }
}
