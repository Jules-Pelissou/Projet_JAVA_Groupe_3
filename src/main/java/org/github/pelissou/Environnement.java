package org.github.pelissou;

public class Environnement {

    private double x_min = 0;
    private double x_max;
    private double y_min = 0;
    private double y_max;

    public Environnement(double longueur, double largeur) {
        this.x_max = longueur;
        this.y_max = largeur;
    }
}
