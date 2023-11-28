package lab6_7.tsp;

public class TSPSimulatedAnnealing {
	public double execute(Travel initialState, double startingTemperature, double coolingRate) {
		Travel travel = initialState;
		double t = startingTemperature;
		double bestDistance = travel.getDistance();
		double currentDistance = bestDistance;
		Travel bestSolution = travel;
		Travel currentSolution = travel;
		System.out.println("Initial distance of travel: " + bestDistance);

		while(t >= 0.1) {
			Travel newSolution = currentSolution.clone(); newSolution.swapCities();
			double newDistance = newSolution.getDistance();
			double deltaE = currentDistance - newDistance;
			if (deltaE > 0 || Math.exp(deltaE / t) > Math.random()) {
				//Accept if next candidate is better than current solution or with probability
				currentSolution = newSolution;
				currentDistance = newDistance;

				//Check if new solution is better than best solution we have
				if(currentDistance < bestDistance){
					bestSolution = currentSolution;
					bestDistance = currentDistance;
				}
			}

			t *= coolingRate;
		}
		return bestDistance;
	}
}
