package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.enums.Localidades;

import java.io.Serializable;
import java.util.Locale;

public class Service implements Serializable {
    private int id;
    private String name;
    private String phoneNumber;
    private int duration;
    private Localidades local;
    private double price;
    private int IVA;
    private String description;
    private boolean active;
    private Person owner;

    public Service() {
        active = false;
    }

    public void create(){
        id = FileManager.getFileManager().getServices().size();
        FileManager.getFileManager().getServices().put(this.id, this);
        System.out.println("Serviço criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    public void update(){
        FileManager.getFileManager().getServices().put(this.id, this);
        System.out.println("Serviço atualizada com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Localidades getLocal() {
        return local;
    }

    public void setLocal(Localidades local) {
        this.local = local;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIVA() {
        return IVA;
    }

    public void setIVA(int IVA) {
        this.IVA = IVA;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String isActive() {
        if(active) return "Ativado";
        else return "Desativado";
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
