package lab6_7.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Travel {
	private static Random random = new Random();
	private ArrayList<City> travel = new ArrayList<>();

	public Travel(int numberOfCities) {
		for (int i = 0; i < numberOfCities; i++) {
			travel.add(new City());
		}
	}

	public Travel(ArrayList<City> travel){
		this.travel = travel;
	}

	public void shuffle() {
		Collections.shuffle(travel);
	}

	public void swapCities() {
		int a = generateRandomIndex();
		int b = generateRandomIndex();
		City x = getCity(a);
		City y = getCity(b);
		setCity(a, y);
		setCity(b, x);
	}

	private int generateRandomIndex() {
		return random.nextInt(travel.size());
	}

	public City getCity(int index) {
		return travel.get(index);
	}

	private void setCity(int position, City city){
		travel.set(position, city);
	}

	public int numberOfCities(){
		return travel.size();
	}

	public double getDistance() {
		double distance = 0;
		for (int index = 0; index < travel.size() - 1; index++) {
			City starting = getCity(index);
			City destination = getCity(index + 1);
			distance += starting.distanceToCity(destination);
		}
		distance += getCity(0).distanceToCity(getCity(travel.size() - 1));

		return distance;
	}

	@Override
	public Travel clone(){
		return new Travel((ArrayList<City>) travel.clone());
	}
}
