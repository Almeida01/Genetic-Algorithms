package main.models;

import org.junit.jupiter.api.Test;

import javax.management.relation.RelationNotFoundException;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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
        population.addChromossome(a);
        population.addChromossome(b);
        population.addChromossome(c);
        population.addChromossome(d);
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
        population.addChromossome(a);
        population.addChromossome(b);
        population.addChromossome(c);
        population.addChromossome(d);

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

        pop.addChromossome(a);
        pop.addChromossome(b);
        pop.addChromossome(c);
        pop.addChromossome(d);

        LinkedList<Chromosome> result = new LinkedList<>();
        result.add(c);
        result.add(a);
        result.add(c);
        result.add(b);

        assertEquals(result, pop.selectionWithoutReplacement(generator, 2));

    }

    @Test
    void oneGerationOneMax() {

    }
}