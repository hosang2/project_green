package com.group3.project_green.Service;

import com.group3.project_green.DTO.FileImageDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.FileImage;
import com.group3.project_green.entity.Post;

import java.util.ArrayList;
import java.util.List;

public interface PostListService {

    List<PostDTO> getList();
    List<PostDTO>getFoodList();
    List<PostDTO>getAccomList();
    List<PostDTO>getSightsList();
    default Post dtoToEntity(PostDTO dto){
        Post post =Post.builder()
                .pno(dto.getPno())
                .accom(dto.getAccom())
                .content(dto.getContent())
                .food(dto.getFood())
                .likeNum(dto.getLikeNum())
                .member(dto.getMember())
                .sights(dto.getSights())
                .title(dto.getTitle())
                .imageDTOList(dto.getImageDTOList())
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

    default FileImage dtoToEntityImage(FileImageDTO dto){
        FileImage fileImage = FileImage.builder()
                .imgName(dto.getImgName())
                .path(dto.getPath())
                .uuid(dto.getUuid())
                .build();
        return fileImage;
    }

    default FileImageDTO entityToDTOImage(FileImage fileImage) {
        FileImageDTO fileImageDTO = FileImageDTO.builder()
                .imgName(fileImage.getImgName())
                .path(fileImage.getPath())
                .uuid(fileImage.getUuid())
                .build();
        return fileImageDTO;
    }
}
