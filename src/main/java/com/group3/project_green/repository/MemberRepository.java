package com.group3.project_green.repository;

import com.group3.project_green.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.password = :password")
    Member login(@Param("email") String email , @Param("password") String password); //민혁

    @Transactional
    // 소셜 로그인시, 이미 있는 사용자인지 확인하기 위함
    Optional<Member> findByEmail(String email);
}
