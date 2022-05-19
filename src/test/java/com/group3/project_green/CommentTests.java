package com.group3.project_green;

import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.CommentRepository;
import com.group3.project_green.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTests {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void insert(){
        Member member = Member.builder()
                .id(3L)
                .email("test2@naver.com")
                .build();
        Comment comment = Comment.builder()
                .replyer(member.getEmail())
                .content("댓글 테스트 1")
                .likeNum(2L)
                .post(Post.builder().pno(2L).build())
                .build();
        commentRepository.save(comment);

    }
}
