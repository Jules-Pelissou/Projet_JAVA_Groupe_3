package org.github.pelissou;

import java.sql.Array;
import java.util.*;

public class Population {

    // Attributs

    private ArrayList<Personne> personnes;
    private int tailleLigne;
    private int tailleColonne;
    private int tauxAccesVaccin;
    private int tauxMalade;
    private HashMap<TypePersonne, Integer> tauxTypePersonne;


    // Constructeur

    public Population(int tailleLigne, int tailleColonne, int nbPersonne, int tauxAccesVaccin, int tauxMalade) {

        // Défini les pourcentages globaux
        int pourcentageType = 100;


        // Création du nombre de personnes pour les pourcentages d'accès au vaccin & de personnes malades
        int nbPersonnesAccesVaccin = (tauxAccesVaccin * nbPersonne) / 100;
        int nbPersonnesMalades = (tauxMalade * nbPersonne) / 100;

        this.personnes = new ArrayList<>();

        // Création d'un tableau avec en valeur tous les états et en valeur un Array de personnes
        // Ce tableau permet d'attribuer les accès au vaccin et les taux de malades
        HashMap<TypePersonne, ArrayList<Personne>> personneParType = new HashMap<>();
        for (TypePersonne typePersonne : TypePersonne.values()) {
            personneParType.put(typePersonne, new ArrayList<Personne>());
        }


        // Créé un tableau avec les taux (%ages) de personnes de chaque type à avoir
        this.tauxTypePersonne = new HashMap<TypePersonne, Integer>();

        Random random = new Random();
        int pourcentageTypeRdm = 0;


        // Pour chaque type de personnes => créé un pourcentageType random de type de personnes
        for (TypePersonne t : TypePersonne.values()){

            // Vérification que t est la dernière valeur des valeurs TypePersonne (On récup les valeurs avec .values() et l'index avec ce qui est entre [])
            // Si c'est le dernier index (la dernière valeur a attribuer alors on donne le reste des %ages
            if (t == TypePersonne.values()[TypePersonne.values().length - 1]){
                pourcentageTypeRdm = pourcentageType;
            }else if(t == TypePersonne.IMMUNISEE) {
                pourcentageTypeRdm = Math.min(5, pourcentageType);
            }else{
                    pourcentageTypeRdm = random.nextInt(pourcentageType);
            }
            tauxTypePersonne.put(t, pourcentageTypeRdm);
            pourcentageType -= pourcentageTypeRdm;
            System.out.println(tauxTypePersonne);
        }


        /*
         *   Pour chaques valeurs dans le dictionnaire TypePersonnes / pourcentageType
         *   => Créer le bon nombre de personnes
         */

        for ( HashMap.Entry<TypePersonne, Integer> entry: tauxTypePersonne.entrySet()){
            // Récupère le nombre de personnes demandées pour chaque type de personnes
            int nbPersSpe = (entry.getValue() * nbPersonne) / 100;
            // Changement des valeurs du taux type de personnes pour les nombres de personnes
            entry.setValue(nbPersSpe);
        }

        // Créé toutes les personnes avec des valeurs par défaut, sauf pour le Type de Personnes
        // Toutes les personnes créées sont mises dans les tableaux du Dictionnaire (personneParType)

        for (TypePersonne typePersonne : TypePersonne.values()) {
            personneParType.put(typePersonne, new ArrayList<Personne>());
        }

        for (HashMap.Entry<TypePersonne, Integer> entry : tauxTypePersonne.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Personne p = new Personne(false, entry.getKey(), Etat.NEUTRE, 0, 0);
                personneParType.get(entry.getKey()).add(p);
            }
        }
        // Ici personneParType contient logiquement toutes les personnes créées triées par type de personnes

        // Ajout de toute la population dans une même liste

        for (HashMap.Entry<TypePersonne, ArrayList<Personne>> entry: personneParType.entrySet()) {
            personnes.addAll(entry.getValue());
        }

        // Ajout des gens malades

        ArrayList<Integer> indicePersonnesMalades = new ArrayList<>();
        for (int i = 0; i < personnes.size(); i++) {
            indicePersonnesMalades.add(i);
        }

        for (int i = 0; i < nbPersonnesMalades; i++) {

            int randomIndex = random.nextInt(indicePersonnesMalades.size());
            int index = indicePersonnesMalades.get(randomIndex);

            if (personnes.get(index).getTypePersonne() == TypePersonne.IMMUNISEE) {
                i--;
            } else {
                personnes.get(index).setEtat(Etat.MALADE);
                indicePersonnesMalades.remove(randomIndex);
            }
        }

        // Ajout des accès au vaccin

        ArrayList<Integer> indicePersonnesAccesVaccin = new ArrayList<>();
        for (int i = 0; i < personnes.size(); i++) {
            indicePersonnesAccesVaccin.add(i);
        }
        for (int i = 0; i < nbPersonnesAccesVaccin; i++) {
            // Retire l'index utilisé de la liste des indexs pour éviter de donner l'accès 2 fois à la même personne
            int index = indicePersonnesAccesVaccin.remove(0);
            personnes.get(index).setAccesVaccin(true);
        }


        this.tailleLigne = tailleLigne;
        this.tailleColonne = tailleColonne;
        
    }
    // Fin du constructeur


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

    //Getters

    public HashMap<Etat, Integer> getEtatPersonnes() {
        HashMap<Etat, Integer> comptePersonnes = new HashMap<Etat, Integer>();

        for (Etat etat : Etat.values()) {
            comptePersonnes.put(etat, 0);
        }
        for (Personne personne : personnes) {
            comptePersonnes.put(personne.getEtat(), comptePersonnes.get(personne.getEtat()) + 1);
        }
        return comptePersonnes;
    }

    public String getEtatPersonneLisible(){
        HashMap<Etat, Integer> comptePersonnes = getEtatPersonnes();
        String result = "";
        for (Etat etat : comptePersonnes.keySet()) {
            result += etat.toString() + " " + comptePersonnes.get(etat) + "\n";
        }
        return result;
    }



    // Override méthode toString
    @Override
    public String toString(){
        String result = "";
        for (Personne personne : personnes){
            result+= personne.toString() + "\n";
        }
        return result;
    }

}
