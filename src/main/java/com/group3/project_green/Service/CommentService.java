package com.group3.project_green.Service;

import com.group3.project_green.DTO.CommentDTO;
import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Post;

import java.util.List;

public interface CommentService {
    Long register(CommentDTO commentDTO);
    List<CommentDTO> getList(Long pno);
    default Comment dtoToEntity(CommentDTO dto) {
        Post post = Post.builder()
                .pno(dto.getPno())
                .build();
        Comment comment = Comment.builder()
                .cno(dto.getCno())
                .content(dto.getContent())
                .likeNum(dto.getLikeNum())
                .replyer(dto.getReplyer())
                .post(post)
                .build();
        return comment;
    }

    default CommentDTO entityToDto(Comment comment) {
        CommentDTO dto = CommentDTO.builder()
                .cno(comment.getCno())
                .content(comment.getContent())
                .likeNum(comment.getLikeNum())
                .replyer(comment.getReplyer())
                .regDate(comment.getRegDate())
                .modDate(comment.getModDate())
                .build();
        return dto;
    }
}
