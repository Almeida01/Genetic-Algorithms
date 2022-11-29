package main.selections;

import main.models.Chromossome;
import main.models.Population;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SelectionTest {
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

        assertEquals(winners, Selection.binaryTournament(generator, population));
    }

    @Test
    void roulette() {
        Random generator = new Random(0);

        Chromossome a = new Chromossome("110", 1.0);
        Chromossome b = new Chromossome("001", 3.0);
        Chromossome c = new Chromossome("101", 4.0);
        Chromossome d = new Chromossome("111", 2.0);

        Population population = new Population();
        population.addChromossome(a);
        population.addChromossome(b);
        population.addChromossome(c);
        population.addChromossome(d);

        LinkedList<Chromossome> winners = new LinkedList<>();
        winners.add(b);
        winners.add(b);
        winners.add(c);
        winners.add(d);

        assertEquals(winners, Selection.roulette(generator, population));
    }
}