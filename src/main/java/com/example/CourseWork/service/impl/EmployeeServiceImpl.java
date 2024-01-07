package com.example.CourseWork.service.impl;

import com.example.CourseWork.dto.EmployeeDTO;
import com.example.CourseWork.dto.responses.EmployeeResponse;
import com.example.CourseWork.model.Employee;
import com.example.CourseWork.model.Role;
import com.example.CourseWork.repository.EmployeeRepository;
import com.example.CourseWork.repository.RoleRepository;
import com.example.CourseWork.service.EmployeeService;
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
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;

    private EmployeeDTO toEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO;
        employeeDTO = new EmployeeDTO();
        employeeDTO.setName(employee.getName());
        employeeDTO.setSurname(employee.getSurname());
        employeeDTO.setRole(employee.getRole().getName());
        return employeeDTO;
    }
    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Optional<Role> roleData = Optional.ofNullable(roleRepository.findByName(employeeDTO.getRole()));
        if (roleData.isPresent()) {
            Role role = roleData.get();
            Employee employee = new Employee();
            employee.setName(employeeDTO.getName());
            employee.setSurname(employeeDTO.getSurname());
            employee.setRole(role);
            return toEmployeeDTO(employeeRepository.save(employee));
        }
        return null;
    }

    @Override
    public EmployeeDTO findEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()) {
            return toEmployeeDTO(employee.get());
        }
        return null;
    }

    @Override
    public EmployeeDTO updateEmployeeInfo(EmployeeDTO employeeDTO, Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Optional<Role> roleData = Optional.ofNullable(roleRepository.findByName(employeeDTO.getRole()));
        if (employee.isPresent()) {
            Role role = roleData.get();
            if (roleData.isPresent()) {
                Employee employeeModel = employee.get();
                employeeModel.setName(employeeDTO.getName());
                employeeModel.setSurname(employeeDTO.getSurname());
                employeeModel.setRole(role);
                return toEmployeeDTO(employeeRepository.save(employeeModel));
            }
        }
        return null;
    }

    @Override
    public EmployeeDTO deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
        }
        return null;
    }

    @Override
    public EmployeeResponse getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Employee> employees = employeeRepository.findAll(pageable);
        List<Employee> listOfEmployees = employees.getContent();

        List<EmployeeDTO> content = listOfEmployees.stream().map(employee -> toEmployeeDTO(employee))
                .collect(Collectors.toList());
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setContent(content);
        employeeResponse.setPageNo(employees.getNumber());
        employeeResponse.setPageSize(employees.getSize());
        employeeResponse.setTotalElements(employees.getTotalElements());
        employeeResponse.setTotalPages(employees.getTotalPages());
        employeeResponse.setLast(employees.isLast());

        return employeeResponse;
    }
}
