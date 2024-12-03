package org.github.pelissou;

import java.util.Random;

public class Individu {
    private int id;
    private double x, y;
    private Etat etat;
    private int cyclesResistance;
    private Comportement comportement;

    public Individu(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.etat = Etat.SAIN; // Par défaut, l'individu est sain
        this.cyclesResistance = 0;
        this.comportement = Comportement.NONE; // Pas de comportement spécifique
    }

    public double calculDistance(Individu autre) {
        return Math.sqrt(Math.pow(this.x - autre.x, 2) + Math.pow(this.y - autre.y, 2));
    }

    public void mettreAJourEtat(Maladie maladie) {
        Random random = new Random();
        switch (etat) {
            case INFECTE:
                // Vérifie la guérison ou le décès
                if (random.nextDouble() < maladie.getProbaGuerison()) {
                    etat = Etat.GUERI;
                    cyclesResistance = maladie.getDureeResistance();
                } else if (random.nextDouble() < maladie.getProbaDeces()) {
                    etat = Etat.DECEDE;
                }
                break;

            case GUERI:
                // Réduit les cycles de résistance
                if (cyclesResistance > 0) {
                    cyclesResistance--;
                } else {
                    etat = Etat.SAIN;
                }
                break;

            default:
                break;
        }
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat nouvelEtat) {
        this.etat = nouvelEtat;
    }

    public Comportement getComportement() {
        return comportement;
    }

    public void setComportement(Comportement comportement) {
        this.comportement = comportement;
    }
}

