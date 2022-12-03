package main.models;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChromosomeTest {

    @Test
    void getGene() {
        Random generator = new Random(0);
        Chromosome x = new Chromosome(3, generator);
        assertEquals(x.getGene(), "101");
        Chromosome y = new Chromosome(3, generator);
        assertEquals(y.getGene(), "110");
        Chromosome z = new Chromosome(3, generator);
        assertEquals(z.getGene(), "011");
        Chromosome w = new Chromosome(3, generator);
        assertEquals(w.getGene(), "100");
    }

    @Test
    void getFitness() {
    }

    @Test
    void onePointCrossover() {
        Random generator = new Random(0);

        Chromosome a = new Chromosome("11111111");
        Chromosome b = new Chromosome("00000000");

        Chromosome[] children = {
                new Chromosome("11111000"),
                new Chromosome("00000111")
        };

        assertEquals(Arrays.toString(children), Arrays.toString(a.onePointCrossover(generator, b)));
    }

    @Test
    void uniformCrossover() {
        Random generator = new Random(0);

        Chromosome a = new Chromosome("11111111");
        Chromosome b = new Chromosome("00000000");

        Chromosome[] children = {
                new Chromosome("10111001"),
                new Chromosome("01000110")
        };

        assertEquals(Arrays.toString(children), Arrays.toString(a.uniformCrossover(generator, b)));
    }

    @Test
    void bitFlipMutation() {
        Random generator = new Random(0);

        double prob = 0.25;
        Chromosome a = new Chromosome("11111111");

        Chromosome child = new Chromosome("10111111");

        assertEquals(child.getGene(), a.bitFlipMutation(generator, prob).getGene());
    }

    /*@Test
    void random() {
        Random generator = new Random(0);
        int N = 4;
        Chromosome a = new Chromosome();

        Chromosome[] expected = {
                new Chromosome("2"),
                new Chromosome("1"),
                new Chromosome("3"),
                new Chromosome("0")
        };

        assertTrue(compare(expected, a.randomPermutation(generator, N)));
    }*/

    private boolean compare(Chromosome[] a, Chromosome[] b) {
        for (int i = 0; i < a.length; i++)
            if (!a[i].getGene().equals(b[i].getGene())) return false;
        return true;
    }
}