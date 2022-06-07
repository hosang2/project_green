package com.group3.project_green.Service;

import com.group3.project_green.DTO.FileImageDTO;
import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PostService {
    Food saveFood(Food food);
    Sights saveSights(Sights sights);
    Accom saveAccom(Accom accom);

    List<PostDTO>getList();

    List<PostDTO>getFoodList();

    List<PostDTO>getAccomList();
    List<PostDTO>getSightsList();
    Page<Post> findByTitleContainingOrContentContaining(String title ,String content, Pageable pageable);
    List<Post> accomfindByTitleContainingOrContentContaining(String title ,String content, Pageable pageable);
    List<Post> foodfindByTitleContainingOrContentContaining(String title ,String content, Pageable pageable);
    List<Post> sightfindByTitleContainingOrContentContaining(String title ,String content, Pageable pageable);
    PostCommentDTO getPostWithCommentCnt(Long pno);

    PostDTO get(Long pno);

    List<PostDTO> getPostList(Long pno);
    Page<Post> getPostListPage(Long pno, String title, String content , Pageable pageable);
    Page<Post> getPostByFoodFid(Long pno, String title,String content ,Pageable pageable);
    Page<Post> getPostBySights(Long pno, String title,String content ,Pageable pageable);
    Page<Post> getPostByAccomAid(Long pno, String title,String content ,Pageable pageable);

    void savePost(PostDTO postDTO);
    Long insert(PostDTO postDTO);

    default PostDTO entitiesToDTO(Post post, List<FileImage> fileImages) {
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

        List<FileImageDTO> fileImageDTOList = fileImages.stream().map(fileImage -> {
            return FileImageDTO.builder().imgName(fileImage.getImgName())
                    .path(fileImage.getPath())
                    .uuid(fileImage.getUuid())
                    .build();
        }).collect(Collectors.toList());
        postDTO.setImageDTOList(fileImageDTOList);
        return postDTO;

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

    default Map<String, Object> dtoToEntity(PostDTO postDTO) {
        Map<String, Object> entityMap = new HashMap<>();
        Post post = Post.builder()
                .pno(postDTO.getPno())
                .accom(postDTO.getAccom())
                .content(postDTO.getContent())
                .food(postDTO.getFood())
                .likeNum(postDTO.getLikeNum())
                .member(postDTO.getMember())
                .sights(postDTO.getSights())
                .title(postDTO.getTitle())
                .build();
        entityMap.put("post",post);
        List<FileImageDTO> imageDTOList = postDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0 ) {
            List<FileImage> fileImageList = imageDTOList.stream().map(fileImageDTO ->{
                FileImage fileImage = FileImage.builder()
                        .path(fileImageDTO.getPath())
                        .imgName(fileImageDTO.getImgName())
                        .uuid(fileImageDTO.getUuid())
                        .post(post)
                        .build();
                return fileImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", fileImageList);

        }
        return entityMap;

    }

}
