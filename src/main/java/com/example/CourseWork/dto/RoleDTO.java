package com.example.CourseWork.dto;

public class RoleDTO {
    private String name;

    public RoleDTO() {
    }
    public RoleDTO(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "RoleDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
