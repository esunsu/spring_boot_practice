package com.example.restfull.Service;

import com.example.restfull.Domain.Entity.Board;
import com.example.restfull.Domain.Entity.Member;
import com.example.restfull.Domain.Form.BoardForm;
import com.example.restfull.Domain.Repos.BoardRepos;
import com.example.restfull.Domain.Repos.MemberRepos;
import com.example.restfull.Exception.BoardException;
import com.example.restfull.Exception.ErrorCode;
import com.example.restfull.Exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final MemberRepos memberRepos;
    private final BoardRepos boardRepos;

    public boolean isCertifiedMember (Member member){//이메일 인증이 됐는지 확인
        if(member.isCertified()){
            return true;
        }
        else{
            throw new MemberException(ErrorCode.NOT_CERTIFIED_MEMBER);
        }
    }
    public boolean isCorrectMember (Board board, Member member){//작성자가 맞는지 확인
        if(board.getMember().equals(member)){
            return true;
        }
        else{
            throw new BoardException(ErrorCode.NOT_CORRECT_MEMBER);
        }
    }
    @Transactional
    public void createBoard(Long memberId, BoardForm boardForm) {
        Board board = boardForm.from(boardForm);
        Member member = memberRepos.findByMemberId(memberId).orElseThrow(
                () -> new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
        );
        if (isCertifiedMember(member)) {
            board.setMember(member);
            boardRepos.save(board);
        }
    }
    @Transactional
    public List<String> readBoard(Long boardId){
        Board board = boardRepos.findByBoardId(boardId).orElseThrow(()->new BoardException(ErrorCode.NOT_EXSISTS_BOARD));
        List<String> boardList = new ArrayList<String>();
        boardList.add(board.getTitle());
        boardList.add(board.getContent());
        return boardList;
    }
    @Transactional
    public void updateBoard(Long boardId, Long memberId, BoardForm boardForm){
        Board board1 = boardRepos.findByBoardId(boardId).orElseThrow(()->new BoardException(ErrorCode.NOT_EXSISTS_BOARD));//기존 게시판
        Board board2 = boardForm.from(boardForm);//수정할 게시판 내용
        Member member = memberRepos.findByMemberId(memberId).orElseThrow(
                ()->new MemberException(ErrorCode.NOT_EXSISTS_MEMBER)
        );
        if(isCertifiedMember(member)){
            if(isCorrectMember(board1, member)) {
                board1.setContent(board2.getContent());
                board1.setTitle(board2.getTitle());
            }
        }
    }
    @Transactional
    public void deleteBoard(Long boardId, Long memberId){
        Board board = boardRepos.findByBoardId(boardId).orElseThrow(()->new BoardException(ErrorCode.NOT_EXSISTS_BOARD));
        Member member = memberRepos.findByMemberId(memberId).orElseThrow(()-> new MemberException(ErrorCode.NOT_EXSISTS_MEMBER));
        if(isCertifiedMember(member)){
            if(isCorrectMember(board, member)){
                boardRepos.delete(board);
            }
        }
    }
}
