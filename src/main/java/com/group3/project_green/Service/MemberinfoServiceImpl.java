package com.group3.project_green.Service;

import com.group3.project_green.DTO.MemberInfoProfileDTO;
import com.group3.project_green.entity.MemberInfo;
import com.group3.project_green.entity.ProfileImage;
import com.group3.project_green.repository.MemberinfoRepository;
import com.group3.project_green.repository.ProfileImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberinfoServiceImpl implements MemberinfoService{
    private final MemberinfoRepository memberinfoRepository;

    private final ProfileImageRepository profileImageRepository;


    @Transactional
    @Override
    public Long insert(MemberInfoProfileDTO memberInfo2DTO) {
        MemberInfo memberInfoAll = memberinfoRepository.findByMemberId(memberInfo2DTO.getIno());
        memberInfoAll.setProfileImageDTOList(memberInfo2DTO.getProfileImageDTOList());
        System.out.println("인포정보: " + memberInfoAll);
        Map<String ,Object> entityMap = dtoToEntity(memberInfoAll);
        MemberInfo memberInfo = (MemberInfo) entityMap.get("memberInfo");
        List<ProfileImage> fileImageList =(List<ProfileImage>) entityMap.get("imgList");
        memberinfoRepository.save(memberInfo);
        profileImageRepository.saveAll(fileImageList);
        return memberInfo2DTO.getIno();
    }
}
