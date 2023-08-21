package com.example.restfull.Controller;


import com.example.restfull.Domain.Form.LogInForm;
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
    public ResponseEntity<String> loginCheck(@RequestBody LogInForm logInForm){
        return ResponseEntity.ok(loginService.LoginMethod(logInForm.getEmail(),logInForm.getPw()));
    }

}
