package main.selections;

import main.models.Chromossome;
import main.models.Population;

import java.util.LinkedList;
import java.util.Random;

import static main.models.Population.mapToPopSize;

public class Selection {
    public static LinkedList<Chromossome> binaryTournament(Random generator, Population population) {
        LinkedList<Chromossome> participants = population.getPopulation();
        LinkedList<Chromossome> winners = new LinkedList<>();
        int compSize = 2 * population.getSize();
        int[] competitors = generateCompetitors(generator, population, compSize);

        for (int i = 0; i < compSize - 1; i += 2) {
            int firstComp = competitors[i];
            int secComp = competitors[i + 1];

            Chromossome roundWinner = battle(participants.get(firstComp), participants.get(secComp));
            winners.add(roundWinner);
        }

        return winners;
    }

    private static int[] generateCompetitors(Random generator, Population population, int compSize) {
        int[] competitors = new int[compSize];
        for (int i = 0; i < compSize; i++) {
            double u = generator.nextDouble();
            competitors[i] = mapToPopSize(0, population.getSize() - 1, u);
        }
        return competitors;
    }

    private static Chromossome battle(Chromossome firstComp, Chromossome secComp) {
        double fitFirst = firstComp.getFitness();
        double fitSec = secComp.getFitness();
        return fitFirst >= fitSec ? firstComp : secComp;
    }

    public static LinkedList<Chromossome> roulette(Random generator, Population population) {
        LinkedList<Chromossome> participants = population.getPopulation();
        LinkedList<Chromossome> winners = new LinkedList<>();

        double totalFitness = population.getTotalFitness();
        assert (totalFitness > 0);

        int n = population.getSize();
        for (int i = 0; i < n; i++) {
            double prob = 0;
            for (int j = 0; j < population.getSize(); j++) {
                prob += participants.get(i).getFitness() / totalFitness;
                double u = generator.nextDouble();
                if (u > prob) continue;
                int index = mapToPopSize(0, population.getSize() - 1, u);
                winners.add(participants.get(index));
                break;
            }
        }
        //winners.sort(Chromossome::compareTo);
        //reverseLinkedList(winners);
        return winners;
    }

    private void reverseLinkedList(LinkedList<Chromossome> list) {
        for (int i = 0; i < (list.size() / 2); i++) {
            int end = (list.size() - 1) - i;
            Chromossome temp = list.get(end);
            list.set(end, list.get(i));
            list.set(i, temp);
        }
    }

    public static LinkedList<Chromossome> selectionWithoutReplacement(Random generator, Population population, int s) {
        //Chromossome[] children = Permutation.random(generator, )
        return null;
    }
}
