package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class MenuRating {
    @Id @GeneratedValue
    private Long id;

    private String menuName;
    private Integer rating;
    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private MealReview review;
}
