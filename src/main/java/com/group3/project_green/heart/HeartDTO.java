package com.group3.project_green.heart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeartDTO {
    private Long hno;
    private Long pno;
    private Long id;
    private LocalDateTime regDate,modDate;
}
