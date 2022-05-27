package com.group3.project_green.Service;

import com.group3.project_green.DTO.MemberDTO;
import com.group3.project_green.entity.Member;

public interface MemberInfoService {

    MemberDTO get(Long id);

    void quit(Long id);

    default Member dtoToEntity(MemberDTO dto){
        Member member=Member.builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return member;
    }

    default MemberDTO entityToDTO(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .email(member.getEmail())
                .regDate(member.getRegDate())
                .modDate(member.getModDate())
                .build();
        return memberDTO;


    }
}
