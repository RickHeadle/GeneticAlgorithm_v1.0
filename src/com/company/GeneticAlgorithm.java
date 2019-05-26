package com.company;

import org.jetbrains.annotations.NotNull;

class GeneticAlgorithm {
    static final int[] TARGET_CHROMOSOME = {1, 1, 0, 1, 0, 0, 1, 1, 1, 0};
    static final int POPULATION_SIZE = 8;
    private static final int NUMBER_OF_ELITE_CHROMOSOMES = 1;
    private static final int TOURNAMENT_SELECTION_SIZE = 4;
    private static final double MUTATION_RATE = 0.25;

    private Population crossoverPopulation(@NotNull Population population) {
        Population crossoverPopulation = new Population(population.getChromosomes().length);
        for (int index = 0; index < NUMBER_OF_ELITE_CHROMOSOMES; index++)
            crossoverPopulation.getChromosomes()[index] = population.getChromosomes()[index];
        for (int index = NUMBER_OF_ELITE_CHROMOSOMES; index < population.getChromosomes().length; index++) {
            Chromosome chromosome1 = selectTournamentPopulation(population).getChromosomes()[0];
            Chromosome chromosome2 = selectTournamentPopulation(population).getChromosomes()[0];
            crossoverPopulation.getChromosomes()[index] = crossoverChromosome(chromosome1, chromosome2);
        }
        return crossoverPopulation;
    }

    private Population mutatePopulation(@NotNull Population population) {
        Population mutatePopulation = new Population(population.getChromosomes().length);
        for (int index = 0; index < NUMBER_OF_ELITE_CHROMOSOMES; index++)
            mutatePopulation.getChromosomes()[index] = population.getChromosomes()[index];
        for (int index = NUMBER_OF_ELITE_CHROMOSOMES; index < population.getChromosomes().length; index++)
            mutatePopulation.getChromosomes()[index] = mutateChromosome(population.getChromosomes()[index]);
        return mutatePopulation;
    }

    private Chromosome crossoverChromosome(@NotNull Chromosome chromosome1, Chromosome chromosome2) {
        Chromosome crossoverChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int index = 0; index < chromosome1.getGenes().length; index++)
            if (Math.random() < 0.5)
                crossoverChromosome.getGenes()[index] = chromosome1.getGenes()[index];
            else crossoverChromosome.getGenes()[index] = chromosome2.getGenes()[index];
        return crossoverChromosome;
    }

    private Chromosome mutateChromosome(@NotNull Chromosome chromosome) {
        Chromosome mutateChromosome = new Chromosome(TARGET_CHROMOSOME.length);
        for (int index = 0; index < chromosome.getGenes().length; index++)
            if (Math.random() < MUTATION_RATE)
                if (Math.random() < 0.5)
                    mutateChromosome.getGenes()[index] = 1;
                else mutateChromosome.getGenes()[index] = 0;
        return mutateChromosome;
    }

    private Population selectTournamentPopulation(Population population) {
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE);
        for (int index = 0; index < TOURNAMENT_SELECTION_SIZE; index++)
            tournamentPopulation.getChromosomes()[index] =
                    population.getChromosomes()[(int) (Math.random() * population.getChromosomes().length)];
        tournamentPopulation.sortChromosomesByFitness();
        return tournamentPopulation;
    }

    Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }
}
