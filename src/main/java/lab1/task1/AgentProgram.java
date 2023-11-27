package lab1.task1;

/**
 * Lõi chương trình cho Agent,
 * hiện thực phương thức execute() để tạo ra hành động tương ứng với Percept nhận được
 */
public class AgentProgram {

	public Action execute(Percept p) {// location, status
		//TODO
		//----------- My solution -----------
		String agentLocation = p.getAgentLocation();
		Environment.LocationState locationState = p.getLocationState();
		if(locationState == Environment.LocationState.CLEAN){
			switch (agentLocation){
				case "A":
					return Environment.MOVE_RIGHT;
				case "B":
					return Environment.MOVE_LEFT;
			}
		}
		else{
			return Environment.SUCK_DIRT;
		}

		return NoOpAction.NO_OP;
	}
}