package lab6_7.eightqueen;

public class HillClimbingSearch {
    private int stepClimbed;

    public Node execute(Node initialState) {
        stepClimbed = 0;
        Node currentState = initialState;
        while (currentState != null){
            if(currentState.getH() == 0){
                return currentState;
            }

            boolean found = false;
            for(Node nextState : currentState.generateAllCandidates()){
                if(currentState.getH() > nextState.getH()){
                    currentState = nextState;
                    found = true;
                }
            }

            if(!found){
                return currentState;
            }
            else{
                stepClimbed++;
            }
        }
        return null;
    }
    public Node executeHillClimbingWithRandomRestart(Node initialState) {
        int totalStepClimbed = 0;
        int totalRestart = 0;

        Node currentState = initialState.clone();
        Node result;
        while((result = execute(currentState)).getH() != 0){
            currentState.generateBoard();
            System.out.println("Attemp " + (totalRestart + 1) + ": " + stepClimbed + " steps.");
            totalStepClimbed += stepClimbed;
            totalRestart++;
        }
        System.out.println("Total steps: " + totalStepClimbed);
        System.out.println("Total attemps: " + totalRestart);

        return result;
    }

}
