package com.example.restfull.Domain.Form;

import com.example.restfull.Domain.Entity.Board;
import com.example.restfull.Domain.Entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardForm {
    private String title;
    private String content;

    public static Board from (BoardForm from){
        return Board.builder()
                .title(from.getTitle())
                .content(from.getContent())
                .build();
    }
}
