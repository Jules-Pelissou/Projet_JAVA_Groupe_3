package org.github.pelissou;

import java.sql.Array;
import java.util.*;

public class Population {

    // Attributs

    private ArrayList<Personne> personnes;
    private int tailleLigne;
    private int tailleColonne;
    private HashMap<TypePersonne, Integer> tauxTypePersonne;


    // Constructeur

    public Population(int tailleLigne, int tailleColonne, int nbPersonne, int tauxAccesVaccin, int tauxMalade) {

        // Défini les pourcentages globaux
        int pourcentageType = 100;
        int pourcentageEtat = 100;
        int pourcentageVaccin = 100;

        // Création du nombre de personnes pour les pourcentages de Malades
        int nbPersonnesMalades = (pourcentageEtat * nbPersonne) / 100;
        int nbPersonnesAccesVaccin = (pourcentageVaccin * nbPersonne) / 100;

        this.personnes = new ArrayList<>();

        // Création d'un tableau avec en valeur tous les états et en valeur un Array de personnes
        // Ce tableau permet d'attribuer les accès au vaccin et les taux de malades
        HashMap<TypePersonne, ArrayList<Personne>> personneParType = new HashMap<>();
        for (TypePersonne typePersonne : TypePersonne.values()) {
            personneParType.put(typePersonne, new ArrayList<Personne>());
        }


        // Créé un tableau avec les taux (%ages) de personnes de chaque type à avoir
        this.tauxTypePersonne = new HashMap<TypePersonne, Integer>();

        // Pour chaque type de personnes => créé un pourcentageType random de type de personnes
        for (TypePersonne t : TypePersonne.values()){
            Random random = new Random();
            int pourcentageTypeRdm = random.nextInt(pourcentageType);
            tauxTypePersonne.put(t, pourcentageTypeRdm);
            pourcentageType -= pourcentageTypeRdm;
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

        // Ajout des accès aux vaccins
        for (HashMap.Entry<TypePersonne, ArrayList<Personne>> entry: personneParType.entrySet()){
            for (int i = 0; i < nbPersonnesAccesVaccin/4; i++) {
                int index = (int)(Math.random() * entry.getValue().size());
                entry.getValue().get(index).setAccesVaccin(true);
            }
        }

        // Ajout des états
        for (HashMap.Entry<TypePersonne, ArrayList<Personne>> entry: personneParType.entrySet()) {
            for (int i = 0; i < nbPersonnesMalades /3; i++) {
                int index = (int)(Math.random() * entry.getValue().size());
                entry.getValue().get(index).setEtat(Etat.MALADE);
            }
        }

        // Ajout de toute la population dans une même liste

        for (HashMap.Entry<TypePersonne, ArrayList<Personne>> entry: personneParType.entrySet()) {
            personnes.addAll(entry.getValue());
        }

        // Gestion des cases occupées
        HashSet<Case> occupiedCases = new HashSet<>();

        for (Personne personne : personnes) {
            occupiedCases.add(personne.getPosition());
        }

        for (Personne personne : personnes){
            Case position = new Case(Math.random()*tailleLigne, Math.random()*tailleColonne);
            while (occupiedCases.contains(position)){
                position = new Case(Math.random()*tailleLigne, Math.random()*tailleColonne);
            }
            personne.setPosition(position);
            personne.setY(position.getColonne());
            personne.setX(position.getLigne());
        }

        this.tailleLigne = tailleLigne;
        this.tailleColonne = tailleColonne;
        
    }
    // Fin du constructeur


    public void contaminerAleatoirement(double pourcentage) {
        int nombreInfectes = (int) (personnes.size() * pourcentage);
        for (int i = 0; i < nombreInfectes; i++) {
            personnes.get(i).setEtat(Etat.MALADE);
        }
    }

    public void propagerMaladie(Maladie maladie, double dmax) {
        Random random = new Random();
        for (Personne infecte : personnes) {
            if (infecte.getEtat() == Etat.MALADE) {
                for (Personne autre : personnes) {
                    if (autre.getEtat() == Etat.NEUTRE) {
                        double probabilite = infecte.probabiliteContagion(autre, dmax);
                        if (random.nextDouble() < probabilite) {
                            autre.setEtat(Etat.MALADE);
                        }
                    }
                }
            }
        }
    }

    public void campagneVaccination(int doses) {
        for (Personne personne : personnes) {
            if (personne.hasAccesVaccin() && !personne.isImmunise()) {
                personne.vacciner(doses);
            }
        }
    }

    public void appliquerComportements(double pourcentageMasques, double pourcentageDistanciation) {
        Random random = new Random();
        for (Personne personne : personnes) {
            double chance = random.nextDouble();
            if (chance < pourcentageMasques) {
                personne.setComportement(Comportement.PORT_DE_MASQUE);
            } else if (chance < pourcentageMasques + pourcentageDistanciation) {
                personne.setComportement(Comportement.DISTANCIATION_SOCIALE);
            }
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

    public ArrayList<Personne> getPersonnes(){
        return personnes;
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
