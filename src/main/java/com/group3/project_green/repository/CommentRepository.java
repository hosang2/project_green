package com.group3.project_green.repository;

import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Post 삭제 시 댓글들 삭제
    @Modifying
    @Query("delete from Comment c where c.post.pno = :pno")
    void deleteByPno(@Param("pno")Long pno);
    // 게시물로 댓글 목록 가져오기
    List<Comment> getCommentsByPostOrderByCno(Post post);
}
