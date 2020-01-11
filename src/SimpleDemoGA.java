import java.util.Random;

//Main class
public class SimpleDemoGA {

    private Population population;
    private Individual fittest;
    private Individual secondFittest;
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

        //Calculate fitness of each individual
        demo.population.calculateFitness();

        System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.getFittest());

        demo.printIndividuals();
        System.out.println();

        //While population gets an individual with maximum fitness
        while (demo.population.getFittest() < 5) {
            ++demo.generationCount;

            //Do selection
            demo.selection();

            //Do crossover
            demo.crossover();

            //Do mutation under a random probability
            if (rn.nextInt()%7 < 5) {
                demo.mutation();
            }

            //Add fittest offspring to population
            demo.addFittestOffspring();

            //Calculate new fitness value
            demo.population.calculateFitness();

            System.out.println("Generation: " + demo.generationCount + " Fittest: " + demo.population.getFittest());

            demo.printIndividuals();
            System.out.print("Fittest offspring: ");
            for (int i = 0; i < 5; i++) {
                System.out.print(demo.getFittestOffspring().getGenes()[i]);
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

        //Select the most fittest individual
        fittest = population.selectFittest();

        //Select the second most fittest individual
        secondFittest = population.selectSecondFittest();
    }

    //Crossover
    private void crossover() {
        Random rn = new Random();

        //Select a random crossover point
        int crossOverPoint = rn.nextInt(this.numberOfGenes);

        //Swap values among parents
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.getGenes()[i];
            fittest.getGenes()[i] = secondFittest.getGenes()[i];
            secondFittest.getGenes()[i] = temp;

        }

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

    //Get fittest offspring
    private Individual getFittestOffspring() {
        if (fittest.getFitness() > secondFittest.getFitness()) {
            return fittest;
        }
        return secondFittest;
    }


    //Replace least fittest individual from most fittest offspring
    private void addFittestOffspring() {

        //Update fitness values of offspring
        fittest.calcFitness();
        secondFittest.calcFitness();

        //Get index of least fit individual
        int leastFittestIndex = population.getLeastFittestIndex();

        //Replace least fittest individual from most fittest offspring
        population.getIndividuals()[leastFittestIndex] = getFittestOffspring();
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