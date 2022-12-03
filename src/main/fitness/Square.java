package main.fitness;

import main.IProblem;
import main.models.Chromosome;

public class Square implements IProblem {
    private final Chromosome chromosome;
    public Square(Chromosome chromosome) {
        this.chromosome = chromosome;
    }

    private int squareFitness() {
        assert (chromosome.getLength() <= 10);

        int value = Integer.parseInt(chromosome.getGene(), 2);
        return value * value;
    }

    @Override
    public double getFitness() {
        return squareFitness();
    }
}
