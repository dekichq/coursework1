package com.example.CourseWork.controller;

import com.example.CourseWork.dto.RoomDTO;
import com.example.CourseWork.dto.responses.RoomResponse;
import com.example.CourseWork.service.RoomService;
import com.example.CourseWork.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("")
    public RoomResponse getAllRooms(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return roomService.getAllRooms(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable("id") Long id) {
        Optional<RoomDTO> roomDTO = Optional.ofNullable(roomService.findRoomById(id));
        if (roomDTO.isPresent()) {
            return new ResponseEntity<>(roomDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        return new ResponseEntity<>(roomService.createRoom(roomDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable("id") Long id, @RequestBody RoomDTO roomDTO) {
        Optional<RoomDTO> roomData = Optional.ofNullable(roomService.findRoomById(id));
        if (roomData.isPresent()) {
            roomService.updateRoomInfo(roomDTO, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable("id") Long id) {
        try {
            roomService.deleteRoom(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
