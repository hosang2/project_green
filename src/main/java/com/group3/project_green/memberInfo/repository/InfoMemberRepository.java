package com.group3.project_green.memberInfo.repository;

import com.group3.project_green.entity.Member;
import com.group3.project_green.entity.MemberInfo;
import com.group3.project_green.repository.MemberRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfoMemberRepository extends JpaRepository<Member, Long> {

}
