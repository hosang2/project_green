package com.group3.project_green.DTO;

import com.group3.project_green.entity.Accom;
import com.group3.project_green.entity.Food;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Sights;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class PostDTO {

    private Long pno;
    private String title;
    private String content;
    private Long likeNum;
    private Member member;
    private Food food;
    private Accom accom;
    private Sights sights;
    private LocalDateTime regDate,modDate;

}
