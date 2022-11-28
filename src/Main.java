import main.models.Chromossome;
import main.models.Population;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random generator = new Random(0);
        Scanner sc = new Scanner(System.in);
        Population population = new Population();
        int n = 0;


        while (n < 4) {
            population.addChromossome(
                    new Chromossome(
                            sc.next(),
                            Double.parseDouble(sc.next())
                    ));
            n++;
        }

        population.roulette(generator, n).forEach(System.out::println);

    }


}
