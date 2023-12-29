package com.example.CourseWork.service;

import com.example.CourseWork.dto.model.GuestDTO;
import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GuestService {
    GuestDTO registerGuest(GuestDTO guestDTO);

    GuestDTO findGuestByName(String name);

    GuestDTO updateGuestInfo(GuestDTO guestDTO);
}
