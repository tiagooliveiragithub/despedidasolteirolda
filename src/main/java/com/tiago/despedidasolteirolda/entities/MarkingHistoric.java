package com.tiago.despedidasolteirolda.entities;

import com.tiago.despedidasolteirolda.entities.enums.MarkingStates;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class MarkingHistoric {
    private Map<MarkingStates, Date> stateHistory;

    public MarkingHistoric() {
        stateHistory = new LinkedHashMap<>();
    }

    public void addState(MarkingStates state) {
        Date currentDate = new Date();
        stateHistory.put(state, currentDate);
    }

    public Map<MarkingStates, Date> getStateHistory() {
        return stateHistory;
    }
}
