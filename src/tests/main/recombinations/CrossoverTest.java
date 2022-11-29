package main.recombinations;

import main.models.Chromossome;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CrossoverTest {

    @Test
    void onePointCrossover() {
        Random generator = new Random(0);

        Chromossome a = new Chromossome("11111111");
        Chromossome b = new Chromossome("00000000");

        Chromossome[] children = {
                new Chromossome("11111000"),
                new Chromossome("00000111")
        };

        assertEquals(Arrays.toString(children), Arrays.toString(Crossover.onePoint(generator, a, b)));
    }

    @Test
    void uniformCrossover() {
        Random generator = new Random(0);

        Chromossome a = new Chromossome("11111111");
        Chromossome b = new Chromossome("00000000");

        Chromossome[] children = {
                new Chromossome("10111001"),
                new Chromossome("01000110")
        };

        assertEquals(Arrays.toString(children), Arrays.toString(Crossover.uniform(generator, a, b)));
    }
}