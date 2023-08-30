package com.example.restfull.Controller;

import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Domain.Form.GetEmailAndPwForm;
import com.example.restfull.Domain.Form.LogInForm;
import com.example.restfull.Domain.Form.SignupForm;
import com.example.restfull.Domain.Form.UpdateMemberForm;
import com.example.restfull.Exception.MemberException;
import com.example.restfull.Service.MemberDeleteService;
import com.example.restfull.Service.MemberReadService;
import com.example.restfull.Service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;


@RestController
@RequestMapping("/rud")
@RequiredArgsConstructor
public class MemberCRUDController {
    private final MemberReadService memberReadService;
    private final MemberUpdateService memberUpdateService;
    private final MemberDeleteService memberDeleteService;

    @GetMapping("/read")
    public ResponseEntity<List<String>> memberRead(@RequestParam String email, @RequestParam String pw) throws MemberException {
        return ResponseEntity.ok(
                memberReadService.readMember(email, pw));
    }

    @PutMapping("/update/{present_email}&{present_pw}")
    public ResponseEntity<String> memberUpdate(@PathVariable String present_pw, @PathVariable String present_email, @RequestBody @Valid UpdateMemberForm updateMemberForm) throws MemberException{
        memberUpdateService.updateMember(present_email, present_pw, updateMemberForm);
        return ResponseEntity.ok("회원정보가 변경되었습니다.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> memberDelete(@RequestBody @Valid GetEmailAndPwForm form) throws MemberException {
        memberDeleteService.deleteMember(form);
        return ResponseEntity.ok("회원이 삭제되었습니다.");
    }

}
