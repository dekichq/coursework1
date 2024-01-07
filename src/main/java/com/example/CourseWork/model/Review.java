package com.example.CourseWork.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guest;
    @Column(name = "review_body")
    private String reviewBody;
    @Column(name = "stars")
    private int stars;

    public Review() {
    }

    public Review(Long id, Guest guest, String reviewBody, int stars) {
        this.id = id;
        this.guest = guest;
        this.reviewBody = reviewBody;
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public int getStars() {
        return stars;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", guest=" + guest +
                ", reviewBody='" + reviewBody + '\'' +
                ", stars=" + stars +
                '}';
    }
}
