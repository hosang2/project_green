package com.group3.project_green.Service;

import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.*;
import com.group3.project_green.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService{
    private final PostRepository repository;
    private final FileImageRepository imageRepository;

    private final FoodRepository foodRepository;

    private final SightRepository sightRepository;

    private final AccomRepository accomRepository;
    private final CommentRepository commentRepository;

    @Override
    public Food saveFood(Food food) {
        ///System.out.println("음식추가 : " +food);
        return foodRepository.save(food);
    }

    @Override
    public Sights saveSights(Sights sights) {
        return sightRepository.save(sights);
    }

    @Override
    public Accom saveAccom(Accom accom) {
        return accomRepository.save(accom);
    }

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
    public List<Post> findByTitleContainingOrContentContaining(String title ,String content, Pageable pageable) {
        List<Post> result= repository.findByTitleContainingOrContentContaining(title,content, pageable);
        return result;
    }

    @Override
    public List<Post> accomfindByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        List<Post> result= repository.findByTitleContainingOrContentContaining(title,content, pageable).stream().filter(i-> i.getAccom() !=null).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Post> foodfindByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        List<Post> result= repository.findByTitleContainingOrContentContaining(title,content, pageable).stream().filter(i-> i.getFood() !=null).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Post> sightfindByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        List<Post> result= repository.findByTitleContainingOrContentContaining(title,content, pageable).stream().filter(i-> i.getSights() !=null).collect(Collectors.toList());
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
    public void removePost(Long pno) {
        commentRepository.deleteByPno(pno);
        repository.deleteById(pno);
    }

    @Override
    public List<PostDTO> getPostList(Long pno) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
       // System.out.println("멤버 아이디 : " +memberId.getMember().getId());
        List<Post> result = repository.getPostsByMemberId(memberId.getMember().getId());
        List<PostDTO> postDTOList = new ArrayList<>();
        for(Post post : result){
           // System.out.println("포스트 :" +post);
            postDTOList.add(entityToDTO(post));
        }
        return postDTOList;
    }

    @Override
    public Page<Post> getPostListPage(Long pno, String title, String content, Pageable pageable) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        Page<Post> result = repository.getPostsByMemberIdPage(memberId.getMember().getId(),title,content,pageable);
        return result;
    }
    @Override
    public Page<Post> getPostByFoodFid(Long pno, String title, String content, Pageable pageable) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        Page<Post> result = repository.getPostsByMemberIdByFoodFid(memberId.getMember().getId(),title,content,pageable);
        return result;
    }
    @Override
    public Page<Post> getPostBySights(Long pno, String title, String content, Pageable pageable) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        Page<Post> result = repository.getPostsByMemberIdBySightSid(memberId.getMember().getId(),title,content,pageable);
        return result;
    }
    @Override
    public Page<Post> getPostByAccomAid(Long pno, String title, String content, Pageable pageable) {
        PostDTO memberId = entityToDTO(repository.getById(pno));
        Page<Post> result = repository.getPostsByMemberIdByAccomAid(memberId.getMember().getId(),title,content,pageable);
        return result;
    }

    @Override //민혁
    public void savePost(PostDTO postDTO) {

    }
    @Transactional
    @Override
    public Long insert(PostDTO postDTO) {
        Map<String ,Object> entityMap = dtoToEntity(postDTO);
        Post post = (Post) entityMap.get("post");
        List<FileImage> fileImageList =(List<FileImage>) entityMap.get("imgList");
        repository.save(post);
        fileImageList.forEach(i->{
            imageRepository.save(i);
        });
        return postDTO.getPno();
    }
    @Override
    public List<FileImage> getImageList(Long pno) {
        //System.out.println("이미지 가져오기 : " + pno );
        return imageRepository.findbyPno(pno);
    }

}
