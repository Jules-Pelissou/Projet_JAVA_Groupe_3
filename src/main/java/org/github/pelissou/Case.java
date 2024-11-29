package org.github.pelissou;

class Case {
    private int ligne;
    private int colonne;

    public Case(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    // Getters
    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
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