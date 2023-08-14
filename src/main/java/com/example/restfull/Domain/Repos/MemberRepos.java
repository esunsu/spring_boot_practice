package com.example.restfull.Domain.Repos;

import com.example.restfull.Domain.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepos extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmailAndPw(String email, String pw);

}
