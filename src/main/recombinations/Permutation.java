package main.recombinations;

import main.models.Chromossome;

import java.util.Random;

import static main.models.Population.mapToPopSize;

public class Permutation {
    /**
     *
     * @param generator Random object.
     * @param N Range of permutation. From 0 to N-1.
     * @return Chromossome array containing permutations.
     */
    public static Chromossome[] random(Random generator, int N) {
        int[] v = new int[N];

        for (int i = 0; i < N; i++)
            v[i] = i;

        for (int i = 0; i < N - 1; i++) {
            double u = generator.nextDouble();
            int r = mapToPopSize(i, N - 1, u);
            int temp = v[r];
            v[r] = v[i];
            v[i] = temp;
        }

        Chromossome[] permutations = new Chromossome[N];
        for (int i : v)
            permutations[i] = new Chromossome(String.valueOf(v[i]));
        return permutations;
    }
}
