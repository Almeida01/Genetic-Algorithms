package main.models;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ChromossomeTest {

    @Test
    void getGene() {
        Random generator = new Random(0);
        Chromossome x = new Chromossome(3, generator);
        assertEquals(x.getGene(), "101");
        Chromossome y = new Chromossome(3, generator);
        assertEquals(y.getGene(), "110");
        Chromossome z = new Chromossome(3, generator);
        assertEquals(z.getGene(), "011");
        Chromossome w = new Chromossome(3, generator);
        assertEquals(w.getGene(), "100");

    }

    @Test
    void getFitness() {
    }
}