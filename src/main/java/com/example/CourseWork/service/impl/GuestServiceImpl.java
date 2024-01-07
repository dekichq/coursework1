package com.example.CourseWork.service.impl;

import com.example.CourseWork.dto.GuestDTO;
import com.example.CourseWork.dto.responses.GuestResponse;
import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;
import com.example.CourseWork.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;
    private GuestDTO toGuestDTO(Guest guest){
        GuestDTO guestDTO;
        guestDTO = new GuestDTO();
        guestDTO.setName(guest.getName());
        guestDTO.setSurname(guest.getSurname());
        guestDTO.setPhonenumber(guest.getPhonenumber());
        return guestDTO;
    }
    @Override
    public GuestDTO registerGuest(GuestDTO guestDTO) {
        Guest guest = new Guest(guestDTO.getName(), guestDTO.getSurname(), guestDTO.getPhonenumber());
        return toGuestDTO(guestRepository.save(guest));
    }

    @Override
    public GuestDTO findGuestById(Long id) {
        Optional<Guest> guest = guestRepository.findById(id);
        if(guest.isPresent()) {
            return toGuestDTO(guest.get());
        }
        return null;
    }

    @Override
    public GuestDTO updateGuestInfo(GuestDTO guestDTO, Long id) {
        Optional<Guest> guest = guestRepository.findById(id);
        if (guest.isPresent()) {
            Guest guestModel = guest.get();
            guestModel.setName(guestDTO.getName());
            guestModel.setSurname(guestDTO.getSurname());
            guestModel.setPhonenumber(guestDTO.getPhonenumber());
            return toGuestDTO(guestRepository.save(guestModel));
        }
        return null;
    }

    @Override
    public GuestDTO deleteGuest(Long id) {
        Optional<Guest> guestData = guestRepository.findById(id);
        if (guestData.isPresent()) {
            guestRepository.delete(guestData.get());
        }
        return null;
    }

    @Override
    public GuestResponse getAllGuests(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Guest> guests = guestRepository.findAll(pageable);
        List<Guest> listOfGuests = guests.getContent();

        List<GuestDTO> content = listOfGuests.stream().map(guest -> toGuestDTO(guest)).collect(Collectors.toList());
        GuestResponse guestResponse = new GuestResponse();
        guestResponse.setContent(content);
        guestResponse.setPageNo(guests.getNumber());
        guestResponse.setPageSize(guests.getSize());
        guestResponse.setTotalElements(guests.getTotalElements());
        guestResponse.setTotalPages(guests.getTotalPages());
        guestResponse.setLast(guests.isLast());

        return guestResponse;
    }

}
