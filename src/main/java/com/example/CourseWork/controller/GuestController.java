package com.example.CourseWork.controller;

import java.util.Optional;

import com.example.CourseWork.dto.GuestDTO;
import com.example.CourseWork.dto.responses.GuestResponse;
import com.example.CourseWork.service.GuestService;
import com.example.CourseWork.utils.ApplicationConstants;
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

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    GuestService guestService;

    @GetMapping("")
    public GuestResponse getAllGuests(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return guestService.getAllGuests(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable("id") Long id) {
        Optional<GuestDTO> guestDTO = Optional.ofNullable(guestService.findGuestById(id));
        if (guestDTO.isPresent()) {
            return new ResponseEntity<>(guestDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guestDTO) {
            return new ResponseEntity<>(guestService.registerGuest(guestDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable("id") Long id, @RequestBody GuestDTO guestDTO) {
        Optional<GuestDTO> guestData = Optional.ofNullable(guestService.findGuestById(id));
        if (guestData.isPresent()) {
           guestService.updateGuestInfo(guestDTO, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGuest(@PathVariable("id") Long id) {
        try {
            guestService.deleteGuest(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}