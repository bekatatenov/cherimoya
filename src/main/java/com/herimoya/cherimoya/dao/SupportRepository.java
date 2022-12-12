package com.herimoya.cherimoya.dao;

import com.herimoya.cherimoya.entity.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository <Support, Long> {

}