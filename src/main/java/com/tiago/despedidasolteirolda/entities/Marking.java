package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.entities.enums.MarkingStates;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Marking {
    private Set<Service> servicesApplied;
    private MarkingStates currentState;
    private MarkingHistoric markingHistoric;
    private Date markingDay;

    public Marking() {
        servicesApplied = new HashSet<>();
        currentState = MarkingStates.PENDENT;
        markingHistoric = new MarkingHistoric();
        markingHistoric.addState(currentState);
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

    public Date getMarkingDay() {
        return markingDay;
    }

    public void setMarkingDay(Date markingDay) {
        this.markingDay = markingDay;
    }

    public MarkingStates getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MarkingStates newState) {
        currentState = newState;
        markingHistoric.addState(currentState);
    }
}
