import main.fitness.OneMax;
import main.models.Chromosome;
import main.models.Population;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int s = sc.nextInt();
        double pc = sc.nextDouble();
        double pm = sc.nextDouble();

        Population population = new Population(n, l, generator);
        population.getPopulation().forEach(c -> c.setFitness(new OneMax(c)));

        LinkedList<Chromosome> generation = population.oneGerationOneMax(generator, s, pc, pm);

    }


}
