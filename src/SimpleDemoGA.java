import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Main class
public class SimpleDemoGA {

    private Population population;

    private int generationCount;
    private int numberOfGenes = 14;
    private int numberOfIndividuals = 10;

    public SimpleDemoGA() {
        this.population = new Population(this.numberOfIndividuals, this.numberOfGenes);
        this.generationCount = 0;
    }

    public static void main(String[] args) {

        Random rn = new Random();

        //f(x) = -0.2x^2+13x - 630
        //max = 841.25 at x≈32.5
        SimpleDemoGA demo = new SimpleDemoGA();

        //Initialize population
        demo.population = new Population(demo.numberOfIndividuals, demo.numberOfGenes);

        Individual fittestIndividual = demo.population.getFittestIndividual();
        System.out.println("Generation: " + demo.generationCount + " Fittest: " + fittestIndividual.getFitness());

        demo.printIndividuals();
        System.out.println();

        //While population gets an individual with maximum fitness
        while (fittestIndividual.getFitness() < 841) {
            ++demo.generationCount;

            System.out.println("\nFIRST PRINT\nBefore selection");
            demo.printIndividuals();

            //Do selection
            demo.selection();

            //Do crossover
            demo.parentSelection();

            System.out.println("\nAfter crossing, before mutation");
            demo.printIndividuals();

            //Do mutation under a random probability
            for (int i = 0; i < demo.numberOfIndividuals; i++) {
                double chance = rn.nextDouble();
                if (chance < 0.05) {
                    Individual mutant = demo.mutation(demo.population.getIndividual(i));
                    demo.population.setIndividual(i, mutant);
                }
            }

            //Calculate new fitness value
            demo.population.calculateFitness();
            fittestIndividual = demo.population.getFittestIndividual();

            System.out.println("\nGeneration: " + demo.generationCount + " Fittest: " + fittestIndividual.getFitness());

            demo.printIndividuals();
            System.out.print("Fittest offspring: ");
            for (int i = 0; i < demo.numberOfGenes; i++) {
                System.out.print(fittestIndividual.getGenes()[i]);
            }
            System.out.println();
            System.out.println();
        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+fittestIndividual.getFitness());
        System.out.print("Genes: ");
        for (int i = 0; i < demo.numberOfGenes; i++) {
            System.out.print(fittestIndividual.getGenes()[i]);
        }
        System.out.println("Genes fenotype (x=): " + fittestIndividual.getFenotype());
        System.out.println("");
        System.out.println();

    }

    //Selection
    private void selection() {
        double totalFitness = 0;
        for (int i = 0; i < numberOfIndividuals; i++) {
            double x = population.getIndividual(i).getFitness();
            totalFitness += x;
        }

        for (int i = 0; i < numberOfIndividuals; i++) {
            double selectionProbability = population.getIndividual(i).getFitness() / totalFitness;
            population.getIndividual(i).setSelectionProbability(selectionProbability);
        }

        List<Individual> newIndividuals = new ArrayList<>();

        for (int i = 0; i < numberOfIndividuals; i++) {
            double temporarySum = 0;
            Random rn = new Random();
            double chance = rn.nextDouble();
            for (int j = 0; j < numberOfIndividuals; j++) {
                temporarySum += population.getIndividual(j).getSelectionProbability();
                if (chance <= temporarySum) {
                    newIndividuals.add(population.getIndividual(j));
                    j+=numberOfIndividuals;
                }
            }
        }
        population.setIndividuals(newIndividuals);
    }

    private void parentSelection() {
        Random rn = new Random();
        List<Individual> individuals = population.getIndividuals();
        List<Individual> newIndividuals = new ArrayList<>();
        int i = 0;
        while (individuals.size() > 0) {
            Individual individual1 = individuals.get(i);
            int index;
            if(individuals.size() == 2) {
                index = 1;
            }
            else {
                index = rn.nextInt(individuals.size() - 2) + 1;
            }
            Individual individual2 = individuals.get(index);
            List<Individual> children = crossover(individual1, individual2);
            newIndividuals.addAll(children);
            individuals.remove(individual1);
            individuals.remove(individual2);

            if(individuals.size() == 1) {
                Individual lastIndividual = individuals.get(0);
                individuals.remove(lastIndividual);
                newIndividuals.add(lastIndividual);
            }
        }
        population.setIndividuals(newIndividuals);
    }

    //Crossover
    private List<Individual> crossover(Individual individual1, Individual individual2) {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(this.numberOfGenes);

        Individual child1 = new Individual(numberOfGenes);
        Individual child2 = new Individual(numberOfGenes);
        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            child1.getGenes()[i] = individual1.getGenes()[i];
            child2.getGenes()[i] = individual2.getGenes()[i];
        }

        for (int i = crossOverPoint; i < numberOfGenes; i++) {
            child1.getGenes()[i] = individual2.getGenes()[i];
            child2.getGenes()[i] = individual1.getGenes()[i];
        }
        return Arrays.asList(child1, child2);
    }

    //Mutation
    private Individual mutation(Individual individual) {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(this.numberOfGenes);

        //Flip values at the mutation point
        if (individual.getGenes()[mutationPoint] == 0) {
            individual.getGenes()[mutationPoint] = 1;
        } else {
            individual.getGenes()[mutationPoint] = 0;
        }
        return individual;
    }

    public void printIndividuals() {
        for (int i = 0; i < numberOfIndividuals; i++) {
            System.out.print("Individual " + i + ":" );
            for (int j = 0; j < numberOfGenes; j++) {
                System.out.print(population.getIndividual(i).getGenes()[j]);
            }
            System.out.println();
        }
    }
}