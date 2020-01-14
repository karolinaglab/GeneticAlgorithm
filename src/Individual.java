import java.util.Random;

import static java.lang.Integer.parseInt;

//Individual class
class Individual {
    private final double TRANSLATION = 50.0;
    private double fitness;
    private int[] genes;
    private int geneLength;
    private double selectionProbability;
    private double normalizedFitness;

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
        int dividePoint = 7;
        StringBuilder integerNumberBuilder = new StringBuilder();
        StringBuilder fractionalNumberBuilder = new StringBuilder();
        int plus = genes[0] == 0 ? -1 : 1;
        for (int i = 1; i < dividePoint; i++) {
            integerNumberBuilder.append(genes[i]);
        }
        for (int i = dividePoint; i < geneLength; i++) {
            fractionalNumberBuilder.append(genes[i]);
        }
        String integerNumber = integerNumberBuilder.toString();
        String fractionalNumber = fractionalNumberBuilder.toString();
        double decimal = parseInt(integerNumber, 2) + TRANSLATION;
        int fractal = parseInt(fractionalNumber, 2);
        decimal += (double) fractal / 100.0;
        decimal *= plus;
        this.fitness = fitFunction(decimal);
    }

    private double fitFunction(double decimal) {
        return -0.2 * Math.pow(decimal, 2) + 25 * decimal + 200;
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

    public double getNormalizedFitness() {
        return normalizedFitness;
    }

    public void setNormalizedFitness(double normalizedFitness) {
        this.normalizedFitness = normalizedFitness;
    }
}