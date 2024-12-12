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

    protected int cyclesResistance;
    private Comportement comportement;
    private int dureeResistanceSpecifique;
    private boolean immunise;

    // Constructeur
    public Personne(boolean accesVaccin, TypePersonne typePersonne, Etat etat, double x, double y) {
                this.x = x;
                this.y = y;
                this.etat = Etat.NEUTRE; // Par défaut, l'individu est sain
                this.typePersonne = typePersonne;
                this.cyclesResistance = 0;
                this.dureeResistanceSpecifique = (int) (Math.random() * 5 + 1); // Durée aléatoire entre 1 et 5 cycles
                this.comportement = Comportement.NONE; // Pas de comportement spécifique
                this.accesVaccin = accesVaccin;
                this.nbVaccin = 0;
                this.immunise = false;
    }
            public double calculDistance(Personne autre) {
                double distance = Math.sqrt(Math.pow(this.x - autre.x, 2) + Math.pow(this.y - autre.y, 2));

                // Appliquer l'effet de distanciation sociale
                if (this.comportement == Comportement.DISTANCIATION_SOCIALE ||
                        autre.comportement == Comportement.DISTANCIATION_SOCIALE) {
                    distance *= 2; // Double la distance observée
                }
                return distance;
            }

            public double probabiliteContagion(Personne autre, double dmax) {
                double p0 = autre.typePersonne.getTauxTransmission();
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

                switch (etat) {
                    case MALADE:
                        // Vérifie la guérison ou le décès
                        if (rdm.nextDouble() < maladie.getProbaGuerison()) {
                            etat = Etat.GUERIE;
                            cyclesResistance = dureeResistanceSpecifique;
                            typePersonne = TypePersonne.RESISTANTE; // Une personne guérie devient résistante
                        } else if (rdm.nextDouble() < maladie.getProbaDeces()) {
                            etat = Etat.MORTE;
                        }
                        break;

                    case GUERIE:
                        // Réduit les cycles de résistance
                        if (cyclesResistance > 0) {
                            cyclesResistance--;
                        } else {
                            etat = Etat.NEUTRE; // Retour à l'état sain après la période de résistance
                            typePersonne = TypePersonne.NEUTRE; // Redevient neutre après la résistance
                        }
                        break;

                    default:
                        break;
                }
            }

            public void vacciner(int doses) {
                if (!accesVaccin || immunise) return; // Pas d'effet si pas d'accès ou déjà immunisé

                nbVaccin += doses;

                if (nbVaccin == 1) {
                    if (typePersonne == TypePersonne.SENSIBLE || typePersonne == TypePersonne.NEUTRE) {
                        typePersonne = TypePersonne.RESISTANTE; // Rend résistants les sensibles et neutres
                    }
                }

                if (nbVaccin >= 2 || doses == 1) { // Immunisation complète pour 2 doses ou vaccination en une dose
                    immunise = true;
                    typePersonne = TypePersonne.RESISTANTE; // Toutes les personnes deviennent résistantes
                    etat = Etat.GUERIE; // Immunisées, elles sont considérées guéries
                }
            }

            public boolean isImmunise() {
                return immunise;
            }

            public boolean hasAccesVaccin() {
                return accesVaccin;
            }

            public int getnbVaccin() {
                return nbVaccin;
            }

            public Etat getEtat() {
                return etat;
            }

            public Comportement getComportement() {
                return comportement;
            }

            public int getDureeResistanceSpecifique() {
                return dureeResistanceSpecifique;
            }

            public TypePersonne getTypePersonne() {
                return typePersonne;
            }

            public Case getPosition(){
                return position;
            }

            public void setPosition(Case position) {
                this.position = position;
            }

            public void setAccesVaccin(boolean accesVaccin) {
                this.accesVaccin = accesVaccin;
            }

            public void setEtat(Etat etat) {
                this.etat = etat;
            }

            public void setX(double x) {
                this.x = x;
            }
            public void setY(double y) {
                this.y = y;
            }

            public void setComportement(Comportement comportement) {
                this.comportement = comportement;
            }

            // Override méthote toString
            @Override
            public String toString() {
                return "Accès au vaccin : " + accesVaccin + ", nbVaccin : " + nbVaccin + ", etat : " + etat + ", TypePersonne : " + typePersonne + ", x : "+ x + ", y : " + y ;
            }
}