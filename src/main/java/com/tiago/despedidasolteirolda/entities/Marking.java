package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.data.FileManager;
import com.tiago.despedidasolteirolda.entities.enums.MarkingStates;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Marking implements Serializable {
    private int id;
    private Person client;
    private Set<Service> servicesApplied;
    private MarkingStates currentState;
    private MarkingHistoric markingHistoric;
    private LocalDate markingDay;
    private Float price;
    private int rate;

    public Marking() {
        client = Session.user;
        servicesApplied = new HashSet<>();
        currentState = MarkingStates.PENDENT;
        markingHistoric = new MarkingHistoric();
        markingHistoric.addState(currentState);
    }

    public void create(){
        id = FileManager.getFileManager().getMarkings().size();
        FileManager.getFileManager().getMarkings().put(this.id, this);
        System.out.println("Marcação criado com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    public void update(){
        FileManager.getFileManager().getMarkings().put(this.id, this);
        System.out.println("Marcação atualizada com sucesso!!!");
        FileManager.getFileManager().serialize("src\\main\\resources\\data\\info.repo");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Set<Service> getServicesApplied() {
        return servicesApplied;
    }

    public void setServicesApplied(Set<Service> servicesApplied) {
        this.servicesApplied = servicesApplied;
    }

    public MarkingHistoric getMarkingHistoric() {
        return markingHistoric;
    }

    public void setMarkingHistoric(MarkingHistoric markingHistoric) {
        this.markingHistoric = markingHistoric;
    }

    public LocalDate getMarkingDay() {
        return markingDay;
    }

    public void setMarkingDay(LocalDate markingDay) {
        this.markingDay = markingDay;
    }

    public MarkingStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MarkingStates newState) {
        currentState = newState;
        markingHistoric.addState(currentState);
    }

    public float getPrice() {
        float total = 0;
        for(Service service: servicesApplied) {
            total += service.getPrice() * 1.35;
        }
        return total;
    }
}
