package lab6_7.tsp;

import java.util.Random;

public class City {
	private static Random random = new Random();
	private int x;
	private int y;

	public City() {
		this.x = random.nextInt(500);
		this.y = random.nextInt(500);
	}

	private City(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double distanceToCity(City city) {
		int x = Math.abs(getX() - city.getX());
		int y = Math.abs(getY() - city.getY());
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	@Override
	public City clone(){
		return this;
	}
}