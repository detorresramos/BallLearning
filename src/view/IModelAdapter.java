/**
 * 
 */
package view;

import java.awt.Graphics;
import java.awt.Point;

/**
 * @author davidtorres
 *
 */
public interface IModelAdapter {

	public void update(Graphics g);

	public void startSimulation(int numBalls, Point dimensions);
	
}
