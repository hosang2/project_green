package com.group3.project_green.DTO;

import com.group3.project_green.entity.Accom;
import com.group3.project_green.entity.Food;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Sights;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class PostCommentDTO {
    private Long pno;
    private String title;
    private String content;
    private Long likeNum;
    private Member member;
    private Food food;
    private Accom accom;
    private Sights sights;
    private LocalDateTime regDate,modDate;

    private String memberEmail;

    private int commentCnt;
}
