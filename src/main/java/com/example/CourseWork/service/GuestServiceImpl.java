package com.example.CourseWork.service;

import com.example.CourseWork.dto.mapper.GuestMapper;
import com.example.CourseWork.dto.model.GuestDTO;
import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class GuestServiceImpl implements GuestService{

    private GuestRepository guestRepository;
    private ModelMapper modelMapper;
    @Override
    public GuestDTO registerGuest(GuestDTO guestDTO) {
        Guest guest = guestRepository.findByNameContaining(guestDTO.getName());
        guest = new Guest(guestDTO.getName(), guestDTO.getSurname(), guestDTO.getPhonenumber());
        return GuestMapper.toGuestDTO(guestRepository.save(guest));
    }

    @Override
    public GuestDTO findGuestByName(String name) {
        Optional<Guest> guest = Optional.ofNullable(guestRepository.findByNameContaining(name));
        if(guest.isPresent()) {
            return modelMapper.map(guest.get(), GuestDTO.class);
        }
        return null;
    }

    @Override
    public GuestDTO updateGuestInfo(GuestDTO guestDTO) {
        Optional<Guest> guest = Optional.ofNullable(guestRepository.findByNameContaining(guestDTO.getName()));
        if (guest.isPresent()) {
            Guest guestModel = guest.get();
            guestModel.setName(guestDTO.getName());
            guestModel.setSurname(guestDTO.getSurname());
            guestModel.setPhonenumber(guestDTO.getPhonenumber());
            return GuestMapper.toGuestDTO(guestRepository.save(guestModel));
        }
        return null;
    }
}
