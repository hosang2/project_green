package com.group3.project_green.memberInfo.repository;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface InfoMemberDetailRepository extends JpaRepository<MemberInfo, Long> {

    @Transactional
    Optional<MemberInfo> findByMemberId(Long id);

}
