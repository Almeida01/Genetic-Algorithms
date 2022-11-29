package main.recombinations;

import main.models.Chromossome;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MutationTest {

    @Test
    void bitFlip() {
        Random generator = new Random(0);

        double prob = 0.25;
        Chromossome a = new Chromossome("11111111");

        Chromossome child = new Chromossome("10111111");

        assertEquals(child.getGene(), Mutation.bitFlip(generator, a, prob).getGene());
    }
}