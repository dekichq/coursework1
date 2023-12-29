package com.example.CourseWork.repository;

import com.example.CourseWork.model.Guest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
public interface GuestRepository extends JpaRepository<Guest, Long>{
    Optional<Guest> findById(Long id);

    Guest findByNameContaining(String name);

}
