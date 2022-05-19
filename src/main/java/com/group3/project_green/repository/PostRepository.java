package com.group3.project_green.repository;


import com.group3.project_green.DTO.PostDTO;
import com.group3.project_green.entity.Accom;
import com.group3.project_green.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p.title, p.content from Post p " +
            "left join p.member m " +
            "where m.id = :id")
    List<String[]> getListByMemberId(Long id);
   // List<Post> findbyAccom();

  //  List<Post> findbyFood();

  //  List<Post> findbysights();

}