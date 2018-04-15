package components;

import java.util.List;

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
 * @see Color
 */
public abstract class Board implements Cloneable {
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
	protected Color[][] contents;
	
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
	 * 
	 * @return {@link #contents}
	 */
	public Color[][] getContents() {
		return contents;
	}
	
	/**
	 * Places a {@link Color} at the specified {@link Coordinate} on the {@link Board}.
	 * 
	 * @param c - the {@link Color} to add to the board 
	 * @param coord - the position on the board to place the piece (represented as a row-column pair in a Coordinate object)
	 * 
	 * @return <b><i>true</i></b> or <b><i>false</i></b> to indicate whether or not the method succeeded in placing the color. 
	 * This can be useful for validity check in outside code if an implementation of this method prevents placing a piece on the board if a piece is already at that position, 
	 * or if it checks whether or not a move is legal under the rules of Othello (which it probably should at some point).
	 * 
	 * @see Color
	 * @see Coordinate
	 */
	public abstract boolean set(Color c, Coordinate coord);
	
	/**
	 * Retrieves a piece on the board from the specified {@link Coordinate}.
	 * 
	 * @param coord - the position of the piece to retrieve (represented as a row-column pair in a Coordinate object)
	 * 
	 * @see Coordinate
	 */
	public abstract Color get(Coordinate coord);
	
	/**
	 * Sets up the {@link Board} so that it's configured to start a new game.
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Reversi">Othello Overview</a> (Look in the Rules section)
	 */
	public abstract void initialize();
	
	/**
	 * Returns the number of valid moves for the specified color (black or white)
	 * 
	 * @param c - an {@link Color} enum representing the color to check. 
	 * 
	 * @return the number of valid moves for the specified color, given the board's current configuration
	 */
	public abstract int countValidMoves(Color c);
	
	/**
	 * Returns the valid moves for the specified color (black or white) expressed as {@link Coordinate} objects
	 * 
	 * @param c - an {@link Color} enum representing the color to check. 
	 * 
	 * @return the valid moves for the specified color in a list, given the board's current configuration
	 */
	public abstract List<Coordinate> getValidMoves(Color c);
	
	/**
	 * Returns the winner of a game of Othello played on the current Board, if it has reached a game over state.
	 * </br></br>
	 * This method relies on the implementation of {@link #countValidMoves(Color)} working correctly.
	 * 
	 * @return the {@link Color} that won the game. (Returns {@link Color#EMPTY} if the game isn't over yet.)
	 */
	public Color winner() {
		
		if(isGameOver()) {
			
			int blackCount = 0;
			int whiteCount = 0;
			
			for (Color[] row : contents) {
				for (Color piece : row) {
					switch(piece) {
					case B:
						blackCount++;
						break;
					case W:
						whiteCount++;
						break;
					default:
						break;
					}
				}
			}
			
			if (blackCount == whiteCount) {
				return Color.EMPTY;
			}
			return blackCount > whiteCount ? Color.B : Color.W;
		} else {
			return Color.EMPTY;
		}
	}
	
	/**
	 * 
	 * Checks if a game of Othello is over, based on the current contents of the Board.
	 * 
	 * </br></br>
	 * This method relies on the implementation of {@link #countValidMoves(Color)} working correctly.
	 * 
	 * @return <b><i>true</i></b> if the game is over, <b><i>false</i></b> if it's not over
	 */
	public boolean isGameOver() {
		
		// Check if all squares on the board are occupied
		boolean isFull = true;
		for (Color[] row : contents) {
			for (Color piece : row) {
				isFull = isFull && (piece != Color.EMPTY);
			}
		}
		
		if (isFull) {
			// Board is full
			return true;
		} else {
			//Board isn't full
			if (this.countValidMoves(Color.B) == 0 && countValidMoves(Color.W) == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Counts the number of pieces of a particular color on a board.
	 * 
	 * @param c the {@link Color} to count on the Board
	 * @return the number of pieces on the Board of the specified {@link Color}
	 */
	public int countPieces(Color c) {
		int count = 0;
		for (Color[] row : contents) {
			for (Color piece : row) {
				if (piece == c) {
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * Returns a string representation of the {@link Board}'s current configuration
	 */
	@Override
	public abstract String toString();
	
	/**
	 * You can ignore this method, which is why it's implemented here to return the normal {@link #toString()}.
	 * If you want, you can override it to place special characters in the locations where there are valid moves for the specified Color.
	 * 
	 * @param c - the color to get valid moves for 
	 * </br>
	 * (passing {@link Color#B} would indicate the possible locations to place a black piece)
	 * @return a string representation of the Board
	 */
	public String toString(Color c) {
		return toString();
	}
	
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Board)) {
			return false;
		} else {
			Board b = (Board) o;
			if (contents.length == b.contents.length) {
				for (int row = 0; row < contents.length; row++) {
					if (contents[row].length == b.contents[row].length) {
						for (int col = 0; col < contents[row].length; col++) {
							if (contents[row][col] != b.contents[row][col]) {
								return false;
							}
						}
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
			return true;
		}
	}
	
	/**
	 * Creates a copy of {@link Board} on which this method is called.
	 * 
	 * </br></br>
	 * If anyone needs help implementing this method, let me know! It's helpful for implementing {@link Player#makeMove(Board)}.
	 */
	@Override
	public abstract Board clone();
}
