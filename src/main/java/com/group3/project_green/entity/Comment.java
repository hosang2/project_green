package com.group3.project_green.entity;


import com.group3.project_green.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    // 댓글 내용
    private String content;

    // 좋아요 갯수
    private Long likeNum;
    
    //댓글 작성자
    private String replyer;

    // 댓글(다) : 게시글(일) 다대일 JOIN
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    // 댓글(다) : 회원(일) 다대일 JOIN
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
