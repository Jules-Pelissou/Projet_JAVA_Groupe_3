package org.github.pelissou;

public enum TypePersonne {
    SENSIBLE(0.3),
    NEUTRE(0.2),
    RESISTANTE(0.1),
    IMMUNISEE(0);

    private final double tauxTransmission;

    TypePersonne(double tauxTransmission) {
        this.tauxTransmission = tauxTransmission;
    }

    public double getTauxTransmission() {
        return tauxTransmission;
    }
}
