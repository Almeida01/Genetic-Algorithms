import main.models.Chromossome;
import main.models.Population;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);
        Population population = new Population();

        Chromossome[] children = population.onePointCrossover(generator,
                new Chromossome(sc.next()),
                new Chromossome(sc.next())
        );

        Arrays.stream(children).toList().forEach(System.out::println);


    }


}
