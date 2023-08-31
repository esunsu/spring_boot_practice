package com.example.restfull.Service;

import com.example.restfull.Domain.Entity.Board;
import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Domain.Form.BoardForm;
import com.example.restfull.Domain.Repos.BoardRepos;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MakeBoardService {
    private final MemberRepos memberRepos;
    private final BoardRepos boardRepos;
    @Transactional
    public Board CreateBoard (Long memberId, BoardForm boardForm){
        Board board = boardForm.from(boardForm);
        Member member = memberRepos.findByMemberId(memberId).orElseThrow(
                ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
        );
        board.setMember(member);
        boardRepos.save(board);
        return board;
    }
}
