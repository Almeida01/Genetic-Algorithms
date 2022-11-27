import main.models.Chromossome;
import main.models.Population;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner scanner = new Scanner(System.in);
        String gene = scanner.next();
        Population population = new Population();
        System.out.println(population.squareFitness(new Chromossome(gene)));
    }


}
