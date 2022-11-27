package src;

import src.models.Population;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int l = scanner.nextInt();
        Population population = new Population(n, l, generator);
        System.out.println(population);
    }
}
