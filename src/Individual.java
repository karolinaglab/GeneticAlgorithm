import java.util.Random;

import static java.lang.Integer.parseInt;

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
        StringBuilder integerNumberBuilder = new StringBuilder();
        StringBuilder fractionalNumberBuilder = new StringBuilder();
        int plus = genes[0] == 0 ? -1 : 1;
        for (int i = 1; i < geneLength - 2; i++) {
            integerNumberBuilder.append(genes[i]);
        }
        for (int i = geneLength - 2; i < geneLength; i++) {
            fractionalNumberBuilder.append(genes[i]);
        }
        String integerNumber = integerNumberBuilder.toString();
        String fractionalNumber = fractionalNumberBuilder.toString();
        double decimal = plus * parseInt(integerNumber, 2);
        decimal += parseInt(fractionalNumber, 2) / 100;
        fitness = fitFunction(decimal);
    }

    private double fitFunction(double decimal) {
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