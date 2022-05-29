package com.group3.project_green.memberInfo.repository;

import com.group3.project_green.entity.Comment;
import com.group3.project_green.repository.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface InfoCommentRepository extends JpaRepository<Comment, Long> {
    // 회원 아이디로 모든 댓글 삭제
    @Transactional
    void deleteByMember_Id(Long id);

    @Transactional
    void deleteByPost_Pno(Long pno);

}
