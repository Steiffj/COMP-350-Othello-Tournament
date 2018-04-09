package examples;

import java.util.List;
import java.util.Random;

import components.Board;
import components.Color;
import components.Coordinate;
import components.Player;

public class StupidAI extends Player {
	
	public StupidAI(String name, Color color) {
		super(name, color);
	}
	
	/**
	 * This AI just randomly choses a move from its list of valid options.
	 */
	@Override
	public Coordinate makeMove(Board board) {
		Random rand = new Random();
		List<Coordinate> moves = board.getValidMoves(color);
		
		try {
			return moves.get(rand.nextInt(moves.size()));
		} catch(IndexOutOfBoundsException e) {
			return new Coordinate(-1, -1);
		}	
	}
}
