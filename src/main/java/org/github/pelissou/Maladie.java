package org.github.pelissou;

public class Maladie {
    protected float tauxTransmissionIni;
    protected int periodeIncubation;
    private float probaGuerison;
    private float probaDeces;
    private double distanceMax;
    private static int cycles;
    private static double tauxTransmission;

    public Maladie(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces,
            double distanceMax, int cycles) {
        this.tauxTransmissionIni = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.distanceMax = distanceMax;
        Maladie.cycles = cycles;
    }

    public static int getCycles() {
        return cycles;
    }

    public double calculProbaContagion(Case c1, Case c2) {
        return Math.max(0, tauxTransmissionIni * (1 - Population.calculDistance(c1, c2) / distanceMax));
    }
}
