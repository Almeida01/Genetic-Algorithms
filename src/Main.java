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
        double pm = Double.parseDouble(sc.next());
        double pc = Double.parseDouble(sc.next());
        int rep = sc.nextInt();

        Population population = new Population(n, l, generator);
        LinkedList<Chromosome> initPop = population.getPopulation();
        for (Chromosome chromosome : initPop) {
            OneMax oneMax = new OneMax(chromosome);
            chromosome.setFitness(oneMax);
        }
//        System.out.println(initPop);
        print(initPop);

        LinkedList<Chromosome> generation = population.oneGerationOneMax(generator, s, pc, pm);
        print(generation);

        for (int i = 0; i < rep - 1; i++) {
            population = new Population();
            for (int j = 0; j < generation.size(); j++) {
                population.addChromossome(generation.get(j));
            }

            generation = population.oneGerationOneMax(generator, s, pc, pm);
            print(generation);
        }
    }

    private static void print(LinkedList<Chromosome> pop) {
        double highestFit = pop.stream().max(Chromosome::compareTo).get().getFitness();
        double smallestFit = pop.stream().min(Chromosome::compareTo).get().getFitness();
        double average = pop.stream().mapToDouble(Chromosome::getFitness).average().getAsDouble();

        System.out.printf("%.02f ", highestFit);
        System.out.printf("%.02f ", average);
        System.out.printf("%.02f%n", smallestFit);
    }
}
