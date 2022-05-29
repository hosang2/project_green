package com.group3.project_green.memberInfo.repository;

import com.group3.project_green.entity.Post;
import com.group3.project_green.repository.PostRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface InfoPostRepository extends JpaRepository<Post, Long> {
    // 회원 아이디를 이용해 회원이 작성한 모든 게시글을 삭제합니다.
    @Transactional
    void deleteByMember_Id(Long id);

    @Transactional
    List<Post> findByMemberId(Long id);

}
