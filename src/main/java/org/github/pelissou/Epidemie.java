package org.github.pelissou;
public class Epidemie {
    private float tauxTransmission;
    private int periodeIncubation;
    private float probaGuerison;
    private float probaDeces;
    private int distanceMax;
    private float probaComtamination;

    public Epidemie(float tauxTransmission, int periodeIncubation, float probaGuerison, float probaDeces, int distanceMax, float probaComtamination) {
        this.tauxTransmission = tauxTransmission;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.distanceMax = distanceMax;
        this.probaComtamination = probaComtamination;
    } 

    public float getTauxTransmission() {
        return tauxTransmission;
    }
}
