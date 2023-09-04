package com.example.restfull.Controller;

import com.example.restfull.Domain.Form.BoardForm;
import com.example.restfull.Service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    @PostMapping("/make")
    public ResponseEntity<String> createBoard(@RequestParam Long memberId, @RequestBody @Valid BoardForm boardForm){
        boardService.createBoard(memberId, boardForm);
        return ResponseEntity.ok("게시판 생성 완료");
    }
    @GetMapping("/read")
    public ResponseEntity<List<String>> readBoard(@RequestParam Long boardId) {
        return ResponseEntity.ok(boardService.readBoard(boardId));
    }
    @PutMapping("/update/{board_id}&{member_id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long board_id, @PathVariable Long member_id, @RequestBody @Valid BoardForm boardForm) {
        boardService.updateBoard(board_id, member_id, boardForm);
        return ResponseEntity.ok("게시판이 변경되었습니다.");
    }
    @DeleteMapping("/delete/{board_id}&{member_id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long board_id, @PathVariable Long member_id) {
        boardService.deleteBoard(board_id, member_id);
        return ResponseEntity.ok("게시판이 삭제되었습니다.");
    }


}
