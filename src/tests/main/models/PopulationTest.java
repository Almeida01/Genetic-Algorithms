package main.models;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PopulationTest {
    @Test
    void binaryTournament() {
        Random generator = new Random(0);
        int l = 3;
        Population population = new Population();
        Chromosome a = new Chromosome(l, generator, 8);
        Chromosome b = new Chromosome(l, generator, 7);
        Chromosome c = new Chromosome(l, generator, 23);
        Chromosome d = new Chromosome(l, generator, 5);
        population.addChromosome(a);
        population.addChromosome(b);
        population.addChromosome(c);
        population.addChromosome(d);
        LinkedList<Chromosome> winners = new LinkedList<>();
        winners.add(a);
        winners.add(c);
        winners.add(c);
        winners.add(c);

        assertEquals(winners, population.binaryTournament(generator));
    }

    @Test
    void roulette() {
        Random generator = new Random(0);

        Chromosome a = new Chromosome("110", 1.0);
        Chromosome b = new Chromosome("001", 3.0);
        Chromosome c = new Chromosome("101", 4.0);
        Chromosome d = new Chromosome("111", 2.0);

        Population population = new Population();
        population.addChromosome(a);
        population.addChromosome(b);
        population.addChromosome(c);
        population.addChromosome(d);

        LinkedList<Chromosome> winners = new LinkedList<>();
        winners.add(b);
        winners.add(b);
        winners.add(c);
        winners.add(d);

        assertEquals(winners, population.roulette(generator));
    }

    @Test
    void selectionWithoutReplacement() {
        Random generator = new Random(0);
        Population pop = new Population();
        Chromosome a = new Chromosome("110", 8.0);
        Chromosome b = new Chromosome("001", 7.5);
        Chromosome c = new Chromosome("101", 23.0);
        Chromosome d = new Chromosome("111", 5.0);

        pop.addChromosome(a);
        pop.addChromosome(b);
        pop.addChromosome(c);
        pop.addChromosome(d);

        LinkedList<Chromosome> result = new LinkedList<>();
        result.add(c);
        result.add(a);
        result.add(c);
        result.add(b);

        assertEquals(result, pop.selectionWithoutReplacement(generator, 2));

    }

    @Test
    void testOneGenerationOneMax() {
        Random generator = new Random(0);
        Population population = new Population(4, 5, generator);

        LinkedList<Chromosome> expected = new LinkedList<>();
        expected.add(new Chromosome("10101", 3));
        expected.add(new Chromosome("01111", 4));
        expected.add(new Chromosome("10111", 4));
        expected.add(new Chromosome("11101", 4));

        assertTrue(compare(expected, population.oneGenerationOneMax(generator, 2, 0.1, 0.8)));
    }

    private boolean compare(LinkedList<Chromosome> a, LinkedList<Chromosome> b) {
        for (int i = 0; i < a.size(); i++)
            if (!a.get(i).getGene().equals(b.get(i).getGene())) return false;
        return true;
    }
}