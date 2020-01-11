//Population class
class Population {

    private int populationSize;
    private int geneLength;
    private Individual[] individuals;
    private int fittest = 0;

    //Initialize population
    public Population(int populationSize, int geneLength) {
        super();
        this.populationSize = populationSize;
        this.geneLength = geneLength;
        this.individuals = new Individual[populationSize];

        //Create a first population pool
        for (int i = 0; i < populationSize; i++) {
            individuals[i] = new Individual(geneLength);
            individuals[i].calcFitness();
        }
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

    public Individual[] getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Individual[] individuals) {
        this.individuals = individuals;
    }

    public void setFittest(int fittest) {
        this.fittest = fittest;
    }

    public int getFittest() {
        return fittest;
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }
}