package com.group3.project_green.heart;

import com.group3.project_green.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    //갯수 확인
    @Query(value = "select count(*) from Heart where post_pno = :pno",nativeQuery = true)
    Long countHeartByPostPno(Long pno);

    //멤버 id 확인
    Heart findByMember_IdAndAndPostPno(Long id,Long pno);


}
