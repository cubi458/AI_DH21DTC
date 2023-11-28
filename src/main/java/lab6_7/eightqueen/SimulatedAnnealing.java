package lab6_7.eightqueen;

public class SimulatedAnnealing {
    public Node execute(Node initialState, double temperature, double rate){
        Node currentState = initialState.clone();

        while(temperature > 0.1){
            temperature *= rate;
            Node nextState = currentState.selectNextRandomCandidate();
            if(nextState.getH() == 0){
                return nextState;
            }

            double deltaE =  currentState.getH() - nextState.getH();
            double probability = Math.exp(deltaE/temperature);

            if(deltaE > 0 || probability > Math.random()){
                currentState = nextState;
            }
        }

        return currentState;
    }
}
