package components;

/**
 * 
 * The base class for representing the state of an Othello board.
 * 
 * </br></br>
 * <b>Fields:</b> {@link #width}, {@link #size}, {@link #contents}
 * 
 * </br></br>
 * Classes derived from Board should be declared as follows:
 * 
 * <pre>
 * 	// Do this!
 * 	public class YourBoardClassName extends Board{@literal <}Color{@literal >} { ... }
 * </pre>
 * 
 * Technically, you can put any type parameter within the angled brackets to represent the two-sided Othello pieces.
 * </br>
 * <b>But... please don't... it'll make everyone's lives easier if you use the Color enums :)</b>
 * 
 * <pre>
 * 	// Do this if you want to everyone to get annoyed with you, and things will get confusing fast
 * 	public class YourBoardClassName extends Board{@literal <}colorLikeClass{@literal >} { ... } 
 * 
 * 	// Or this to be even more annoying and confusing...
 * 	public class YourBoardClassName{@literal <}T extends colorLikeClass{@literal >} extends Board{@literal <}colorLikeClass{@literal >} { ... } 
 * </pre>
 * 
 * (These approaches are potentially useful, but will almost certainly be more trouble than they're worth within the scope of the Othello assignment.)
 * </br></br>
 * 
 * @param <T> - an object that represents the color of a piece on the board (should be a {@link Color} enum)
 * @see Color
 */
public abstract class Board<T> implements Cloneable {
	/**
	 * The length of one side of the board. (An Othello board is 8x8, so its width is 8.)
	 */
	protected final int width;
	
	/**
	 * The total number of squares the board has. (An Othello board is 8x8, so its size is 64.)
	 * 
	 */
	protected final int size;
	
	/**
	 * The actual layout of the board, represented as a 2D array.
	 * You can use a different data structure to store the contents of the board in your child class if you prefer.
	 * You'll just have to copy the contents field into another data structure if copying from one Board object to another. 
	 * Another option is to use a secondary data structure for evaluations within your class that implements {@link Player}.
	 */
	protected T[][] contents;
	
	/**
	 * Standard constructor for the Board class. ({@link #width} should be 8 for an Othello board.)
	 * 
	 * @param width - the (square) board's side length
	 */
	public Board(int width) {
		this.width = width;
		this.size = this.width * this.width;
	}
	
	/**
	 * 
	 * @return {@link #width}
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 
	 * @return {@link #size}
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Places a {@link Color} at the specified {@link Coordinate} on the {@link Board}.
	 * 
	 * </br></br>
	 * Returns <b><i>true</i></b> or <b><i>false</i></b> to indicate whether or not the method succeeded in placing the color. 
	 * This can be useful for validity check in outside code if an implementation of this method prevents placing a piece on the board if a piece is already at that position, 
	 * or if it checks whether or not a move is legal under the rules of Othello (which it probably should at some point).
	 * 
	 * @param t - the {@link Color} to add to the board 
	 * </br>
	 * (or color-like object... see {@link Board} class documentation for an explanation of why you should use {@link Color})
	 * @param coord - the position on the board to place the piece (represented as a row-column pair in a Coordinate object)
	 * 
	 * @see Color
	 * @see Coordinate
	 */
	public abstract boolean set(T t, Coordinate coord);
	
	/**
	 * Retrieves a piece on the board from the specified {@link Coordinate}.
	 * 
	 * @param coord - the position of the piece to retrieve (represented as a row-column pair in a Coordinate object)
	 * 
	 * @see Coordinate
	 */
	public abstract T get(Coordinate coord);
	
	/**
	 * Sets up the {@link Board} so that it's configured to start a new game.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Reversi">Othello Overview</a> (Look in the Rules section)
	 */
	public abstract void initialize();
	
	/**
	 * Returns the number of valid moves for the specified color (black or white)
	 * 
	 * @param t - an object representing the color to check. 
	 * </br>
	 * (Please use {@link Color} enums. See {@link Board} as to why.)
	 * @return the number of valid moves for the specified color, given the board's current configuration
	 */
	public abstract int countValidMoves(T t);
	
	/**
	 * Returns a string representation of the {@link Board}'s current configuration
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Creates a copy of {@link Board} on which this method is called.
	 * 
	 * </br></br>
	 * If anyone needs help implementing this method, let me know! It'll be helpful for {@link Player#makeMove(Board)}.
	 */
	@Override
	public abstract Board<T> clone();
}
