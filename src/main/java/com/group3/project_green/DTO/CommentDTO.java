package com.group3.project_green.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long cno;
    private String content;
    private Long likeNum;
    private String replyer;

    private Long pno;
    private LocalDateTime regDate,modDate;

}
