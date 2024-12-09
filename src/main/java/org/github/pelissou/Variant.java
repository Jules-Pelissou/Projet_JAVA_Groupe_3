package org.github.pelissou;

public class Variant {
    private String nom;
    private double tauxTransmission;
    private int periodeIncubation;
    private double probaGuerison;
    private double probaDeces;

    public Variant(String nom, double tauxTransmission, int periodeIncubation, double probaGuerison, double probaDeces) {
        this.nom = nom;
        this.tauxTransmission = tauxTransmission;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
    }

    public String getNom() {
        return nom;
    }

    public double getTauxTransmission() {
        return tauxTransmission;
    }

    public int getPeriodeIncubation() {
        return periodeIncubation;
    }

    public double getProbaGuerison() {
        return probaGuerison;
    }

    public double getProbaDeces() {
        return probaDeces;
    }
}

