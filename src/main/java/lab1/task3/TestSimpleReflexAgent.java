package lab1.task3;

public class TestSimpleReflexAgent {
	public static void main(String[] args) {
		Environment env = new Environment(100, 100, 0.5, 0.25);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, "1-1");

		env.step();
	}
}
