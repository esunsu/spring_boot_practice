package com.example.restfull.Controller;

import com.example.restfull.Domain.Form.BoardForm;
import com.example.restfull.Service.MakeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final MakeBoardService makeBoardService;
    @PostMapping("/make")
    public ResponseEntity<String> CreateBoard(@RequestParam Long memberId, @RequestBody @Valid BoardForm boardForm){
        makeBoardService.CreateBoard(memberId, boardForm);
        return ResponseEntity.ok("게시판 생성 완료");
    }



}
