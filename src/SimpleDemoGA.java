import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Main class
public class SimpleDemoGA {

    private Population population;

    private int generationCount;
    private int numberOfGenes = 5;
    private int numberOfIndividuals = 5;

    public SimpleDemoGA() {
        this.population = new Population(this.numberOfIndividuals, this.numberOfGenes);
        this.generationCount = 0;
    }


    public static void main(String[] args) {

        Random rn = new Random();

        //Number of genes each individual has
       // numberOfGenes = 5;
        //Number of individuals
        //numberOfIndividuals = 5;
        SimpleDemoGA demo = new SimpleDemoGA();

        //Initialize population
        demo.population = new Population(demo.numberOfIndividuals, demo.numberOfGenes);

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.getFittest());

        demo.printIndividuals();
        System.out.println();

        //While population gets an individual with maximum fitness
        while (demo.population.getFittest() < 5) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.parentSelection();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.getFittest());

            demo.printIndividuals();
            System.out.print("Fittest offspring: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(demo.population.getFittestIndividual().getGenes()[i]);
            }
            System.out.println();
            System.out.println();

        }

        System.out.println("\nSolution found in generation " + demo.generationCount);
        System.out.println("Fitness: "+demo.population.selectFittest().getFitness());
        System.out.print("Genes: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(demo.population.selectFittest().getGenes()[i]);
        }

        System.out.println("");
        System.out.println();

    }

    //Selection
    private void selection() {
        double totalFitness = 0;
        for (int i = 0; i < numberOfIndividuals; i++) {
            totalFitness += population.getIndividual(i).getFitness();
        }

        for (int i = 0; i < numberOfIndividuals; i++) {
            double selectionProbability = population.getIndividual(i).getFitness() / totalFitness;
            population.getIndividual(i).setSelectionProbability(selectionProbability);
        }

        Individual[] newIndividuals = new Individual[numberOfIndividuals];

        for (int i = 0; i < numberOfIndividuals; i++) {
            double temporarySum = 0;
            for (int j = 0; j < numberOfIndividuals; j++) {
                temporarySum += population.getIndividual(j).getSelectionProbability();
                Random rn = new Random();
                double chance = rn.nextDouble();
                if (chance <= temporarySum) {
                    newIndividuals[i] = population.getIndividual(j);
                }
            }
        }
        population.setIndividuals(newIndividuals);
    }

    private void parentSelection() {
        Random rn = new Random();
        List<Individual> individuals = Arrays.asList(population.getIndividuals());
        List<Individual> newIndividuals = new ArrayList<>();
        int i = 0;
        while (individuals.size() > 0) {
            Individual individual1 = individuals.get(i);
            int index = rn.nextInt(individuals.size() - 2) + 1;
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
        population.setIndividuals((Individual[]) newIndividuals.toArray());
    }

    //Crossover
    private List<Individual> crossover(Individual individual1, Individual individual2) {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(this.numberOfGenes);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = individual1.getGenes()[i];
            individual1.getGenes()[i] = individual2.getGenes()[i];
            individual2.getGenes()[i] = temp;
        }
        return Arrays.asList(individual1, individual2);
    }

    //Mutation
    private void mutation() {
        Random rn = new Random();

        //Select a random mutation point
        int mutationPoint = rn.nextInt(this.numberOfGenes);

        //Flip values at the mutation point
        if (fittest.getGenes()[mutationPoint] == 0) {
            fittest.getGenes()[mutationPoint] = 1;
        } else {
            fittest.getGenes()[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(numberOfGenes);

        if (secondFittest.getGenes()[mutationPoint] == 0) {
            secondFittest.getGenes()[mutationPoint] = 1;
        } else {
            secondFittest.getGenes()[mutationPoint] = 0;
        }
    }




    public void printIndividuals() {
        for (int i = 0; i < numberOfIndividuals; i++) {
            System.out.print("Individual " + i + ":" );
            for (int j = 0; j < 5; j++) {
                System.out.print(population.getIndividuals()[i].getGenes()[j]);
            }
            System.out.println();
        }
    }

}