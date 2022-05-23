package com.group3.project_green;

import com.group3.project_green.DTO.PostCommentDTO;
import com.group3.project_green.Service.PostService;
import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class CommentTests {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;

    @Test
    public void insert(){
        Member member = Member.builder()
                .id(4L)
                .email("test3@naver.com")
                .build();
        Comment comment = Comment.builder()
                .member(Member.builder().id(4L).build())
                .replyer(member.getEmail())
                .content("댓글 테스트 3")
                .likeNum(2L)
                .post(Post.builder().pno(2L).build())
                .build();
        commentRepository.save(comment);

    }
    @Test
    @Transactional
    public void listByPost() {
        List<Comment> list = commentRepository.getCommentsByPostOrderByCno(Post.builder().pno(2L).build());
        list.forEach(i-> System.out.println(i));
    }

    @Test
    @Transactional
    public void getTest() {
        Long pno = 2L;
        PostCommentDTO dto = postService.getPostWithCommentCnt(pno);
        System.out.println(dto);
    }
}
