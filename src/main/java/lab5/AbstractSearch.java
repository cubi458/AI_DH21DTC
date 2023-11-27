package lab5;

public abstract class AbstractSearch implements IPuzzleAlgo{
    protected String algoName;

    public AbstractSearch(String algoName){
        this.algoName = algoName;
    }

    @Override
    public Node executeWithBenchmark(Puzzle model) {
        long start = System.nanoTime();
        Node result = execute(model);
        long end = System.nanoTime();
        double time = (end - start)*1.0/1000000;

        System.out.println("Algorithm: " + algoName);
        System.out.print("Begin state:\n" + model.getInitialState());
        System.out.println("Solvable: " + (result != null));
        System.out.println("Time: " + time + " milisec.");
        System.out.println("Number of step: " + ((result != null)?(result.getG()):(-1)));
        System.out.println("---------------------------------------");

        return result;
    }
}
