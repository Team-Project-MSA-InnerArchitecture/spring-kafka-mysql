package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
@Entity
public class MealReview {
    @Id @GeneratedValue
    private Long id;

    private String mealdayInfo;
    private String mealType;
    private String overallOpinion;
    private String freeOpinion;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<MenuRating> menuRatings = new ArrayList<>();
}
