package com.group3.project_green.repository;

import com.group3.project_green.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileImageRepository extends JpaRepository<ProfileImage,Long> {

    List<ProfileImage> findByMemberInfoIno(Long id);

}
