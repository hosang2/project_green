package com.group3.project_green.memberInfo.service;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.entity.Member;
import com.group3.project_green.memberInfo.MemberDetailDTO;
import com.group3.project_green.memberInfo.MemberInfoDTO;

public interface MemberInfoService {

    // 회원조회
    MemberInfoDTO get(Long id);

    // 회원탈퇴
    void quit(Long id);

    // 회원정보 수정
    void modifyInfo(Long id, MemberDetailDTO memberInfoDTO);

    MemberDetailDTO getDetail(Long id);

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
