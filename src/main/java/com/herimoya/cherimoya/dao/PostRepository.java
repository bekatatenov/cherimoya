package com.herimoya.cherimoya.dao;

import com.herimoya.cherimoya.entity.Post;
import com.herimoya.cherimoya.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List <Post> findAll();

    List<Post> findAllByTitle(String title);

    @Modifying
    @Query("UPDATE posts p set p.pStatus = :status where p.id = :id")
    void update(@Param("id") Long id, @Param("status") Status pStatus);

    Optional<Post> findById(Post id);
}
