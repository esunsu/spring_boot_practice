package com.example.restfull.Controller;


import com.example.restfull.Domain.Form.GetEmailAndPwForm;
import com.example.restfull.Filter.Token;
import com.example.restfull.Service.LogInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LogInController {
    private final LogInService loginService;

    @PostMapping("/user")
    public ResponseEntity<Token> loginCheck(@RequestBody GetEmailAndPwForm getEmailAndPwForm){
        return ResponseEntity.ok(loginService.LoginMethod(getEmailAndPwForm.getEmail(),getEmailAndPwForm.getPw()));
    }

}
