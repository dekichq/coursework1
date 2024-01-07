package com.example.CourseWork.service.impl;

import com.example.CourseWork.dto.RoomDTO;
import com.example.CourseWork.dto.responses.RoomResponse;
import com.example.CourseWork.model.Guest;
import com.example.CourseWork.model.Room;
import com.example.CourseWork.repository.GuestRepository;
import com.example.CourseWork.repository.RoomRepository;
import com.example.CourseWork.service.RoomService;
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
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private GuestRepository guestRepository;

    private RoomDTO toRoomDTO(Room room){
        RoomDTO roomDTO;
        roomDTO = new RoomDTO();
        roomDTO.setStatus(room.getStatus());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setGuestId(room.getGuestId().getId());
        return roomDTO;
    }
    @Override
    public RoomDTO createRoom(RoomDTO roomDTO) {
        Optional<Guest> guestData = guestRepository.findById(roomDTO.getGuestId());
        if (guestData.isPresent()) {
            Guest guest = guestData.get();
            Room room = new Room();
            room.setPrice(roomDTO.getPrice());
            room.setStatus(roomDTO.getStatus());
            room.setGuestId(guest);
            return toRoomDTO(roomRepository.save(room));
        }
        return null;
    }

    @Override
    public RoomDTO findRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()) {
            return toRoomDTO(room.get());
        }
        return null;
    }

    @Override
    public RoomDTO updateRoomInfo(RoomDTO roomDTO, Long id) {
        Optional<Room> room = roomRepository.findById(id);
        Optional<Guest> guestData = guestRepository.findById(roomDTO.getGuestId());
        if (room.isPresent()) {
            Guest guest = guestData.get();
            if (guestData.isPresent()) {
                Room roomModel = room.get();
                roomModel.setStatus(roomDTO.getStatus());
                roomModel.setPrice(roomDTO.getPrice());
                roomModel.setGuestId(guest);
                return toRoomDTO(roomRepository.save(roomModel));
            }
        }
        return null;
    }

    @Override
    public RoomDTO deleteRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            roomRepository.delete(room.get());
        }
        return null;
    }

    @Override
    public RoomResponse getAllRooms(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Room> rooms = roomRepository.findAll(pageable);
        List<Room> listOfRooms = rooms.getContent();

        List<RoomDTO> content = listOfRooms.stream().map(room -> toRoomDTO(room)).collect(Collectors.toList());
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setContent(content);
        roomResponse.setPageNo(rooms.getNumber());
        roomResponse.setPageSize(rooms.getSize());
        roomResponse.setTotalElements(rooms.getTotalElements());
        roomResponse.setTotalPages(rooms.getTotalPages());
        roomResponse.setLast(rooms.isLast());

        return roomResponse;
    }
}
