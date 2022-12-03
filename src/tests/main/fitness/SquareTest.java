package main.fitness;

import main.models.Chromosome;
import main.models.Population;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {
    @Test
    void getFitness() {
        Population population = new Population(1);
        Chromosome x = new Chromosome("1101");
        Square square = new Square(x);
        assertEquals(169, square.getFitness());
    }
}