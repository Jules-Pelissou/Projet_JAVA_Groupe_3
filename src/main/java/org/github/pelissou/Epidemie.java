package org.github.pelissou;
public class Epidemie {
    protected float tauxTransmissionIni;
    protected int periodeIncubation;
    private float probaGuerison;
    private float probaDeces;
    private double distanceMax;
    private static int cycles;

    public Epidemie(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces, double distanceMax, float tauxTransmission, int cycles) {
        this.tauxTransmissionIni = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.distanceMax = distanceMax;
        Epidemie.cycles = cycles;
    } 

    public static int getCycles() {
        return cycles;
    }

    public double calculTauxTransmission(Case c1, Case c2) {
        return Math.max(0, tauxTransmissionIni*(1-Population.calculDistance(c1, c2)/distanceMax));
    }
}
