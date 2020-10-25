/**
 * 
 */
package model;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Random;
import java.util.PrimitiveIterator.OfLong;

/**
 * @author davidtorres
 *
 */
public class Brain {
	private Point2D.Double[] directions;
	private int size;
	private int maxVal = 1;
	private int move = 0;

	public Brain(int size) {
		this.size = size;
		this.directions = new Point2D.Double[size];
		Random random = new Random();
		for (int i = 0; i < size; i ++) {
			directions[i] = new Point2D.Double(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
		}
	}
	
	public Point2D.Double[] getDirections() {
		return directions;
	}

	public Double nextMove() {
		if (move == size) {
			return directions[size-1];
		}
		move++;
		return directions[move-1];
	}

}
