package com.example.CourseWork.dto;

import lombok.*;


public class RoomDTO {
    private float price;
    private String status;
    private Long guestId;

    public RoomDTO() {
    }

    public RoomDTO(float price, String status, Long guestId) {
        this.price = price;
        this.status = status;
        this.guestId = guestId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    @Override
    public String toString() {
        return "RoomDTO{" +
                "price=" + price +
                ", status='" + status + '\'' +
                ", guestId=" + guestId +
                '}';
    }
}
