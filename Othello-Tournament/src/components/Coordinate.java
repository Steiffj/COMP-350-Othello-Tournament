package components;

/**
 * 
 * A simple class to make passing row-column pairs cleaner. 
 * 
 * </br></br>
 * <b>Fields:</b> {@link #row}, {@link #col}
 * 
 * </br></br>
 * Your {@link Player} classes and {@link Board} classes don't need to work with Coordinates internally (although they can if you want them to).
 *  
 * </br></br>
 * Coordinate implements the {@link Comparable} interface with {@link #compareTo(Coordinate)} to allow for sorting Coordinate objects. 
 * This may or may not be useful, depending on how you use Coordinates within your other classes.
 * 
 * </br></br>
 * There are a couple of ways to create copies of Coordinates: 
 * either via the copy constructor {@link #Coordinate(Coordinate)}, or via the clone method {@link #clone()} which overrides the {@link Cloneable} interface.
 * 
 * </br></br>
 * The main purpose of Coordinate is to standardize the {@link Board#set(Object, Coordinate)} parameter and the {@link Player#makeMove(Board)} return type.
 * 
 * @see Board
 * @see Player
 *
 */
public class Coordinate implements Comparable<Coordinate>, Cloneable {
	
	/**
	 * The - <i>you guessed it!</i> - row component of the coordinate.
	 */
	private int row;
	
	/**
	 * The <i>*gasp*</i> column component of the coordinate.
	 */
	private int col;
	
	/**
	 * Standard constructor for the Coordinate class.
	 * 
	 * @param row - the value of the {@link #row} component of the Coordinate
	 * @param column - the value of the {@link #col} component of the Coordinate
	 */
	public Coordinate(int row, int column) {
		this.row = row;
		this.col = column;
	}
	
	/**
	 * Copy constructor for the {@link Coordinate} class. Creates a deep copy of the provided Coordinate object 
	 * (which in this case is trivial, since all fields are primitive types).
	 * 
	 * </br></br>
	 * This constructor is called in {@link #clone()}, so passing a Coordinate object into the copy constructor and calling {@link #clone()} on that same Coordinate object are effectively equivalent.
	 * 
	 * <pre>
	 * 	Coordinate original = new Coordinate(0, 0);
	 * 	Coordinate copy;
	 * 
	 * 	// These two lines do the same thing
	 * 	copy = new Coordinate(original);	// copy constructor
	 * 	copy = original.clone();		// {@link #clone()} method
	 * </pre>
	 * 
	 *  
	 * @param that - the Coordinate object to copy
	 */
	public Coordinate(Coordinate that) {
		this.row = that.row;
		this.col = that.col;
	}
	
	/**
	 * 
	 * @return {@link Coordinate#row}
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @return {@link #col}
	 */
	public int getCol() {
		return col;
	}
	
	
	/**
	 * Checks equality between {@link Coordinate}s.
	 * 
	 * @param o - the object with which to check equality
	 * @return <b>true</b> or <b>false</b>, depending on whether the Coordinates compared are equal
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (!(o instanceof Coordinate)) {
			return false;
		} else {
			Coordinate c = (Coordinate) o;
			return row == c.row && col == c.col;
		}
	}
	
	/**
	 * Compares {@link Coordinate}s based first by row number, then by column number. 
	 * </br><b>
	 * Example:</b> (0, 0) < (1, 0) < (1, 1) < (2, 0) < (2, 5), etc. where <i>(row, column)</i> represents a Coordinate object.
	 * 
	 * </br></br>
	 * If for some reason you would like to be able to sort Coordinates based on some other criteria, 
	 * you can make life more complicated by writing a subclass of Coordinate to override {@link #compareTo(Coordinate)}.
	 * 
	 * @param o - the Coordinate to be compared
	 */
	@Override
	public int compareTo(Coordinate o) {
		
		// Outer if-else-if block compares row numbers
		if (this.row > o.row) {
			return 1;
			
		} else if (this.row < o.row) {
			return -1;
			
		} else {
			
			// Inner if-else-if block compares column numbers
			if (this.col > o.col) {
				return 1;
			} else if (this.col < o.col) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	/**
	 * Creates a copy of the {@link Coordinate} object that {@link #clone()} is called on.
	 * 
	 * </br></br>
	 * @return a new Coordinate object by passing the current Coordinate (this) into the copy constructor {@link #Coordinate(Coordinate)}.
	 */
	@Override
	public Coordinate clone() {
		return new Coordinate(this);
	}
}
