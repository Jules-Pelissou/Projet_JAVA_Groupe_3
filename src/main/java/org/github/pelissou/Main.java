package org.github.pelissou;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(100, 10, 10); // 100 individus
        Maladie maladie = new Maladie(0.3, 2, 0.4, 0.1, 3); // Maladie avec caractéristiques de base
        double dmax = 10.0; // Distance maximale de transmission

        // Ajout de variants
        Variant variantAlpha = new Variant("Alpha", 0.4, 2, 0.3, 0.15);
        Variant variantDelta = new Variant("Delta", 0.5, 3, 0.25, 0.2);
        maladie.ajouterVariant(variantAlpha);
        maladie.ajouterVariant(variantDelta);

        Simulation simulation = new Simulation(population, maladie, dmax);

        // Simulation avec introduction du variant "Delta" au 5e cycle
        simulation.lancerSimulation(10, true, 5, "Delta");
    }
}




