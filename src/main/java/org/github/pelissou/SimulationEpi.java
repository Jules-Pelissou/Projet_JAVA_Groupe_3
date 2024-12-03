package org.github.pelissou;

import java.util.List;
import java.util.Map;

public class SimulationEpi {
    private Population populationEtude;
    private List<Maladie> listeMaladies;
    private Map<Maladie, List<Variant>>listeVariantsPourMaladie;
    private Cycle cycleActuel;
    private int nbCycles;

    public SimulationEpi(Population populationEtude, List<Maladie> listeMaladies, Map<Maladie, List<Variant>> listeVariantsPourMaladie, int nbCycles) {
        this.populationEtude = populationEtude;
        this.listeMaladies = listeMaladies;
        this.listeVariantsPourMaladie = listeVariantsPourMaladie;
        this.nbCycles = nbCycles;
    }
}
