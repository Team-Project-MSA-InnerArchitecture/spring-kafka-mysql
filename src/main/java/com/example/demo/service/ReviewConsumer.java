package com.example.demo.service;

import com.example.kafka_schemas.ReviewEvent;
import com.example.demo.model.MealReview;
import com.example.demo.model.MenuRating;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;

@Service
public class ReviewConsumer {
    @Autowired
    private MealReviewRepository mealReviewRepository;

    @Autowired
    private MenuRatingRepository menuRatingRepository;

    @KafkaListener(topics = "review.events", groupId = "review-consumer-view")
    @Transactional
    public void consume(ReviewEvent event) {
        System.out.println("Kafka message consumed: " + event);
        MealReview review = new MealReview();
        review.setMealdayInfo(event.getMealdayInfo());
        review.setMealType(event.getMealType());
        review.setOverallOpinion(event.getOverallOpinion() != null ? event.getOverallOpinion() : null);
        review.setFreeOpinion(event.getFreeOpinion() != null ? event.getFreeOpinion() : null);
        mealReviewRepository.save(review);

        event.getMenuRatings().forEach((menu, rating) -> {
            MenuRating r = new MenuRating();
            r.setMenuName(menu);
            r.setRating(rating);
            r.setQuestion(event.getMenuQuestions().get(menu));
            r.setAnswer(event.getMenuAnswers().get(menu));
            r.setReview(review);
            menuRatingRepository.save(r);
            System.out.println("Saved MenuRating: " + r.getMenuName() + ", rating=" + r.getRating());
        });
    }
}
