package org.github.pelissou;

public class Main {
    public static void main(String[] args) {
        Population population = new Population(10, 10, 100, 25, 50); // 100 individus dans un espace 10x10
        System.out.println(population.getEtatPersonnes());
    }
}