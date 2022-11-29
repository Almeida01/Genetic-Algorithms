package main.recombinations;

import main.models.Chromossome;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PermutationTest {

    @Test
    void random() {
        Random generator = new Random(0);
        int N = 4;

        Chromossome[] expected = {
                new Chromossome("2"),
                new Chromossome("1"),
                new Chromossome("3"),
                new Chromossome("0")
        };

        assertTrue(compare(expected, Permutation.random(generator, N)));
    }

    private boolean compare(Chromossome[] a, Chromossome[] b) {
        for (int i = 0; i < a.length; i++)
            if (!a[i].getGene().equals(b[i].getGene())) return false;
        return true;
    }
}