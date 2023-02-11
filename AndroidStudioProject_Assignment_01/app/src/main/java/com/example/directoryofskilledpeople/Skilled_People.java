package com.example.directoryofskilledpeople;

public class Skilled_People {
    String id;
    String name;
    String address;
    String title;
    String phone;
    String email;
    String nationality;

    public Skilled_People(String id, String name, String address, String title, String phone, String email, String nationality) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
