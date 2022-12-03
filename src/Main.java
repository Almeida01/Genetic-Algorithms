import main.models.Chromosome;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);
        Chromosome a = new Chromosome(sc.next());

        Chromosome[] children = a.onePointCrossover(generator, new Chromosome(sc.next()));

        Arrays.stream(children).toList().forEach(System.out::println);
    }


}
