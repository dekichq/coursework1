package com.example.CourseWork.service;

import com.example.CourseWork.dto.RoomDTO;
import com.example.CourseWork.dto.responses.RoomResponse;
import com.example.CourseWork.model.Room;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {
    RoomDTO createRoom(RoomDTO roomDTO);

    RoomDTO findRoomById(Long id);

    RoomDTO updateRoomInfo(RoomDTO roomDTO, Long id);

    RoomDTO deleteRoom(Long id);

    RoomResponse getAllRooms(int pageNo, int pageSize, String sortBy, String sortDir);
}
