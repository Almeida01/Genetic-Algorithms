package main.fitness;

import main.IProblem;
import main.models.Chromosome;

public class OneMax implements IProblem {
    private final Chromosome chromosome;
    public OneMax(Chromosome chromosome) {
        this.chromosome = chromosome;
    }

    private int oneMaxFitness() {
        assert (chromosome.getLength() <= 1000);

        String gene = chromosome.getGene();
        int max = 0;
        for (int i = 0; i < chromosome.getLength(); i++) {
            if (gene.charAt(i) == '1') max++;
        }
        return max;
    }

    @Override
    public double getFitness() {
        return oneMaxFitness();
    }
}
