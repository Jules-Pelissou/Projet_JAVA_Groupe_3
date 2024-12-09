package org.github.pelissou;

import java.util.ArrayList;
import java.util.List;

public class Maladie {
    private double tauxTransmission;
    private int periodeIncubation;
    private double probaGuerison;
    private double probaDeces;
    private int dureeResistance;
    private List<Variant> variants;
    private Variant variantActuel;

    public Maladie(double tauxTransmission, int periodeIncubation, double probaGuerison, double probaDeces, int dureeResistance) {
        this.tauxTransmission = tauxTransmission;
        this.periodeIncubation = periodeIncubation;
        this.probaGuerison = probaGuerison;
        this.probaDeces = probaDeces;
        this.dureeResistance = dureeResistance;
        this.variants = new ArrayList<>();
        this.variantActuel = null;
    }

    public void ajouterVariant(Variant variant) {
        variants.add(variant);
    }

    public void activerVariant(String nomVariant) {
        for (Variant variant : variants) {
            if (variant.getNom().equalsIgnoreCase(nomVariant)) {
                variantActuel = variant;
                this.tauxTransmission = variant.getTauxTransmission();
                this.periodeIncubation = variant.getPeriodeIncubation();
                this.probaGuerison = variant.getProbaGuerison();
                this.probaDeces = variant.getProbaDeces();
                break;
            }
        }
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

    public Variant getVariantActuel() {
        return variantActuel;
    }

    public int getDureeResistance() {
        return dureeResistance;
    }
}



