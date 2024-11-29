package org.github.pelissou;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Population {

    // Attributs

    private ArrayList<Personne> personnes;
    private int tailleLigne;
    private int tailleColonne;
    private int tauxAccesVaccin;
    private Map<TypePersonne, Integer> tauxTypePersonne;


    // Constructeur

    public Population(int tailleLigne, int tailleColonne, int nbPersonne, int tauxAccesVaccin, int tauxSensible, int taux) {

        this.personnes = new ArrayList<>();

        // Créé un tableau avec les taux (%ages) de personnes de chaque type à avoir
        this.tauxAccesVaccin = new Map<TypePersonne, Interger>();

        this.tailleLigne = tailleLigne;
        this.tailleColonne = tailleColonne;

        foreach (TypePersonne t : TypePersonne.values()){
            int random = Random();
            int pourcentage = random.nextInt(100);
            
        }

        this.tauxAcces

        for (int i = 0; i < nbPersonne; i++) {
            Personnne personne = new Personne(){}
        }
    }

    public HashSet<Case> getOccupation() {
        HashSet<Case> occupiedCases = new HashSet<>();
        for (Personne personne : personnes) {
            occupiedCases.add(personne.getPosition());
        }
        return occupiedCases;
    }
    public double calculDistance(Case c1, Case c2) {
        return Math.sqrt(Math.pow(c2.getLigne() - c1.getLigne(), 2) + Math.pow(c2.getColonne() - c1.getColonne(), 2));
    }

}
