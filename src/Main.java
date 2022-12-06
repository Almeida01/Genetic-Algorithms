import main.models.Chromosome;
import main.models.Population;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);
        Population population = new Population();
        int count = 0;
        while(count++ < 4) {
            population.addChromosome(new Chromosome(sc.next(), Double.parseDouble(sc.next())));
        }

        LinkedList<Chromosome> result = population.roulette(generator);
        result.forEach(System.out::println);
    }
}
