package lab1.task3;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");

	public enum LocationState {
		CLEAN, DIRTY, WALL
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(int length, int width, double dirtyRate, double wallRate) {
		envState = new EnvironmentState(length, width, dirtyRate, wallRate);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		//----------- My solution -----------
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		//----------- My solution -----------
		if(action == SUCK_DIRT){
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
			agent.suckTakeAction();
		}
		else{
			String[] ss = envState.getAgentLocation().split("-");
			int x = Integer.valueOf(ss[0]);
			int y = Integer.valueOf(ss[1]);
			int length = envState.getLength();
			int width = envState.getWidth();

			switch (action.toString()){
				case "LEFT":
					x--;
					break;
				case "RIGHT":
					x++;
					break;
				case "UP":
					y--;
					break;
				case "DOWN":
					y++;
					break;
			}

			if(x < 0 || x >= length
					|| y < 0 || y >= width
					|| envState.getLocationState(x + "-" + y) == LocationState.WALL){
				agent.cannotMoveTakeAction();
			}
			else{
				envState.setAgentLocation(x + "-" + y);
				agent.moveTakeAction();
			}
		}
		return envState;

	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		//----------- My solution -----------
		String agentLocation = envState.getAgentLocation();
		LocationState locationState = envState.getLocationState(agentLocation);
		return new Percept(agentLocation, locationState);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);
		int score = agent.getPerformanceScore();

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction + "\tAgent Score: " + score);

		isDone = es.isClean();
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}

	public boolean isDone(){
		return isDone;
	}
}
