package main.Fitness;

import main.models.Chromossome;

public class Fitness {
    public static int oneMaxFitness(Chromossome chromossome) {
        assert (chromossome.getLength() <= 1000);

        String gene = chromossome.getGene();
        int max = 0;
        for (int i = 0; i < chromossome.getLength(); i++) {
            if (gene.charAt(i) == '1') max++;
        }
        return max;
    }

    public static int squareFitness(Chromossome chromossome) {
        assert (chromossome.getLength() <= 10);

        int value = Integer.parseInt(chromossome.getGene(), 2);
        return value * value;
    }
}
