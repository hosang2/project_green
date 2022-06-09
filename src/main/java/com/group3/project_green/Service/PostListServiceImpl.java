package com.group3.project_green.Service;

import com.group3.project_green.DTO.FileImageDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.FileImage;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.FileImageRepository;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostListServiceImpl implements PostListService{

    private final PostRepository postRepository;
    private final FileImageRepository fileImageRepository;
    @Override
    public List<PostDTO> getList() {
        List<Post> result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate"));
       // result.forEach(i-> System.out.println("service list"+i));

        return result.stream().map(
                post -> {
                    System.out.println("before post:" +post);
                    List<FileImage> files = fileImageRepository.findbyPno(post.getPno());
                    System.out.println("files : "+files);
                    PostDTO postDTO =new PostDTO();
                    List<FileImageDTO> fileImagesDto =new ArrayList<>();
                    for( FileImage i :files){
                        FileImageDTO fileImageDTO = entityToDTOImage(i);
                        fileImagesDto.add(fileImageDTO);
                    }
                    PostDTO postDTO1 = entityToDTO(post);
                    postDTO1.setImageDTOList(fileImagesDto);
                    return postDTO1;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getFoodList() {
        List<Post>result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i->i.getFood() != null).collect(Collectors.toList());
        return result.stream().map(
                post -> {
                    System.out.println("before post:" +post);
                    List<FileImage> files = fileImageRepository.findbyPno(post.getPno());
                    System.out.println("files : "+files);
                    PostDTO postDTO =new PostDTO();
                    List<FileImageDTO> fileImagesDto =new ArrayList<>();
                    for( FileImage i :files){
                        FileImageDTO fileImageDTO = entityToDTOImage(i);
                        fileImagesDto.add(fileImageDTO);
                    }
                    PostDTO postDTO1 = entityToDTO(post);
                    postDTO1.setImageDTOList(fileImagesDto);
                    return postDTO1;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAccomList() {
        List<Post> result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i -> i.getAccom() != null).collect(Collectors.toList());
        return result.stream().map(
                post -> {
                    System.out.println("before post:" +post);
                    List<FileImage> files = fileImageRepository.findbyPno(post.getPno());
                    System.out.println("files : "+files);
                    PostDTO postDTO =new PostDTO();
                    List<FileImageDTO> fileImagesDto =new ArrayList<>();
                    for( FileImage i :files){
                        FileImageDTO fileImageDTO = entityToDTOImage(i);
                        fileImagesDto.add(fileImageDTO);
                    }
                    PostDTO postDTO1 = entityToDTO(post);
                    postDTO1.setImageDTOList(fileImagesDto);
                    return postDTO1;
                }
        ).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getSightsList() {
        List<Post> result = postRepository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i ->  i.getSights() != null).collect(Collectors.toList());
        return result.stream().map(
                post -> {
                    System.out.println("before post:" +post);
                    List<FileImage> files = fileImageRepository.findbyPno(post.getPno());
                    System.out.println("files : "+files);
                    PostDTO postDTO =new PostDTO();
                    List<FileImageDTO> fileImagesDto =new ArrayList<>();
                    for( FileImage i :files){
                        FileImageDTO fileImageDTO = entityToDTOImage(i);
                        fileImagesDto.add(fileImageDTO);
                    }
                    PostDTO postDTO1 = entityToDTO(post);
                    postDTO1.setImageDTOList(fileImagesDto);
                    return postDTO1;
                }
        ).collect(Collectors.toList());
    }
}
