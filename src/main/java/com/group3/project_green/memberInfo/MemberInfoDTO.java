package com.group3.project_green.memberInfo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MemberInfoDTO {
    private Long id;
    private String password;
    private String email;
    private LocalDateTime regDate,modDate;
}
