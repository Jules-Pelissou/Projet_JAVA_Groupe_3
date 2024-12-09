package org.github.pelissou;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(100, 10, 10); // 100 individus dans un espace 10x10
        Maladie maladie = new Maladie(0.3, 2, 0.4, 0.1, 3); // Maladie avec caractéristiques définies
        double dmax = 10.0; // Distance maximale de transmission

        Simulation simulation = new Simulation(population, maladie, dmax);
        population.contaminerAleatoirement(0.2); // 20% de la population initialement infectée
        simulation.lancerSimulation(10); // 10 cycles de simulation
    }
}


