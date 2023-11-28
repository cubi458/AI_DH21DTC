package lab1.task3;

/**
 * Class này là Agent gồm một lõi chương trình (AgentProgram)
 * Agent nhận vào 1 quan sát (Percept) và hành động tùy vào AgentProgram được lập trình như thế nào
 */
public class Agent {
	private AgentProgram program;
	private int performanceScore = 0;

	public Agent() {
	}

	public Agent(AgentProgram aProgram) {
		program = aProgram;
	}

	public Action execute(Percept p) {
		if (program != null) {
			return program.execute(p);
		}
		return NoOpAction.NO_OP;
	}

	public void cannotMoveTakeAction(){
		performanceScore -= 100;
	}

	public void suckTakeAction(){
		performanceScore += 500;
	}

	public void moveTakeAction(){
		performanceScore -= 10;
	}

	public int getPerformanceScore(){
		return performanceScore;
	}
}
