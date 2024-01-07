package com.example.CourseWork.controller;

import com.example.CourseWork.dto.RoleDTO;
import com.example.CourseWork.dto.responses.RoleResponse;
import com.example.CourseWork.service.RoleService;
import com.example.CourseWork.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("")
    public RoleResponse getAllRoles(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return roleService.getAllRoles(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable("id") Long id) {
        Optional<RoleDTO> roleDTO = Optional.ofNullable(roleService.findRoleById(id));
        if (roleDTO.isPresent()) {
            return new ResponseEntity<>(roleDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        return new ResponseEntity<>(roleService.createRole(roleDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) {
        Optional<RoleDTO> roleData = Optional.ofNullable(roleService.findRoleById(id));
        if (roleData.isPresent()) {
            roleService.updateRoleInfo(roleDTO, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("id") Long id) {
        try {
            roleService.deleteRole(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
