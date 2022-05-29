package com.group3.project_green;

import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.Post;
import com.group3.project_green.memberInfo.repository.InfoCommentRepository;
import com.group3.project_green.memberInfo.repository.InfoMemberRepository;
import com.group3.project_green.memberInfo.repository.InfoPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
public class UserInfoTests {

    @Autowired
    private InfoCommentRepository infoCommentRepository;

    @Autowired
    private InfoPostRepository infoPostRepository;

    @Autowired
    private InfoMemberRepository infoMemberRepository;

    @Test
    public void insertComment(){

        Member commentWriter = Member.builder().id(2L).build();

        Post post = Post.builder().pno(4L).build();

        Comment comment = Comment.builder()
                .content("comment1")
                .member(commentWriter)
                .post(post)
                .build();

        infoCommentRepository.save(comment);
    }

    @Transactional
    @Modifying
    @Rollback
    @Test
    public void deleteCommentByMemberIdTest(){
        infoCommentRepository.deleteByMember_Id(2L);
    }

    @Modifying
    @Rollback
    @Transactional
    @Test
    public void deletePostByMemberIdTest(){
        infoPostRepository.deleteByMember_Id(2L);
    }

    @Modifying
    @Rollback
    @Transactional
    @Test
    public void deleteMemberByIdTest(){
        infoMemberRepository.deleteById(2L);
    }

}
