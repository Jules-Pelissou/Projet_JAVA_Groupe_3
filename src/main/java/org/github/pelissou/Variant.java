package org.github.pelissou;
public class Variant extends Epidemie{
    public Variant(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces, double distanceMax, float tauxTransmission, int cycles) {
        super(tauxTransmissionIni, periodeIncubation, probaGuerison, probaDeces, distanceMax, tauxTransmission, cycles);
        this.tauxTransmissionIni = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
    }

    public float getTauxTransmission() {
        return super.getTauxTransmission();
    }
}
