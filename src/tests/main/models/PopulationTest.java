package main.models;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PopulationTest {

    @Test
    void oneMaxFitness() {
        Population population = new Population(1);
        Chromossome x = new Chromossome("11010011");
        assertEquals(5, population.oneMaxFitness(x));
    }

    @Test
    void squareFitness() {
        Population population = new Population(1);
        Chromossome x = new Chromossome("1101");
        assertEquals(169, population.squareFitness(x));

    }

    @Test
    void binaryTournament() {
        Random generator = new Random(0);
        int l = 3;
        Population population = new Population();
        Chromossome a = new Chromossome(l, generator, 8);
        Chromossome b = new Chromossome(l, generator, 7);
        Chromossome c = new Chromossome(l, generator, 23);
        Chromossome d = new Chromossome(l, generator, 5);
        population.addChromossome(a);
        population.addChromossome(b);
        population.addChromossome(c);
        population.addChromossome(d);
        LinkedList<Chromossome> winners = new LinkedList<>();
        winners.add(a);
        winners.add(c);
        winners.add(c);
        winners.add(c);

        assertEquals(winners, population.binaryTournament(generator));
    }
}