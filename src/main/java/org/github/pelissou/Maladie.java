package org.github.pelissou;

public class Maladie {
    private double tauxTransmission;
    private int periodeIncubation;
    private double probaGuerison;
    private double probaDeces;
    private int dureeResistance;

    public Maladie(double tauxTransmission, int periodeIncubation, double probaGuerison, double probaDeces, int dureeResistance) {
        this.tauxTransmission = tauxTransmission;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.dureeResistance = dureeResistance;
    }

    public double getTauxTransmission() {
        return tauxTransmission;
    }

    public double getProbaGuerison() {
        return probaGuerison;
    }

    public double getProbaDeces() {
        return probaDeces;
    }

    public int getDureeResistance() {
        return dureeResistance;
    }
}

