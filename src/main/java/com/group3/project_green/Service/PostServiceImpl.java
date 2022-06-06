package com.group3.project_green.Service;

import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService{
    private final PostRepository repository;

    @Override
    public List<PostDTO> getList() {
      
        List<Post> result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate"));
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getFoodList() {
        List<Post>result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i->i.getFood() != null).collect(Collectors.toList());
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getAccomList() {
        List<Post> result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i -> i.getAccom() != null).collect(Collectors.toList());
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getSightsList() {
        List<Post> result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i ->  i.getSights() != null).collect(Collectors.toList());
        return result.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public Page<Post> findByTitleContainingOrContentContaining(String title ,String content, Pageable pageable) {
        Page<Post> result= repository.findByTitleContainingOrContentContaining(title,content, pageable);
        System.out.println("service1) =======================impl================");
        //Page<Post> temp = result;
        System.out.println("resut:  "+result);
        System.out.println("=======================impl================");
        return result;
    }

    @Override
    public PostCommentDTO getPostWithCommentCnt(Long pno) {
        Object result = repository.getPostByPno(pno);
        Object[] arr = (Object[]) result;
        return entityToDTO((Post) arr[0],(Member)arr[1],(Long) arr[2]);
    }

    @Override
    public PostDTO get(Long pno) {
        PostDTO result = entityToDTO(repository.getById(pno));
        return result;
    }

    @Override
    public List<PostDTO> getPostList(Long pno) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        System.out.println("멤버 아이디 : " +memberId.getMember().getId());
        List<Post> result = repository.getPostsByMemberId(memberId.getMember().getId());
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post : result){
            System.out.println("포스트 :" +post);
            postDTOList.add(entityToDTO(post));
        }
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostListBySights(Long pno) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        List<Post> result = repository.getPostsByMemberIdBySightSid(memberId.getMember().getId());
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post : result){
            postDTOList.add(entityToDTO(post));
        }
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostByFoodFid(Long pno) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        List<Post> result = repository.getPostsByMemberIdByFoodFid(memberId.getMember().getId());
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post : result){
            postDTOList.add(entityToDTO(post));
        }
        return postDTOList;
    }

    @Override
    public List<PostDTO> getPostByAccomAid(Long pno) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        List<Post> result = repository.getPostsByMemberIdByAccomAid(memberId.getMember().getId());
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post : result){
            postDTOList.add(entityToDTO(post));
        }
        return postDTOList;
    }

    @Override //민혁
    public void savePost(PostDTO postDTO) {
        Post post = dtoToEntity(postDTO);
        // dtoToEntity
        repository.save(post);
    }

}
