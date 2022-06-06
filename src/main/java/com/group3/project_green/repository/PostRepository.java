package com.group3.project_green.repository;


import com.group3.project_green.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p " +
            "left join p.member m " +
            "where m.id = :id")
    List<Post> getPostsByMemberId(Long id);

    @Query("select p  from Post p " +
            "left join p.member m " +
            "where m.id = :id "+
            "and p.title like %:title% " +
            "or m.id = :id and p.content like %:content% order by p.modDate DESC ")
    Page<Post> getPostsByMemberIdPage(Long id , String title, String content, Pageable pageable);

    @Query("select p  from Post p " +
            "left join p.member m " +
            "where m.id = :id "+
            "and p.food is not null and p.title like %:title% " +
            "or m.id = :id " +
            "and p.food is not null and p.content like %:content% order by p.modDate DESC ")
    Page<Post> getPostsByMemberIdByFoodFid(Long id ,String title, String content, Pageable pageable);

    @Query("select p  from Post p " +
            "left join p.member m " +
            "where m.id = :id "+
            "and p.sights is not null and p.title like %:title% " +
            "or m.id = :id " +
            "and p.sights is not null and p.content like %:content% order by p.modDate DESC ")
    Page<Post> getPostsByMemberIdBySightSid(Long id ,String title, String content, Pageable pageable);

    @Query("select p  from Post p " +
            "left join p.member m " +
            "where m.id = :id "+
            "and p.accom is not null and p.title like %:title% " +
            "or m.id = :id " +
            "and p.accom is not null and p.content like %:content% order by p.modDate DESC ")
    Page<Post> getPostsByMemberIdByAccomAid(Long id ,String title, String content, Pageable pageable);


   // List<Post> findbyAccom();

    @Query(value = "select p,m, count(c) from Post p " +
            "left join p.member m " +
            "left outer join Comment c on c.post = p " +
            "where p.pno = :pno")
    Object getPostByPno(@Param("pno") Long pno);


    Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);





}