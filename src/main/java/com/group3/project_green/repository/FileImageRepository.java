package com.group3.project_green.repository;

import com.group3.project_green.entity.FileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileImageRepository  extends JpaRepository<FileImage,Long> {
    @Query("select f from FileImage f where f.post.pno= :pno")
    List<FileImage> findbyPno(@Param("pno") Long pno);
}
