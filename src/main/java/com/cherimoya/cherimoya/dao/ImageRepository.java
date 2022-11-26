package com.cherimoya.cherimoya.dao;

import com.cherimoya.cherimoya.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByUserIdAndPostIdAndIndex(Image image);
}
