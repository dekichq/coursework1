package com.example.CourseWork.service.impl;

import com.example.CourseWork.dto.GuestDTO;
import com.example.CourseWork.dto.RoleDTO;
import com.example.CourseWork.dto.responses.GuestResponse;
import com.example.CourseWork.dto.responses.RoleResponse;
import com.example.CourseWork.model.Guest;
import com.example.CourseWork.model.Review;
import com.example.CourseWork.model.Role;
import com.example.CourseWork.repository.RoleRepository;
import com.example.CourseWork.service.RoleService;
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
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    private RoleDTO toRoleDTO(Role role){
        RoleDTO roleDTO;
        roleDTO = new RoleDTO();
        roleDTO.setName(role.getName());
        return roleDTO;
    }
    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
            Role role = new Role();
            role.setName(roleDTO.getName());
            return toRoleDTO(roleRepository.save(role));
    }

    @Override
    public RoleDTO findRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()) {
            return toRoleDTO(role.get());
        }
        return null;
    }

    @Override
    public RoleDTO updateRoleInfo(RoleDTO roleDTO, Long id) {
        Optional<Role> role = roleRepository.findById(id);
        if (role.isPresent()) {
            Role roleModel = role.get();
            roleModel.setName(roleDTO.getName());
            return toRoleDTO(roleRepository.save(roleModel));
        }
        return null;
    }

    @Override
    public RoleDTO deleteRole(Long id) {
        Optional<Role> roleData = roleRepository.findById(id);
        if (roleData.isPresent()) {
            roleRepository.delete(roleData.get());
        }
        return null;
    }

    @Override
    public RoleResponse getAllRoles(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Role> roles = roleRepository.findAll(pageable);
        List<Role> listOfRoles = roles.getContent();

        List<RoleDTO> content = listOfRoles.stream().map(role -> toRoleDTO(role)).collect(Collectors.toList());
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setContent(content);
        roleResponse.setPageNo(roles.getNumber());
        roleResponse.setPageSize(roles.getSize());
        roleResponse.setTotalElements(roles.getTotalElements());
        roleResponse.setTotalPages(roles.getTotalPages());
        roleResponse.setLast(roles.isLast());

        return roleResponse;
    }
}
