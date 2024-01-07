package com.example.CourseWork.dto;

public class EmployeeDTO {
    private String name;
    private String surname;
    private String role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
