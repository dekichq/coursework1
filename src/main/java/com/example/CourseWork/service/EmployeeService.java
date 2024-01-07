package com.example.CourseWork.service;

import com.example.CourseWork.dto.EmployeeDTO;
import com.example.CourseWork.dto.responses.EmployeeResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO findEmployeeById(Long id);

    EmployeeDTO updateEmployeeInfo(EmployeeDTO employeeDTO, Long id);

    EmployeeDTO deleteEmployee(Long id);

    EmployeeResponse getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir);
}
