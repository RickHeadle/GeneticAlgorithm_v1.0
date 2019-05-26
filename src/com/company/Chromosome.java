package com.company;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;

public class Chromosome {

    private int[] genes;
    private int fitness = 0;
    private boolean isFitnessChanged = true;

    Chromosome initializeChromosome() {
        for (int index = 0; index < genes.length; index++) {
            if (Math.random() >= 0.5)
                genes[index] = 1;
            else genes[index] = 0;
        }
        return this;
    }

    @Contract(pure = true)
    private int recalculateFitness() {
        int chromosomeFitness = 0;
        for (int index = 0; index < genes.length; index++) {
            if (genes[index] == GeneticAlgorithm.TARGET_CHROMOSOME[index])
                chromosomeFitness++;
        }
        return chromosomeFitness;
    }

    public String toString() {
        return Arrays.toString(this.genes);
    }

    @Contract(pure = true)
    Chromosome(int length) {
        this.genes = new int[length];
    }

    int[] getGenes() {
        isFitnessChanged = true;
        return genes;
    }

    int getFitness() {
        if (isFitnessChanged) {
            fitness = recalculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }
}
