package org.github.pelissou;

public class Simulation {
    private Population population;
    private Maladie maladie;
    private int cycleActuel;
    private double dmax;

    public Simulation(Population population, Maladie maladie, double dmax) {
        this.population = population;
        this.maladie = maladie;
        this.cycleActuel = 0;
        this.dmax = dmax;
    }

    public void lancerSimulation(int nbCycles, boolean introductionVariant, int cycleIntroductionVariant, String nomVariant) {
        for (int i = 0; i < nbCycles; i++) {
            cycleActuel++;
            System.out.println("Cycle " + cycleActuel);

            // Introduction d'un variant si activée et au bon cycle
            if (introductionVariant && cycleActuel == cycleIntroductionVariant) {
                maladie.activerVariant(nomVariant);
                System.out.println("Variant activé : " + nomVariant);
            }

            // Propagation de la maladie
            population.propagerMaladie(maladie, dmax);

            // Mise à jour de l'état des individus
            for (Individu individu : population.getIndividus()) {
                individu.mettreAJourEtat(maladie);
            }

            // Afficher les statistiques
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



