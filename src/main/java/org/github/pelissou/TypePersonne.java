package org.github.pelissou;

public enum TypePersonne {
    IMMUNISEE(0),
    SENSIBLE(0.3),
    NEUTRE(0.2),
    RESISTANTE(0.1);

    private final double tauxTransmission;

    TypePersonne(double tauxTransmission) {
        this.tauxTransmission = tauxTransmission;
    }

    public double getTauxTransmission() {
        return tauxTransmission;
    }
}
