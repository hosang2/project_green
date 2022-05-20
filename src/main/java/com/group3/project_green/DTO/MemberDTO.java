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
public class MemberDTO {
    private Long id;
    private String password;
    private String email;
    private LocalDateTime regDate,modDate;
}
