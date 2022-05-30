package com.group3.project_green.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class MemberLoginDTO {
    private Long id;
    private String password;
    private String email;
    private String name;
    private String birthDate;
    private String PhoneNumber;
    private LocalDateTime regDate,modDate;
}
