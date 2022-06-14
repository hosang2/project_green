package com.group3.project_green.DTO;

import com.group3.project_green.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class MemberInfoProfileDTO {

    private Long ino;

    private String name;
    private Member member;
    private String phone;
    private String birthDate;
    private String address;
    private String detailAddress;
    private String location;
    private String favoriteFood;
    private String favoriteAccom;
    private String favoriteLocation;

    //프로필 사진추가를 위한 칼럼
    @Builder.Default
    private List<ProfileImageDTO> profileImageDTOList = new ArrayList<>();

}
