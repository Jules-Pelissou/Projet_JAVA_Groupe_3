package org.github.pelissou;
public class Epidemie {
    private float tauxTransmissionIni;
    private int periodeIncubation;
    private float probaGuerison;
    private float probaDeces;
    private double distanceMax;
    private float tauxTransmission;
    private static int cycles;

    public Epidemie(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces, double distanceMax, float tauxTransmission, int cycles) {
        this.tauxTransmission = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.distanceMax = distanceMax;
        this.cycles = cycles;
    } 
    
    public float getTauxTransmission() {
        return tauxTransmission;
    }

    public static int getCycles() {
        return cycles;
    }
}
