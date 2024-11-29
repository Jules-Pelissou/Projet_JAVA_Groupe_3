package org.github.pelissou;

import java.util.Random;

public class Personne {

    // Attributs
    private boolean accesVaccin;
    private int nbVaccin;
    private TypePersonne typePersonne;
    private Etat etat;
    private double x;
    private double y;
    protected Case position;
    private Random rdm = new Random();

    // Constructeur
    public Personne(boolean accesVaccin, TypePersonne typePersonne, Etat etat, double x, double y) {
        this.accesVaccin = accesVaccin;
        this.nbVaccin = 0;
        this.typePersonne = typePersonne;
        this.etat = etat;
        this.position = new Case(x, y);
    }

    public void estGuerie() {
        int cyclesRemission = rdm.nextInt(Epidemie.getCycles());
        TypePersonne typePersonneInit = this.typePersonne;
        if (this.etat == Etat.GUERIE && cyclesRemission != 0) {
            this.typePersonne = TypePersonne.RESISTANTE;
            cyclesRemission -= 1;
        } else {
            this.typePersonne = typePersonneInit;
        }
    }

    public Case getPosition() {
        return position;
    }
}