package org.github.pelissou;
public class Epidemie {
    private float tauxTransmissionIni;
    private int periodeIncubation;
    private float probaGuerison;
    private float probaDeces;
    private double distanceMax;
    private float tauxTransmission;

    public Epidemie(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces, double distanceMax, float tauxTransmission) {
        this.tauxTransmission = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.distanceMax = distanceMax;
        
    } 
    
    public float getTauxTransmission() {
        return tauxTransmission;
    }
}
