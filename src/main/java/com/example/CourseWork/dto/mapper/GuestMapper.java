package com.example.CourseWork.dto.mapper;

import com.example.CourseWork.dto.model.GuestDTO;
import com.example.CourseWork.model.Guest;
import org.springframework.stereotype.Component;

@Component
public class GuestMapper {
    public static GuestDTO toGuestDTO(Guest guest) {
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setName(guest.getName());
        guestDTO.setSurname(guest.getSurname());
        guestDTO.setPhonenumber(guest.getPhonenumber());


        return guestDTO;
    }
}
