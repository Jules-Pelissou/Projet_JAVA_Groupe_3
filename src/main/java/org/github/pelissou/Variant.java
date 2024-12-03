package org.github.pelissou;
public class Variant extends Maladie{
    public Variant(float tauxTransmissionIni, int periodeIncubation, float probaGuerison, float probaDeces, double distanceMax, float tauxTransmission, int cycles) {
        super(tauxTransmissionIni, periodeIncubation, probaGuerison, probaDeces, distanceMax, cycles);
        this.tauxTransmissionIni = tauxTransmissionIni;
        this.periodeIncubation = periodeIncubation;
    }
}
