/**
 * 
 */
package model;

import java.awt.geom.Point2D.Double;
import java.util.Random;

/**
 * @author davidtorres
 *
 */
public class Brain {
	private Double[] directions;
	private int size;
	private int maxVal = 1;
	private int move = 0;

	public Brain(int size) {
		this.size = size;
		this.directions = new Double[size];
		Random random = new Random();
		for (int i = 0; i < size; i ++) {
			directions[i] = new Double(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
		}
	}
	
	public Brain(int size, Double[] directions) {
		this.size = size;
		this.directions = directions;
	}
	
	public Double[] getDirections() {
		return directions;
	}
	
	public int getSize() {
		return size;
	}

	public Double nextMove() {
		if (move == size) {
			return directions[size-1];
		}
		move++;
		return directions[move-1];
	}

}
