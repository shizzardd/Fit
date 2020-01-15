package com.example.goodproject.Models;

public class User {
    private String firsName, name, middleName, phone, birthday, email, pass;
    public User() {}

    public User(String firsName, String name, String middleName, String phone, String birthday, String email, String pass) {
        this.firsName = firsName;
        this.name = name;
        this.middleName = middleName;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.pass = pass;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
