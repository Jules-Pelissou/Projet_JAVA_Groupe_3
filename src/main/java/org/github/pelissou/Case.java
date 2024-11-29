package org.github.pelissou;

class Case {
    private double ligne;
    private double colonne;

    public Case(double ligne, double colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Getters
    public double getLigne() {
        return ligne;
    }

    public double getColonne() {
        return colonne;
    }

    @Override
    public String toString() {
        return "Case{" +
                "ligne=" + ligne +
                ", colonne=" + colonne +
                '}';
    }
}