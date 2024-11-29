package org.github.pelissou;

import java.sql.Array;
import java.util.ArrayList;
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

    public double calculDistance(Personne p1, Personne p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

}
