package lab6_7.tsp;

//https://www.baeldung.com/java-simulated-annealing-for-traveling-salesman
//other: http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6
public class RunAlgorithm {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Travel travel = new Travel(10);
		System.out.println("SA: " + new TSPSimulatedAnnealing().execute(travel.clone(),10, 0.9995));
		System.out.println("GA: " + new TSPGeneticAlgorithm(1000, 0.03, 100).execute(travel.clone()));
	}

}
