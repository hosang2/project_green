package com.group3.project_green.entity;

import com.group3.project_green.BaseEntity;
import com.group3.project_green.DTO.FileImageDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString

public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    // 게시글 제목
    private String title;

    // 게시글 내용
    private String content;

    // 게시글 좋아요 갯수
    private Long likeNum;

    // 게시글(다) : 회원(일) 다대일 JOIN 중복댓글가능
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /* 게시글의 카테고리는 null이 가능합니다.
    * 음식 관련 글이라면, 숙박과 관광지 컬럼의 데이터는 null이 됩니다.
    *  */;;

    @ManyToOne(fetch = FetchType.LAZY)
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accom accom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sights sights;

    @Transient
    private List<FileImageDTO> imageDTOList = new ArrayList<>();


}