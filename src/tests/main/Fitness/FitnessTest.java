package main.Fitness;

import main.models.Chromossome;
import main.models.Population;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTest {

    @Test
    void oneMaxFitness() {
        Population population = new Population(1);
        Chromossome x = new Chromossome("11010011");
        assertEquals(5, Fitness.oneMaxFitness(x));
    }

    @Test
    void squareFitness() {
        Population population = new Population(1);
        Chromossome x = new Chromossome("1101");
        assertEquals(169, Fitness.squareFitness(x));

    }
}