package org.github.pelissou;

import java.util.Random;

public class Individu {
    protected int id;
    private double x, y;
    private Etat etat;
    protected int cyclesResistance;
    private Comportement comportement;
    private int dureeResistanceSpecifique;
    private boolean accesVaccination;
    private int dosesReçues;
    private boolean immunise;

    public Individu(int id, double x, double y, boolean accesVaccination) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.etat = Etat.SAIN; // Par défaut, l'individu est sain
        this.cyclesResistance = 0;
        this.dureeResistanceSpecifique = (int) (Math.random() * 5 + 1); // Durée aléatoire entre 1 et 5 cycles
        this.comportement = Comportement.NONE; // Pas de comportement spécifique
        this.accesVaccination = accesVaccination;
        this.dosesReçues = 0;
        this.immunise = false;
    }

    public double calculDistance(Individu autre) {
        double distance = Math.sqrt(Math.pow(this.x - autre.x, 2) + Math.pow(this.y - autre.y, 2));

        // Appliquer l'effet de distanciation sociale
        if (this.comportement == Comportement.DISTANCIATION_SOCIALE || 
            autre.comportement == Comportement.DISTANCIATION_SOCIALE) {
            distance *= 2; // Double la distance observée
        }
        return distance;
    }

    public double probabiliteContagion(Individu autre, double p0, double dmax) {
        double distance = this.calculDistance(autre);
        if (distance > dmax) {
            return 0.0; // Aucune transmission au-delà de la distance maximale
        }

        double facteurMasque = 1.0;
        // Réduit le risque de transmission initial avec les masques
        if (this.comportement == Comportement.PORT_DE_MASQUE || 
            autre.comportement == Comportement.PORT_DE_MASQUE) {
            facteurMasque = 0.5;
        }

        return Math.max(0, p0 * facteurMasque * (1 - distance / dmax));
    }

    public void mettreAJourEtat(Maladie maladie) {
        Random random = new Random();
        switch (etat) {
            case INFECTE:
                // Vérifie la guérison ou le décès
                if (random.nextDouble() < maladie.getProbaGuerison()) {
                    etat = Etat.GUERI;
                    cyclesResistance = dureeResistanceSpecifique;
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

    public void vacciner(int doses) {
        if (!accesVaccination || immunise) return;

        dosesReçues += doses;
        if (dosesReçues >= 2 || doses == 1) { // Immunité active selon le type de vaccination
            immunise = true;
            etat = Etat.GUERI; // Rendu résistant immédiatement
            cyclesResistance = Integer.MAX_VALUE; // Résistance permanente
        }
    }

    public boolean isImmunise() {
        return immunise;
    }

    public boolean hasAccesVaccination() {
        return accesVaccination;
    }

    public int getDosesReçues() {
        return dosesReçues;
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

    public int getDureeResistanceSpecifique() {
        return dureeResistanceSpecifique;
    }
}
