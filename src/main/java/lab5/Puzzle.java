package lab5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Puzzle {
	public static final int MAX_ROW = 3;// 3x3: Dimension of the puzzle map
	public static final int MAX_COL = 3;
	public static final char[] operators = { 'l', 'r', 'u', 'd' };
	private Node initialState;
	private Node goalState;

	public Puzzle() {
		this.initialState = new Node(MAX_ROW, MAX_COL);
		this.goalState = new Node(MAX_ROW, MAX_COL);
	}

	// Load initial state and goal state from files
	public void readInput(String INITIAL_STATE_MAP_PATH, String GOAL_STATE_MAP_PATH) {
		try {
			// 1 - Import map
			BufferedReader bufferedReader = new BufferedReader(new FileReader(INITIAL_STATE_MAP_PATH));

			String line = null;
			int row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					initialState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

			// 2 - Import goal state
			bufferedReader = new BufferedReader(new FileReader(GOAL_STATE_MAP_PATH));

			line = null;
			row = 0;
			while ((line = bufferedReader.readLine()) != null) {
				String[] tile = line.split(" ");
				for (int col = 0; col < tile.length; col++) {
					goalState.updateTile(row, col, Integer.parseInt(tile[col]));
				}
				row++;
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// The total number of misplaced tiles
	public int computeH1(Node currentState) {
		int output = 0;
		/* Enter your code here */
		for(int row = 0; row < currentState.getRow(); row++){
			for(int col = 0; col < currentState.getColumn(); col++){
				//Xét điều kiện != 0 cho chuẩn, trên thực tế không cần phải xét như vậy
				if(currentState.getTile(row, col) != 0
				&& currentState.getTile(row, col) == goalState.getTile(row, col)){
					output++;
				}
			}
		}
		return output;
	}

	// Using manhattanDistance above to compute H
	public int computeH2(Node currentState) {
		int result = 0;
		/* Enter your code here */
		for (int tile = 1; tile < MAX_COL * MAX_ROW; tile++){
			result += PuzzleUtils.manhattanDistance(currentState.getLocation(tile), goalState.getLocation(tile));
		}
		return result;
	}


	public Node moveWhiteTile(Node currentState, char operator) {
		Node result = new Node(currentState);
		int[] whiteTile = currentState.getLocation(0);//get white tile
		int row = whiteTile[0];
		int col = whiteTile[1];
		switch (operator){
			case 'u':
				row--;
				break;
			case 'd':
				row++;
				break;
			case 'l':
				col--;
				break;
			case 'r':
				col++;
				break;
			default:
				System.out.println("DEBUG activated!");
		}

		if(0 <= row && row < MAX_ROW && 0 <= col && col < MAX_COL){
			int tmp = currentState.getTile(row, col);
			result.updateTile(row, col, 0);
			result.updateTile(whiteTile[0], whiteTile[1], tmp);
			result.setH(computeH2(result)); //hoặc computeH1
			return result;
		}
		return null;
	}

	public List<Node> getSuccessors(Node currentState) {
		ArrayList<Node> result = new ArrayList<Node>();

		for (char operator : operators) {
			Node tmp = moveWhiteTile(currentState, operator);
			if (tmp != null) {
				result.add(tmp);
			}
		}

		return result;
	}

	public void setInitialState(Node initialState) {
		this.initialState = initialState;
	}

	public Node getInitialState() {
		return initialState;
	}

	public Node getGoalState() {
		return goalState;
	}
}
