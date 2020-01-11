import java.util.Random;

//Individual class
class Individual {

    private int fitness;
    private int[] genes;
    private int geneLength;

    public Individual(int geneLength) {

        this.geneLength = geneLength;
        this.genes = new int[geneLength];
        Random rn = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Math.abs(rn.nextInt() % 2);
        }
        this.fitness = 0;
    }

    //Calculate fitness
    public void calcFitness() {

        this.fitness = 0;
        for (int i = 0; i < geneLength; i++) {
            if (this.genes[i] == 1) {
                ++this.fitness;
            }
        }
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getGeneLength() {
        return geneLength;
    }

    public void setGeneLength(int geneLength) {
        this.geneLength = geneLength;
    }
}