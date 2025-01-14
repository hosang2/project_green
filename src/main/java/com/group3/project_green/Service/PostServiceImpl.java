package com.group3.project_green.Service;

import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
        // 리스트를 뽑아온다
        List<PostDTO> dtoList = new ArrayList<>();
        for(Post post : result){
            dtoList.add(entityToDTO(post));
        }
        // 뽑아온 리스트를 DTO 리스트로 바꾼다 (default 메서드이용)

        // DTO 리스트를 반환한다.
        return dtoList;
    }

    @Override
    public List<PostDTO> getFoodList() {
        List<Post>result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i->i.getFood() != null).collect(Collectors.toList());
        List<PostDTO> dtoList = new ArrayList<>();
        for (Post post :result){
            dtoList.add(entityToDTO(post));
        }
        return dtoList;
    }

    @Override
    public List<PostDTO> getAccomList() {
        List<Post> result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i -> i.getAccom() != null).collect(Collectors.toList());
        List<PostDTO> dtoList = new ArrayList<>();
        for (Post post : result) {
            dtoList.add(entityToDTO(post));
        }
        return dtoList;
    }

    @Override
    public List<PostDTO> getSightsList() {
        List<Post> result = repository.findAll(Sort.by(Sort.Direction.DESC,"modDate")).stream().filter(i ->  i.getSights() != null).collect(Collectors.toList());
        List<PostDTO> dtoList = new ArrayList<>();
        for (Post post : result) {
            dtoList.add(entityToDTO(post));
        }
        return dtoList;
    }
//
//    @Override
//    public List<PostDTO> getListByAccom() {
//
//        List<Post> result = repository.findbyAccom();
//
//        List<PostDTO> dtoList = new ArrayList<>();
//        for(Post post : result){
//            dtoList.add(entityToDTO(post));
//            System.out.println("===================");
//            System.out.println(post);
//            System.out.println("===================");
//        }
//        return  dtoList;
//    }
//
//    @Override
//    public List<PostDTO> getListByFood() {
//        List<Post> result = repository.findbyFood();
//        List<PostDTO> dtoList = new ArrayList<>();
//        for(Post post : result){
//            dtoList.add(entityToDTO(post));
//            System.out.println("===================");
//            System.out.println(post);
//            System.out.println("===================");
//        }
//
//        return dtoList;
//    }

//    @Override
//    public List<PostDTO> getListBysights() {
//        List<Post> result = repository.findbysights();
//
//        List<PostDTO> dtoList = new ArrayList<>();
//        for(Post post : result){
//            dtoList.add(entityToDTO(post));
//            System.out.println("===================");
//            System.out.println(post);
//            System.out.println("===================");
//        }
//        return dtoList;
//    }


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


}
