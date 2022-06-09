package com.group3.project_green.heart;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;

public interface HeartService {
    Long saveHeart(HeartDTO dto);

    Long countHeart(Long pno);

    void remove(Long hno);

    Heart getHeart(Long id,Long pno);

    default Heart dtoToEntity(HeartDTO dto) {
        Heart heart = Heart.builder()
                .hno(dto.getHno())
                .member(Member.builder().id(dto.getId()).build())
                .post(Post.builder().pno(dto.getPno()).build())
                .build();
        return heart;
    }

    default HeartDTO entityToDto(Heart heart) {
        HeartDTO dto = HeartDTO.builder()
                .hno(heart.getHno())
                .id(heart.getMember().getId())
                .pno(heart.getPost().getPno())
                .regDate(heart.getRegDate())
                .modDate(heart.getModDate())
                .build();
        return dto;
    }
}
