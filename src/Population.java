import java.util.ArrayList;
import java.util.List;

//Population class
class Population {

    private int populationSize;
    private int geneLength;
    private List<Individual> individuals;
    private int fittest = 0;

    //Initialize population
    public Population(int populationSize, int geneLength) {
        this.populationSize = populationSize;
        this.geneLength = geneLength;
        this.individuals = new ArrayList<>();

        //Create a first population pool
        for (int i = 0; i < populationSize; i++) {
            individuals.add(new Individual(geneLength));
            individuals.get(i).calcFitness();
        }
    }

    public void calculateFitness() {
        for (Individual individual : individuals) {
            individual.calcFitness();
        }
    }

    public Individual getFittestIndividual() {
        Individual max = individuals.get(0);
        for (Individual individual : individuals) {
            if (individual.getFitness() > max.getFitness()) {
                max = individual;
            }
        }
        return max;
    }

    public Individual getLeastFittestIndividual() {
        Individual min = individuals.get(0);
        for (Individual individual : individuals) {
            if (individual.getFitness() < min.getFitness()) {
                min = individual;
            }
        }
        return min;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getGeneLength() {
        return geneLength;
    }

    public void setGeneLength(int geneLength) {
        this.geneLength = geneLength;
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public void setFittest(int fittest) {
        this.fittest = fittest;
    }

    public int getFittest() {
        return fittest;
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public void setIndividual(int index, Individual individual) {
        individuals.set(index, individual);
    }

}