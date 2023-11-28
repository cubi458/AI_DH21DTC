package lab6_7.eightqueen;

public class Queen implements Cloneable{
	private int row;
	private int column;

	public Queen(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void move() {
		row += 1;
		if(row >= Node.N){
			row = 0;
		}
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		//Check 2 queens in the same row or in the same diagonal
		if(this.row == q.row || (Math.abs(this.row - q.row) == Math.abs(this.column - q.column))){
			return true;
		}
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}

	@Override
	public Queen clone(){
		return new Queen(this.row, this.column);
	}
}
