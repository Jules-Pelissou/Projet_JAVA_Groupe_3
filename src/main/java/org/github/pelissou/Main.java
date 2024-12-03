package org.github.pelissou;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(100, 10, 10);
        Maladie maladie = new Maladie(0.3, 2, 0.4, 0.1, 3);
        Simulation simulation = new Simulation(population, maladie);

        population.contaminerAleatoirement(0.2);
        simulation.lancerSimulation(10);
    }
}

