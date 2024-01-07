package com.example.CourseWork.dto;
public class GuestDTO {
    private String name;
    private String surname;
    private String phonenumber;


    public GuestDTO(String name, String surname, String phonenumber) {
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
    }

    public GuestDTO(){}

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String toString() {
        return "GuestDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}
