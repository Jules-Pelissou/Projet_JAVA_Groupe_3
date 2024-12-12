package org.github.pelissou;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(10, 10, 100, 50, 50); // 100 individus dans un espace 10x10

        System.out.println(population.getEtatPersonneLisible());
        /*
        Maladie maladie = new Maladie(0.3, 2, 0.4, 0.1, 3); // Maladie avec caractéristiques de base
        double dmax = 10.0; // Distance maximale de transmission

        // Ajout de variants
        Variant variantAlpha = new Variant("Alpha", 0.4, 2, 0.3, 0.15);
        Variant variantDelta = new Variant("Delta", 0.5, 3, 0.25, 0.2);
        maladie.ajouterVariant(variantAlpha);
        maladie.ajouterVariant(variantDelta);

        Simulation simulation = new Simulation(population, maladie, dmax);
        population.contaminerAleatoirement(0.2); // 20% de la population initialement infectée

        // Simulation avec une campagne de vaccination au 5ème cycle, en une dose
        simulation.lancerSimulation(10, true, 6, 1, true, 3, "Delta", 0.5, 0.3);
        */
    }
}