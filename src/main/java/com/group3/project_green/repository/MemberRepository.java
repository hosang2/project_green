package com.group3.project_green.repository;

import com.group3.project_green.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.password = :password")
    Member login(@Param("email") String email , @Param("password") String password); //민혁

}
