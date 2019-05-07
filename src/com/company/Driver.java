package com.company;

import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {
        int generationNumber = 0;
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Population population = new Population(GeneticAlgorithm.POPULATION_SIZE).initializePopulation();
        System.out.println("---------------------------------------------");
        System.out.println(
                "Generation # 0 "
                        .concat(" | Fittest chromosome fitness: ")
                        .concat(Integer.toString(population.getChromosomes()[0].getFitness()))
        );
        printPopulation(population, "Target Chromosome: ".concat(Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME)));
        while (population.getChromosomes()[0].getFitness() < GeneticAlgorithm.TARGET_CHROMOSOME.length) {
            generationNumber++;
            System.out.println("---------------------------------------------");
            population = geneticAlgorithm.evolve(population);
            population.sortChromosomesByFitness();
            System.out.println(
                    "Generation # "
                            .concat(Integer.toString(generationNumber))
                            .concat(" | Fittest chromosome fitness: ")
                            .concat(Integer.toString(population.getChromosomes()[0].getFitness()))
            );
            printPopulation(population, "Target Chromosome: ".concat(Arrays.toString(GeneticAlgorithm.TARGET_CHROMOSOME)));
        }
    }

    private static void printPopulation(Population population, String heading) {
        System.out.println(heading);
        System.out.println("---------------------------------------------");
        for (int index = 0; index < population.getChromosomes().length; index++)
            System.out.println(
                    "Chromosome # "
                            .concat(Integer.toString(index))
                            .concat(" : ")
                            .concat(Arrays.toString(population.getChromosomes()[index].getGenes()))
                            .concat(" | Fitness: ")
                            .concat(Integer.toString(population.getChromosomes()[index].getFitness()))
            );
    }
}