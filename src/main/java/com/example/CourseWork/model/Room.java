package com.example.CourseWork.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    private String status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_id", referencedColumnName = "id")
    private Guest guestId;

    public Room() {
    }

    public Room(Long id, float price, String status, Guest guestId) {
        this.id = id;
        this.price = price;
        this.status = status;
        this.guestId = guestId;
    }

    public Long getId() {
        return id;
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

    public Guest getGuestId() {
        return guestId;
    }

    public void setGuestId(Guest guestId) {
        this.guestId = guestId;
    }
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", guestId=" + guestId +
                '}';
    }
}
