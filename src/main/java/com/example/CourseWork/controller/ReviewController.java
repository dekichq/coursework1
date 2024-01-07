package com.example.CourseWork.controller;

import com.example.CourseWork.dto.ReviewDTO;
import com.example.CourseWork.dto.responses.ReviewResponse;
import com.example.CourseWork.service.ReviewService;
import com.example.CourseWork.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("")
    public ReviewResponse getAllReviews(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return reviewService.getAllReviews(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable("id") Long id) {
        Optional<ReviewDTO> reviewDTO = Optional.ofNullable(reviewService.findReviewById(id));
        if (reviewDTO.isPresent()) {
            return new ResponseEntity<>(reviewDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(reviewService.createReview(reviewDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable("id") Long id, @RequestBody ReviewDTO reviewDTO) {
        Optional<ReviewDTO> reviewData = Optional.ofNullable(reviewService.findReviewById(id));
        if (reviewData.isPresent()) {
            reviewService.updateReviewInfo(reviewDTO, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") Long id) {
        try {
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
