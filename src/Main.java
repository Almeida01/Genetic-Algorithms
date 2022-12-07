import main.models.Chromosome;
import main.models.Population;

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
//        System.out.println(initPop);

        LinkedList<Chromosome> generation = population.oneGenerationOneMax(generator, s, pm, pc);
        System.out.print("0: ");
        print(initPop);
        System.out.print("1: ");
        print(generation);

        for (int i = 0; i < rep - 1; i++) {
            population = new Population();
            for (Chromosome chromosome : generation) {
                population.addChromosome(chromosome);
            }

            generation = population.oneGenerationOneMax(generator, s, pm, pc);
            System.out.print((i+2) + ": ");
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
