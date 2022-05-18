package com.group3.project_green.Service;

import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Post;

import java.util.List;

public interface PostService {

    List<PostDTO>getList();

    default  Post dtoToEntity(PostDTO dto){
        Post post =Post.builder()
                .accom(dto.getAccom())
                .content(dto.getContent())
                .food(dto.getFood())
                .likeNum(dto.getLikeNum())
                .member(dto.getMember())
                .sights(dto.getSights())
                .title(dto.getTitle())
                .build();
        return post;
    }

    default PostDTO entityToDTO(Post post){
        PostDTO postDTO = PostDTO.builder()
                .title(post.getTitle())
                .sights(post.getSights())
                .member(post.getMember())
                .likeNum(post.getLikeNum())
                .food(post.getFood())
                .content(post.getContent())
                .accom(post.getAccom())
                .build();
        return postDTO;


    }
}
