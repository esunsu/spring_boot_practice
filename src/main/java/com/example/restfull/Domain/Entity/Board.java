package com.example.restfull.Domain.Entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column
    @NotBlank
    private  String title;

    @Column
    @NotBlank
    //@Lob
    private String content;
}
