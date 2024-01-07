package com.example.CourseWork.service.impl;

import com.example.CourseWork.dto.ReviewDTO;
import com.example.CourseWork.dto.responses.ReviewResponse;
import com.example.CourseWork.model.Guest;
import com.example.CourseWork.model.Review;
import com.example.CourseWork.repository.GuestRepository;
import com.example.CourseWork.repository.ReviewRepository;
import com.example.CourseWork.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private GuestRepository guestRepository;

    private ReviewDTO toReviewDTO(Review review){
        ReviewDTO reviewDTO;
        reviewDTO = new ReviewDTO();
        reviewDTO.setReviewBody(review.getReviewBody());
        reviewDTO.setStars(review.getStars());
        reviewDTO.setGuestName(review.getGuest().getName());
        reviewDTO.setGuestSurname(review.getGuest().getSurname());
        return reviewDTO;
    }
    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Optional<Guest> guestData = Optional.ofNullable(
                guestRepository.findGuestByNameAndSurname(reviewDTO.getGuestName(), reviewDTO.getGuestSurname()));
        if (guestData.isPresent()) {
            Guest guest = guestData.get();
            Review review = new Review();
            review.setReviewBody(reviewDTO.getReviewBody());
            review.setStars(reviewDTO.getStars());
            review.setGuest(guest);
            return toReviewDTO(reviewRepository.save(review));
        }
        return null;
    }

    @Override
    public ReviewDTO findReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()) {
            return toReviewDTO(review.get());
        }
        return null;
    }

    @Override
    public ReviewDTO updateReviewInfo(ReviewDTO reviewDTO, Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        Optional<Guest> guestData = Optional.ofNullable(
                guestRepository.findGuestByNameAndSurname(reviewDTO.getGuestName(), reviewDTO.getGuestSurname()));
        if (review.isPresent()) {
            Guest guest = guestData.get();
            if (guestData.isPresent()) {
                Review reviewModel = review.get();
                reviewModel.setStars(reviewDTO.getStars());
                reviewModel.setReviewBody(reviewDTO.getReviewBody());
                reviewModel.setGuest(guest);
                return toReviewDTO(reviewRepository.save(reviewModel));
            }
        }
        return null;
    }

    @Override
    public ReviewDTO deleteReview(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
        }
        return null;
    }

    @Override
    public ReviewResponse getAllReviews(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Review> reviews = reviewRepository.findAll(pageable);
        List<Review> listOfReviews = reviews.getContent();

        List<ReviewDTO> content = listOfReviews.stream().map(review -> toReviewDTO(review)).collect(Collectors.toList());
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setContent(content);
        reviewResponse.setPageNo(reviews.getNumber());
        reviewResponse.setPageSize(reviews.getSize());
        reviewResponse.setTotalElements(reviews.getTotalElements());
        reviewResponse.setTotalPages(reviews.getTotalPages());
        reviewResponse.setLast(reviews.isLast());

        return reviewResponse;
    }
}
