package com.company;

import java.util.Arrays;

class Population {

    Population(int length) {
        this.chromosomes = new Chromosome[length];
    }

    Chromosome[] getChromosomes() {
        return chromosomes;
    }

    private Chromosome[] chromosomes;

    Population initializePopulation() {
        for (int index = 0; index < chromosomes.length; index++) {
            chromosomes[index] = new Chromosome(GeneticAlgorithm.TARGET_CHROMOSOME.length).initializeChromosome();
        }
        sortChromosomesByFitness();
        return this;
    }

    void sortChromosomesByFitness() {
        Arrays.sort(chromosomes, (chromosome1, chromosome2) -> {
            int flag = 0;
            if (chromosome1.getFitness() > chromosome2.getFitness())
                flag = -1;
            else if (chromosome1.getFitness() < chromosome2.getFitness())
                flag = 1;
            return flag;
        });
    }
}
