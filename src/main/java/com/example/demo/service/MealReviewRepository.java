package com.example.demo.service;

import com.example.demo.model.MealReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealReviewRepository extends JpaRepository<MealReview, Long> {
}
