package com.group3.project_green.memberInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;
import javax.persistence.Id;

@Data
@Builder
public class MemberDetailDTO {
    private Long ino;

    private String name;

    private String phone;

    private String birthDate;

    private String address;

    private String detailAddress;

    private String location;

    private String favoriteFood;
    private String favoriteAccom;
    private String favoriteLocation;

}
