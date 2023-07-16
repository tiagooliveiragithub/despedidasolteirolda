package com.tiago.despedidasolteirolda.entities;

import javafx.scene.Parent;

import java.io.IOException;
import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private String birthDate;
    private String address;
    private String phoneNumber;
    private String NIF;
    private String username;
    private String password;
    private Permissions permissions;
    private Boolean userActive;
    public Person() {
        userActive = true;
    }

    public abstract Parent loadMenu() throws IOException;

    public abstract void create();

    public void update() {
        create();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return name;
    }

    public Boolean getUserActive() {
        return userActive;
    }

    public void setUserActive(Boolean userActive) {
        this.userActive = userActive;
    }

    public String getUserActiveString() {
        if (userActive) return "Ativado";
        else return "Desativado";
    }
}
