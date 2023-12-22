package com.example.CourseWork.service;

import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {

    private final GuestRepository repository;

    public GuestService(GuestRepository repository) {
        this.repository = repository;
    }

    public List<Guest> getGuests() {
        return repository.findAll();
    }

    public Guest getGuestById(Long id) {
        List<Guest> guestsList = repository.findAll();
        for (Guest guest : guestsList) {
            if (guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }

    public Guest getGuestByName(String name) {
        List<Guest> guestsList = repository.findAll();
        for (Guest guest : guestsList) {
            if (guest.getName().equals(name)) {
                return guest;
            }
        }
        return null;
    }

    public void createGuest(Guest guest) {
        repository.save(guest);
    }

    public void updateGuest(Guest guest, Long id) {
        repository.findAll().set(id.intValue(), guest);
    }
}
