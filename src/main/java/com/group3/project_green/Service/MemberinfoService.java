package com.group3.project_green.Service;

import com.group3.project_green.DTO.MemberInfoProfileDTO;
import com.group3.project_green.DTO.ProfileImageDTO;
import com.group3.project_green.entity.MemberInfo;
import com.group3.project_green.entity.ProfileImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MemberinfoService {


    Long insert(MemberInfoProfileDTO memberInfo2DTO);

    default Map<String, Object> dtoToEntity( MemberInfo memberInfoAll) {
        Map<String, Object> entityMap = new HashMap<>();

        entityMap.put("memberInfo",memberInfoAll);
        List<ProfileImageDTO> profileImageDTOS = memberInfoAll.getProfileImageDTOList();

        if(profileImageDTOS != null && profileImageDTOS.size() > 0 ) {
            List<ProfileImage> profileImageDTOList = profileImageDTOS.stream().map(ProfileImageDTO ->{
                ProfileImage profileImage = ProfileImage.builder()
                        .inum(memberInfoAll.getIno())
                        .path(ProfileImageDTO.getPath())
                        .imgName(ProfileImageDTO.getImgName())
                        .uuid(ProfileImageDTO.getUuid())
                        .memberInfo(memberInfoAll)
                        .build();
                return profileImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", profileImageDTOList);

        }
        return entityMap;
    }
}
