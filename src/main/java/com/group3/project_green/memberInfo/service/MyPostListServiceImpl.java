package com.group3.project_green.memberInfo.service;

import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyPostListServiceImpl implements MyPostListService{

    private final PostRepository postRepository;

    @Override
    public List<PostDTO> getListByMemberId(Long id) {

        List<Post> postList = postRepository.getPostsByMemberId(id);
        List<PostDTO> postDTOList = new ArrayList<>();

        for(Post post : postList){
            postDTOList.add(entityToDTO(post));
        }

        return postDTOList;
    }
}
