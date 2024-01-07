package com.example.CourseWork.service;

import com.example.CourseWork.dto.GuestDTO;
import com.example.CourseWork.dto.responses.GuestResponse;
import org.springframework.stereotype.Service;

@Service
public interface GuestService {
    GuestDTO registerGuest(GuestDTO guestDTO);

    GuestDTO findGuestById(Long id);

    GuestDTO updateGuestInfo(GuestDTO guestDTO, Long id);

    GuestDTO deleteGuest(Long id);

    GuestResponse getAllGuests(int pageNo, int pageSize, String sortBy, String sortDir);

}
