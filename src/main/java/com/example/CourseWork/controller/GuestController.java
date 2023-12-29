package com.example.CourseWork.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.CourseWork.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CourseWork.model.Guest;
import com.example.CourseWork.repository.GuestRepository;




@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    GuestRepository guestRepository;

   /* @GetMapping("")
    public ResponseEntity<List<Guest>> getAllGuests(@RequestParam(required = false) String name) {
        try {
            List<Guest> guests = new ArrayList<Guest>();

            if (name == null)
                guestRepository.findAll().forEach(guests::add);
            else
                guestRepository.findByNameContaining(name).forEach(guests::add);

            if (guests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(guests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getTutorialById(@PathVariable("id") Long id) {
        Optional<Guest> guestData = guestRepository.findById(id);

        if (guestData.isPresent()) {
            return new ResponseEntity<>(guestData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        try {
            Guest _guest = guestRepository
                    .save(new Guest(guest.getName(), guest.getSurname(), guest.getPhonenumber()));
            return new ResponseEntity<>(_guest, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable("id") Long id, @RequestBody Guest guest) {
        Optional<Guest> guestData = guestRepository.findById(id);

        if (guestData.isPresent()) {
            Guest _guest = guestData.get();
            _guest.setName(guest.getName());
            _guest.setSurname(guest.getSurname());
            _guest.setPhonenumber((guest.getPhonenumber()));
            return new ResponseEntity<>(guestRepository.save(_guest), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGuest(@PathVariable("id") Long id) {
        try {
            guestRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllGuests() {
        try {
            guestRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}