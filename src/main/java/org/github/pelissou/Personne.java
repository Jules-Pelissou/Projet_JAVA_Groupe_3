package org.github.pelissou;

public class Personne {

    // Attributs

    private boolean accesVaccin;
    private int nbVaccin;
    private TypePersonne typePersonne;
    private Etat etat;
    private Environnement environnement;

    // Constructeur

    public Personne(accesVaccin,typePersonne,etat,environnement) {
        this.accesVaccin = accesVaccin;
        this.nbVaccin = 0;
        this.typePersonne = typePersonne;
        this.etat = etat;
        this.environnement = environnement;
    }

    // Getters

    public boolean getAccesVaccin(){
        return accesVaccin;
    }

    public int getNbVaccin(){
        return nbVaccin;
    }

    public TypePersonne getTypePersonne(){
        return typePersonne;
    }

    public Etat getEtat(){
        return etat;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }
    
}
