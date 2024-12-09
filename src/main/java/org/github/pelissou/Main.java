package org.github.pelissou;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(100, 10, 10); // 100 individus
        Maladie maladie = new Maladie(0.3, 2, 0.4, 0.1, 3); // Maladie avec caractéristiques définies
        double dmax = 10.0; // Distance maximale de transmission

        Simulation simulation = new Simulation(population, maladie, dmax);

        // Simulation avec une campagne de vaccination au 5ème cycle, en une dose
        simulation.lancerSimulation(10, true, 5, 1);
    }
}



