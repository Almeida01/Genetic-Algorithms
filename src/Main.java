import main.models.Chromossome;
import main.recombinations.Crossover;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);

        Chromossome[] children = Crossover.onePoint(generator,
                new Chromossome(sc.next()),
                new Chromossome(sc.next())
        );

        Arrays.stream(children).toList().forEach(System.out::println);
    }


}
