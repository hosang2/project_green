package com.group3.project_green.repository;


import com.group3.project_green.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface MemberinfoRepository extends JpaRepository<MemberInfo, Long> {
    @Transactional
    MemberInfo findByMemberId(Long id);

}