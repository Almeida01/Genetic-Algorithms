package main.fitness;

import main.models.Chromosome;
import main.models.Population;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OneMaxTest {

    @Test
    void getFitness() {
        Population population = new Population(1);
        Chromosome x = new Chromosome("11010011");
        OneMax oneMax = new OneMax(x);
        assertEquals(5, oneMax.getFitness());
    }
}