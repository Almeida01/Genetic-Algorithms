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
        LinkedList<Chromosome> result = new Population(n, l, generator).getPopulation();
        result.forEach(System.out::println);
    }
}
