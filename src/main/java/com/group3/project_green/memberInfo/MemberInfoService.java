package com.group3.project_green.memberInfo;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.entity.Member;

public interface MemberInfoService {

    MemberInfoDTO get(Long id);

    void quit(Long id);

    default Member dtoToEntity(MemberInfoDTO dto){
        Member member=Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return member;
    }

    default MemberInfoDTO entityToDTO(Member member){
        MemberInfoDTO memberInfoDTO = MemberInfoDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .email(member.getEmail())
                .regDate(member.getRegDate())
                .modDate(member.getModDate())
                .build();
        return memberInfoDTO;

    }
}
