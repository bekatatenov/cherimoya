package com.cherimoya.cherimoya.dao;

import com.cherimoya.cherimoya.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
//    Image findById(Long imageId);

}
