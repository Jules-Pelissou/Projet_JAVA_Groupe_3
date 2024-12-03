package org.github.pelissou;

public class Simulation {
    private Population population;
    private Maladie maladie;
    private int cycleActuel;

    public Simulation(Population population, Maladie maladie) {
        this.population = population;
        this.maladie = maladie;
        this.cycleActuel = 0;
    }

    public void lancerSimulation(int nbCycles) {
        for (int i = 0; i < nbCycles; i++) {
            cycleActuel++;
            System.out.println("Cycle " + cycleActuel);
            for (Individu individu : population.getIndividus()) {
                individu.mettreAJourEtat(maladie);
            }
            afficherStatistiques();
        }
    }

    private void afficherStatistiques() {
        long sains = population.getIndividus().stream().filter(i -> i.getEtat() == Etat.SAIN).count();
        long infectes = population.getIndividus().stream().filter(i -> i.getEtat() == Etat.INFECTE).count();
        long gueris = population.getIndividus().stream().filter(i -> i.getEtat() == Etat.GUERI).count();
        long decedes = population.getIndividus().stream().filter(i -> i.getEtat() == Etat.DECEDE).count();

        System.out.println("Sains : " + sains + ", Infectés : " + infectes + ", Guéris : " + gueris + ", Décédés : " + decedes);
    }
}

