package org.github.pelissou;

import java.util.ArrayList;
import java.util.List;

public class Population {
    private List<Individu> individus;

    public Population(int taille, double largeurEspace, double hauteurEspace) {
        individus = new ArrayList<>();
        for (int i = 0; i < taille; i++) {
            double x = Math.random() * largeurEspace;
            double y = Math.random() * hauteurEspace;
            individus.add(new Individu(i, x, y));
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
}

