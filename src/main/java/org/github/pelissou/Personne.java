package org.github.pelissou;

public class Personne {

    // Attributs
    private boolean accesVaccin;
    private int nbVaccin;
    private TypePersonne typePersonne;
    private Etat etat;
    private double x;
    private double y;

    // Constructeur
    public Personne(boolean accesVaccin, TypePersonne typePersonne,Etat etat, double x, double y){
        this.accesVaccin = accesVaccin;
        this.nbVaccin = 0;
        this.typePersonne = typePersonne;
        this.etat = etat;
        this.x = x;
        this.y = y;
    }
}
