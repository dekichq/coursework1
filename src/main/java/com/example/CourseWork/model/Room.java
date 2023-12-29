package com.example.CourseWork.model;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "roomNumber")
    private int roomNumber;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    private String status;


    public Room() {
    }

    public Room(Long id, int roomNumber, float price, String status) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public float getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}
