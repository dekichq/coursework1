package com.example.CourseWork.service;

import com.example.CourseWork.dto.ReviewDTO;
import com.example.CourseWork.dto.RoleDTO;
import com.example.CourseWork.dto.responses.ReviewResponse;
import com.example.CourseWork.dto.responses.RoleResponse;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO findRoleById(Long id);

    RoleDTO updateRoleInfo(RoleDTO roleDTO, Long id);

    RoleDTO deleteRole(Long id);

    RoleResponse getAllRoles(int pageNo, int pageSize, String sortBy, String sortDir);
}
