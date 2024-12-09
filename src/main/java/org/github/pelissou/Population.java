package org.github.pelissou;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;

public class Population {
    private List<Individu> individus;

    public Population(int taille, double largeurEspace, double hauteurEspace) {
        individus = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            double x = Math.random() * largeurEspace;
            double y = Math.random() * hauteurEspace;
            boolean accesVaccination = Math.random() < 0.7; // 70% des individus ont accès à la vaccination
            individus.add(new Individu(i, x, y, accesVaccination));
        }
    }

    public List<Individu> getIndividus() {
        return individus;
    }

    public void contaminerAleatoirement(double pourcentage) {
        int nombreInfectes = (int) (individus.size() * pourcentage);
        for (int i = 0; i < nombreInfectes; i++) {
            individus.get(i).setEtat(Etat.INFECTE);
        }
    }

    public void propagerMaladie(Maladie maladie, double dmax) {
        Random random = new Random();
        for (Individu infecte : individus) {
            if (infecte.getEtat() == Etat.INFECTE) {
                for (Individu autre : individus) {
                    if (autre.getEtat() == Etat.SAIN) {
                        double probabilite = infecte.probabiliteContagion(autre, maladie.getTauxTransmission(), dmax);
                        if (random.nextDouble() < probabilite) {
                            autre.setEtat(Etat.INFECTE);
                        }
                    }
                }
            }
        }
    }

    public void campagneVaccination(int doses) {
        for (Individu individu : individus) {
            if (individu.hasAccesVaccination() && !individu.isImmunise()) {
                individu.vacciner(doses);
            }
        }
    }
}
