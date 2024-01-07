package com.example.CourseWork.controller;

import com.example.CourseWork.dto.EmployeeDTO;
import com.example.CourseWork.dto.responses.EmployeeResponse;
import com.example.CourseWork.service.EmployeeService;
import com.example.CourseWork.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("")
    public EmployeeResponse getAllEmployees(
            @RequestParam(value = "pageNo", defaultValue = ApplicationConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ApplicationConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApplicationConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApplicationConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir)
    {
        return employeeService.getAllEmployees(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        Optional<EmployeeDTO> employeeDTO = Optional.ofNullable(employeeService.findEmployeeById(id));
        if (employeeDTO.isPresent()) {
            return new ResponseEntity<>(employeeDTO.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id,
                                                      @RequestBody EmployeeDTO employeeDTO) {
        Optional<EmployeeDTO> employeeData = Optional.ofNullable(employeeService.findEmployeeById(id));
        if (employeeData.isPresent()) {
            employeeService.updateEmployeeInfo(employeeDTO, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
