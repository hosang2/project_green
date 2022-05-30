package com.group3.project_green.Service;

import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;

import java.util.List;

public interface PostService {

    List<PostDTO>getList();

    List<PostDTO>getFoodList();

    List<PostDTO>getAccomList();
    List<PostDTO>getSightsList();

    PostCommentDTO getPostWithCommentCnt(Long pno);

    PostDTO get(Long pno);

    List<PostDTO> getPostList(Long pno);
    List<PostDTO> getPostListBySights(Long pno);
    List<PostDTO> getPostByFoodFid(Long pno);
    List<PostDTO> getPostByAccomAid(Long pno);

    void savePost(PostDTO postDTO);

    default  Post dtoToEntity(PostDTO dto){
        Post post =Post.builder()
                .pno(dto.getPno())
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
                .pno(post.getPno())
                .title(post.getTitle())
                .sights(post.getSights())
                .member(post.getMember())
                .likeNum(post.getLikeNum())
                .food(post.getFood())
                .content(post.getContent())
                .accom(post.getAccom())
                .regDate(post.getRegDate())
                .modDate(post.getModDate())
                .build();
        return postDTO;

    }

    default PostCommentDTO entityToDTO(Post post , Member member, Long commentCnt){
        PostCommentDTO postCDTO = PostCommentDTO.builder()
                .pno(post.getPno())
                .title(post.getTitle())
                .sights(post.getSights())
                .member(post.getMember())
                .likeNum(post.getLikeNum())
                .food(post.getFood())
                .content(post.getContent())
                .accom(post.getAccom())
                .regDate(post.getRegDate())
                .modDate(post.getModDate())
                .memberEmail(member.getEmail())
                .commentCnt(commentCnt.intValue())
                .build();
        return postCDTO;

    }

}
