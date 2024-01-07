package com.example.CourseWork.service;

import com.example.CourseWork.dto.ReviewDTO;
import com.example.CourseWork.dto.responses.ReviewResponse;
import com.example.CourseWork.dto.responses.RoomResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ReviewDTO createReview(ReviewDTO reviewDTO);

    ReviewDTO findReviewById(Long id);

    ReviewDTO updateReviewInfo(ReviewDTO reviewDTO, Long id);

    ReviewDTO deleteReview(Long id);

    ReviewResponse getAllReviews(int pageNo, int pageSize, String sortBy, String sortDir);
}
