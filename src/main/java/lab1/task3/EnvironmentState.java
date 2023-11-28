package lab1.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EnvironmentState {
	private int length, width;
	private double dirtyRate, wallRate;
	private Map<String, Environment.LocationState> state = new HashMap<String, Environment.LocationState>();
	private String agentLocation = null;//

	public EnvironmentState(int length, int width, double dirtyRate, double wallRate) {
		this.length = length;
		this.width = width;
		this.dirtyRate = dirtyRate;
		this.wallRate = wallRate;
		initEnv();
	}

	private void initEnv(){
		Random random = new Random();
		int numOfDirt = (int) (length * width * dirtyRate);
		int numOfWall = (int) (length * width * wallRate);
		while(numOfDirt > 0){
			int x = random.nextInt(length);
			int y = random.nextInt(width);
			String location = x + "-" + y;
			if(!state.containsKey(location)){
				state.put(location, Environment.LocationState.DIRTY);
				numOfDirt--;
			}
		}
		while (numOfWall > 0){
			int x = random.nextInt(length);
			int y = random.nextInt(width);
			String location = x + "-" + y;
			if(!state.containsKey(location)){
				state.put(location, Environment.LocationState.WALL);
				numOfWall--;
			}
		}
	}

	public boolean isClean(){
		for(Environment.LocationState locationState : state.values()){
			if(locationState == Environment.LocationState.DIRTY){
				return false;
			}
		}
		return true;
	}

	public int getLength(){
		return length;
	}

	public int getWidth(){
		return width;
	}

	public void setAgentLocation(String location) {
		this.agentLocation = location;
	}

	public String getAgentLocation() {
		return this.agentLocation;
	}

	public Environment.LocationState getLocationState(String location) {
		return this.state.get(location);
	}

	public void setLocationState(String location, Environment.LocationState locationState) {
		this.state.put(location, locationState);
	}

	public void display() {
		System.out.println("Environment state: \n\t" + this.state);
	}
}