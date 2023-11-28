package lab6_7.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TSPGeneticAlgorithm {
    private int numOfFilial;
    private double mutationRate;
    private int populationSize;
    private List<Travel> population;
    private Random random = new Random();

    public TSPGeneticAlgorithm(int numOfFilial, double mutationRate, int populationSize) {
        this.numOfFilial = numOfFilial;
        this.mutationRate = mutationRate;
        this.populationSize = populationSize;
    }

    private void initPopulation(Travel initialState){
        this.population = new ArrayList<>(populationSize);
        for(int i = 0; i < populationSize; i++){
            Travel instance = initialState.clone();
            instance.shuffle();
            population.add(instance);
        }
    }

    public double execute(Travel initialState){
        initPopulation(initialState);
        double bestDistance = Double.MAX_VALUE;
        for(int filial = 0; filial < numOfFilial; filial++){
            List<Travel> newPopulation = new ArrayList<>(populationSize);
            for(int i = 0; i < populationSize; i++){
                Travel parent1 = chooseBestParentFromK(10);
                Travel parent2 = chooseBestParentFromK(10);
                Travel child = crossover(parent1, parent2);

                if(mutationRate > Math.random()){
                    child.swapCities();
                }
                newPopulation.add(child);
                bestDistance = Math.min(bestDistance, child.getDistance());
            }
            population = newPopulation;
        }

        return bestDistance;
    }

    public Travel chooseBestParentFromK(int k){
        Travel bestTravel = population.get(random.nextInt(populationSize));
        double bestDistance = bestTravel.getDistance();

        for(int i = 1; i < k; i++){
            Travel travel = population.get(random.nextInt(populationSize));
            double distance = travel.getDistance();
            if(distance < bestDistance){
                bestTravel = travel;
                bestDistance = distance;
            }
        }
        return bestTravel;
    }

    public Travel crossover(Travel parent1, Travel parent2){
        int numCities = parent1.numberOfCities();
        int pivot = random.nextInt(numCities);
        ArrayList<City> travel = new ArrayList<>(numCities);
        for(int i = 0; i < pivot; i++){
            travel.add(parent1.getCity(i));
        }
        for(int i = 0; i < numCities; i++){
            if(!travel.contains(parent2.getCity(i))){
                travel.add(parent2.getCity(i));
            }
        }

        return new Travel(travel);
    }
}
