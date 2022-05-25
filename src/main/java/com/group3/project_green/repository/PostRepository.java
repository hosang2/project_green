package com.group3.project_green.repository;


import com.group3.project_green.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p " +
            "left join p.member m " +
            "where m.id = :id")
    List<Post> getPostsByMemberId(Long id);

    @Query("select p from Post p " +
            "left join p.member m " +
            "where m.id = :id and p.sights.sid is not null")
    List<Post> getPostsByMemberIdBySightSid(Long id);

    @Query("select p from Post p " +
            "left join p.member m " +
            "where m.id = :id and p.food.fid is not null")
    List<Post> getPostsByMemberIdByFoodFid(Long id);

    @Query("select p from Post p " +
            "left join p.member m " +
            "where m.id = :id and p.accom.aid is not null")
    List<Post> getPostsByMemberIdByAccomAid(Long id);


   // List<Post> findbyAccom();

  //  List<Post> findbyFood();

    //  List<Post> findbysights();
    @Query(value = "select p,m, count(c) from Post p " +
            "left join p.member m " +
            "left outer join Comment c on c.post = p " +
            "where p.pno = :pno")
    Object getPostByPno(@Param("pno") Long pno);
  //  List<Post> findbysights();



}