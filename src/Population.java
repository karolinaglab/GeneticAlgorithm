import java.util.*;

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
        Optional<Individual> max = individuals.stream()
                .max(Comparator.comparingDouble(Individual::getFitness));
        return max.orElse(null);
    }

    public Individual getLeastFittestIndividual() {
        Optional<Individual> min = individuals.stream()
                .min(Comparator.comparingDouble(Individual::getFitness));
        return min.orElse(null);
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
}