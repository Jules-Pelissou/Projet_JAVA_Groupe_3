package org.github.pelissou;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Random;

public class Population {

    // Attributs

    private ArrayList<Personne> personnes;
    private int tailleLigne;
    private int tailleColonne;
    private int tauxAccesVaccin;
    private HashMap<TypePersonne, Integer> tauxTypePersonne;


    // Constructeur

    public Population(int tailleLigne, int tailleColonne, int nbPersonne) {

        this.personnes = new ArrayList<>();

        // Créé un tableau avec les taux (%ages) de personnes de chaque type à avoir
        this.tauxTypePersonne = new HashMap<TypePersonne, Integer>();

        // Défini le pourcentage global
        int pourcentage = 100;

        // Pour chaque type de personnes => créé un pourcentage random de type de personnes
        for (TypePersonne t : TypePersonne.values()){
            Random random = new Random();
            int pourcentageRdm = random.nextInt(pourcentage);
            tauxTypePersonne.put(t, pourcentageRdm);
            pourcentage -= pourcentageRdm;
        }

        /*
         *   Pour chaques valeurs dans le dictionnaire TypePersonnes / pourcentage
         *   => Créer le bon nombre de personnes
         */

        assert tauxTypePersonne != null;
        for ( HashMap.Entry<TypePersonne, Integer> entry: tauxTypePersonne.entrySet()){
            // Récupère le nombre de personnes demandées pour chaque type de personnes
            int nbPersSpe = (entry.getValue() * nbPersonne) / 100;
            // Changement des valeurs du taux type de personnes pour les nombres de personnes
            entry.setValue(nbPersSpe);
        }

        // Créé toutes les personnes avec des valeurs par défaut, sauf pour le Type de Personnes

        for ( HashMap.Entry<TypePersonne, Integer> entry: tauxTypePersonne.entrySet()){
            Personne p = new Personne(false,entry.getKey(), Etat.NEUTRE, 0,0);
            personnes.add(p);
        }

        // Modifier l'accès au vaccin

        //for (Personne p: personnes){
        //
        //}

        this.tailleLigne = tailleLigne;
        this.tailleColonne = tailleColonne;
        
    }

    public HashSet<Case> getOccupation() {
        HashSet<Case> occupiedCases = new HashSet<>();
        for (Personne personne : personnes) {
            occupiedCases.add(personne.getPosition());
        }
        return occupiedCases;
    }

    public void positionnerPersonne(){
        for (Personne personne : personnes){
            Case position = new Case(Math.random()*tailleLigne, Math.random()*tailleColonne);
            while (getOccupation().contains(position)){
                position = new Case(Math.random()*tailleLigne, Math.random()*tailleColonne);
            }
            personne.setPosition(position);
        }
    }

    public static double calculDistance(Case c1, Case c2) {
        return Math.sqrt(Math.pow(c2.getLigne() - c1.getLigne(), 2) + Math.pow(c2.getColonne() - c1.getColonne(), 2));
    }

}
