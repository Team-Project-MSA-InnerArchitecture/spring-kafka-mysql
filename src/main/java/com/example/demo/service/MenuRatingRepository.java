package com.example.demo.service;

import com.example.demo.model.MenuRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRatingRepository extends JpaRepository<MenuRating, Long> {
}
