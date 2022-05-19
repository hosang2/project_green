package com.group3.project_green.repository;

import com.group3.project_green.entity.Comment;
import com.group3.project_green.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getCommentsByPostOrderByCno(Post post);
}
