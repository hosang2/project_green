package com.group3.project_green.memberInfo.repository;

import com.group3.project_green.entity.Member;
import com.group3.project_green.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoMemberRepository extends JpaRepository<Member, Long> {
}
