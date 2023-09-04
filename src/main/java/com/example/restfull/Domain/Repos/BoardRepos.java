package com.example.restfull.Domain.Repos;

import com.example.restfull.Domain.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepos extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardId(Long boardId);

}
