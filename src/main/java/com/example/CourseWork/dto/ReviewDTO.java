package com.example.CourseWork.dto;
public class ReviewDTO {
    private String guestName;
    private String guestSurname;
    private String reviewBody;
    private int stars;

    public ReviewDTO() {
    }

    public ReviewDTO(String guestName, String guestSurname, String reviewBody, int stars) {
        this.guestName = guestName;
        this.guestSurname = guestSurname;
        this.reviewBody = reviewBody;
        this.stars = stars;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getReviewBody() {
        return reviewBody;
    }

    public void setReviewBody(String reviewBody) {
        this.reviewBody = reviewBody;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getGuestSurname() {
        return guestSurname;
    }

    public void setGuestSurname(String guestSurname) {
        this.guestSurname = guestSurname;
    }
}
