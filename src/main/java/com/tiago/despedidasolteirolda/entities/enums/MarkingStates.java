package com.tiago.despedidasolteirolda.entities.enums;

import java.io.Serializable;

public enum MarkingStates implements Serializable {
    PENDENT("Pedente"),
    WAITING("Em espera"),
    FINISHED("Completa");

    private final String stateValue;

    MarkingStates(String stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateValue() {
        return stateValue;
    }
}
