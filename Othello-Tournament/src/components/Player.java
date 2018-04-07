package components;

/**
 * 
 * The class upon which all players (which can be AI or actual people) are based. 
 * 
 * </br></br>
 * <b>Fields:</b> {@link #name}, {@link color}
 *
 */
public abstract class Player {
	
	/**
	 * The name of the person or AI playing (this is mostly for fun, but it will also help in keeping track of which player made what move).
	 * 
	 */
	protected final String name;
	
	/**
	 * Indicates which {@link Color} (either {@link Color#B} or {@link Color#W}) the player is for the current game.
	 * @see Color
	 */
	protected final Color color;
	
	/**
	 * Standard constructor for the {@link Player} class
	 * </br>(You will need to create a child class that inherits from Player and implements its abstract methods.)
	 * 
	 * @param name - the player's name
	 * @param color - {@link Color#B} or {@link Color#W} - the player's {@link Color} for the current game
	 * 
	 * @see Color
	 */
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}
	
	/**
	 * 
	 * @return {@link #color}
	 * @see Color
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 *
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method will contain all the work the {@link Player} (your AI) does in order to decide which move to make during its ply.
	 * 
	 * </br></br>
	 * The parameter {@link board} is a reference to the actual game {@link Board}, 
	 * the first thing you'll want to do in {@link #makeMove(Board)} is copy {@link board} to avoid mutating it.
	 * 
	 * </br></br>
	 * Since classes derived from {@link Board} provide implementation for {@link Board#clone()}, something along the lines of:
	 * 
	 * <pre>
	 * 	public Coordinate makeMove(Board<?> board) {
	 * 		Board boardCopy = board.clone();	// call {@link Board#clone()} method on the original board
	 * 		...					// use boardCopy for the rest of the method to evaluate which move to make
	 * 	}
	 * </pre>
	 * 
	 * @param board - a {@link Board} representing the state of the game at the beginning of the player's ply
	 * @return the {@link Coordinate} at which to place a piece on the board for that ply
	 * 
	 * @see Coordinate
	 */
	public abstract Coordinate makeMove(Board board);
}
