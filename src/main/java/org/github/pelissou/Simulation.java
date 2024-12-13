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

    public void lancerSimulation(int nbCycles, boolean campagneVaccination, int cycleVaccination, int doses, boolean introductionVariant, int cycleIntroductionVariant, String nomVariant, double pourcentageMasques, double pourcentageDistanciation) {
        // Appliquer les comportements sociaux avant le début de la simulation
        population.appliquerComportements(pourcentageMasques, pourcentageDistanciation);

        for (int i = 0; i < nbCycles; i++) {
            System.out.println(population.getTypePersonneLisible());
            cycleActuel++;
            System.out.println("Cycle " + cycleActuel);

            // Campagne de vaccination si activée et au bon cycle
            if (campagneVaccination && cycleActuel == cycleVaccination) {
                population.campagneVaccination(doses);
                System.out.println("Campagne de vaccination appliquée (doses : " + doses + ").");
            }

            // Introduction d'un variant si activée et au bon cycle
            if (introductionVariant && cycleActuel == cycleIntroductionVariant) {
                maladie.activerVariant(nomVariant);
                System.out.println("Variant activé : " + nomVariant);
            }

            // Propagation de la maladie
            population.propagerMaladie(maladie, dmax);

            // Mise à jour de l'état des individus
            for (Personne personne : population.getPersonnes()) {
                personne.mettreAJourEtat(maladie);
            }

            // Afficher les statistiques
            afficherStatistiques();
        }
        System.out.println(population.getTypePersonneLisible());
        System.out.println(population.getEtatPersonneLisible());
    }

    private void afficherStatistiques() {
        long sains = population.getPersonnes().stream().filter(i -> i.getEtat() == Etat.NEUTRE).count();
        long infectes = population.getPersonnes().stream().filter(i -> i.getEtat() == Etat.MALADE).count();
        long gueris = population.getPersonnes().stream().filter(i -> i.getEtat() == Etat.GUERIE).count();
        long decedes = population.getPersonnes().stream().filter(i -> i.getEtat() == Etat.MORTE).count();
        long immunises = population.getPersonnes().stream().filter(i -> i.getTypePersonne() == TypePersonne.IMMUNISEE).count();

        System.out.println("Sains : " + sains + ", Infectés : " + infectes + ", Guéris : " + gueris + ", Décédés : " + decedes + ", Immunisés : " + immunises + "/ " + population.getNbPop());
    }
}
