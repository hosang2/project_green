package com.group3.project_green.Service;

import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListServiceImpl implements PostListService{

    private final PostRepository postRepository;
    @Override
    public List<PostDTO> getList() {
        List<Post> result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate"));
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getFoodList() {
        List<Post>result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i->i.getFood() != null).collect(Collectors.toList());
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAccomList() {
        List<Post> result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i -> i.getAccom() != null).collect(Collectors.toList());
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getSightsList() {
        List<Post> result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i ->  i.getSights() != null).collect(Collectors.toList());
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }
}
