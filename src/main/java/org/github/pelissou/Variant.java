package org.github.pelissou;
public class Variant extends Epidemie{
    public Variant(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces, double distanceMax, float tauxTransmission) {
        super(probaGuerison, periodeIncubation, probaDeces, tauxTransmission, distanceMax, tauxTransmission);
        this.tauxTransmissionIni = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
    }

    public float getTauxTransmission() {
        return tauxTransmission;
    }
}
