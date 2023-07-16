package com.tiago.despedidasolteirolda.entities.enums;

import java.io.Serializable;

public enum Localidades implements Serializable {
    Aveiro("Aveiro"),
    Açores("Açores"),
    Beja("Beja"),
    Braga("Braga"),
    Bragança("Bragança"),
    Castelo_Branco("Castelo Branco"),
    Coimbra("Coimbra"),
    Évora("Évora"),
    Faro("Faro"),
    Guarda("Guarda"),
    Leiria("Leiria"),
    Lisboa("Lisboa"),
    Madeira("Madeira"),
    Portalegre("Portalegre"),
    Porto("Porto"),
    Santarém("Santarém"),
    Setúbal("Setúbal"),
    Viana_do_Castelo("Viana do Castelo"),
    Vila_Real("Vila Real"),
    Viseu("Viseu");

    private String label;

    Localidades(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
