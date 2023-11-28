package lab1.task3;

/**
 * Class này đại diện cho các hành động của Agent (DynamicAction và NoOpAction)
 * Các hành động trong bài tập gồm "LEFT", "RIGHT", "SUCK", "NoOp"
 */
public abstract class Action {
	public abstract boolean isNoOp();
}