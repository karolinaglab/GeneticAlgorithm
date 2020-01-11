import java.util.Random;

//Individual class
class Individual {

    private double fitness;
    private int[] genes;
    private int geneLength;
    private double selectionProbability;

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
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < geneLength; i++) {
            binary.append(genes[i]);
        }
        String binaryString = binary.toString();
        int decimal = Integer.parseInt(binaryString, 2);
        fitness = fitFunction(decimal);
    }

    private double fitFunction(int decimal) {
        return Math.pow(decimal, 2) - 4;
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

    public double getSelectionProbability() {
        return selectionProbability;
    }

    public void setSelectionProbability(double selectionProbability) {
        this.selectionProbability = selectionProbability;
    }


    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}