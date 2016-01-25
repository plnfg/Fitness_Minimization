package binary;

import java.util.List;
import java.util.Random;

public class Population {

    final int POP_SIZE = 6;
    final float mutRate = 0.06f;
    final int GeneLength = 8;
    Random rnd = new Random();
    Individual child1;
    Individual child2;

    Individual[] init_population() {
        int i, fitness;
        Individual[] POP = new Individual[POP_SIZE];
        for (i = 0; i < POP_SIZE; i++) {
            POP[i] = new Individual();
            POP[i].setAllGenes();
            fitness = fitnessCalc(POP[i]);
            POP[i].setFitness(fitness);
        }

        return POP;
    }

    public Individual[] generatePop(Individual[] pop) {

        int i = 0;
        Individual[] new_pop = new Individual[POP_SIZE];
        Individual parent1;
        Individual parent2;
        Individual offspring1;
        Individual offspring2;

        while (i < (POP_SIZE)) {

            parent1 = Selection(2, pop);
            parent2 = Selection(2, pop);

            doCrossover(parent1, parent2);
            offspring1 = child1;
            offspring2 = child2;
            child1 = Mutuate(offspring1);
            child2 = Mutuate(offspring2);

            new_pop[i] = (child1);
            new_pop[i + 1] = (child2);

            child1 = null;
            child2 = null;
            i += 2;
        }

        return new_pop;
    }

    Individual Selection(int tour, Individual[] pop) {
        int i, j = 0;
        Individual best;

        best = null;

        while (j < tour) {
            i = rnd.nextInt(POP_SIZE);
            if (best != pop[i]) {
                if (best == null || (pop[i].getFitness() < best.getFitness())) {
                    best = pop[i];
                }
                j++;
            }
        }

        return best;

    }

    private int fitnessCalc(Individual indv) {
        int i;
        String chromosome = "";
        for (i = 0; i < 8; i++) {
            chromosome += indv.Genes.get(i);
        }

        int value = binaryToInt(chromosome);

        return (value * value) + (16 * value);
    }

    private void doCrossover(Individual parent1, Individual parent2) {
        int k = 1;
        int rnd_gene;

        while (true) {
            rnd_gene = rnd.nextInt(GeneLength);
            if ((rnd_gene < GeneLength) && k != GeneLength - 1) {
                int cutPoint = rnd_gene;
                List<String> sublist2 = parent2.Genes.subList(cutPoint, GeneLength);
                List<String> sublist1 = parent1.Genes.subList(0, cutPoint);
                child1 = new Individual();
                child1.Genes.addAll(sublist1);
                child1.Genes.addAll(sublist2);
                child1.setFitness(fitnessCalc(child1));

                sublist2 = parent1.Genes.subList(cutPoint, GeneLength);
                sublist1 = parent2.Genes.subList(0, cutPoint);
                child2 = new Individual();
                child2.Genes.addAll(sublist1);
                child2.Genes.addAll(sublist2);
                child2.setFitness(fitnessCalc(child2));

                break;
            }
        }

    }

    private Individual Mutuate(Individual child) {
        int k = 0;
        float rnd_k; // random probability for each Gene
        String gene;

        while (k < GeneLength) {
            rnd_k = rnd.nextFloat();

            if (rnd_k <= mutRate) {
                gene = child.Genes.get(k);
                if ("1".equals(gene)) {
                    child.Genes.set(k, "0");
                }
                if ("0".equals(gene)) {
                    child.Genes.set(k, "1");
                }
            }
            k++;
        }

        return child;
    }

    public Individual getFittest(Individual[] pop) {
        Individual fittest = pop[0];
        int i;
        for (i = 1; i < POP_SIZE; i++) {
            if (pop[i].getFitness() < fittest.getFitness()) {
                fittest = pop[i];
            }
        }

        return fittest;
    }

    public int binaryToInt(String number) {
        String buffer = number.substring(1);

        if (number.charAt(0) == '0') {
            return Integer.parseInt(buffer, 2);
        }
        return Integer.parseInt(buffer, 2) - 128;
    }
}
