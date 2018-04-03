package components;

/**
 * 
 * Indicates the possible states for a piece on an Othello {@link Board}.
 * Colors are also used to denote which color a {@link Player} is playing for.
 * 
 * </br></br>
 * <b>Constants:</b> {@link #B}, {@link #W}, {@link #EMPTY}
 * 
 * @see Board
 * @see Player
 * 
 */
public enum Color {
	
	/**
	 * Black side of the piece is facing up
	 */
	B,
	
	/** 
	 * White side of the piece is facing up
	 */
	W,
	
	/**
	 * Indicates that there is no piece present at the given location
	 * (This is an alternative to using <i>null</i> to point to an empty object on a board)
	 */
	EMPTY
}
