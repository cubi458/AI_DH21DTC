package lab1.task3;

import java.util.Random;

/**
 * Lõi chương trình cho Agent,
 * hiện thực phương thức execute() để tạo ra hành động tương ứng với Percept nhận được
 */
public class AgentProgram {
	private Random random = new Random();

	public Action execute(Percept p) {// location, status
		//TODO
		//----------- My solution -----------
		String agentLocation = p.getAgentLocation();
		Environment.LocationState locationState = p.getLocationState();
		if(locationState == Environment.LocationState.DIRTY){
			return Environment.SUCK_DIRT;
		}
		else{
			return randomAction();
		}
	}

	public Action randomAction(){
		switch (random.nextInt(4)){
			case 0:
				return Environment.MOVE_LEFT;
			case 1:
				return Environment.MOVE_RIGHT;
			case 2:
				return Environment.MOVE_UP;
			case 3:
				return Environment.MOVE_DOWN;
		}

		return NoOpAction.NO_OP;
	}
}