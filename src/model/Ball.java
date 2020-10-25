/**
 * 
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D.Double;

/**
 * @author davidtorres
 *
 */
public class Ball {
	
	private Brain brain;
	private Double pos;
	private Double vel;
	private int radius = 10;
	

	public Ball(Point dimensions) {
		this.pos = new Double(radius, dimensions.y - radius);
		this.vel = new Double(0,0);
		this.brain = new Brain(400);
	}
	
	public void update(Graphics g) {
		move();
		paint(g);
	}
	
	public void move() {
		Double acc = brain.nextMove();
		vel.setLocation(vel.getX() + acc.getX(), vel.getY() + acc.getY());
		pos.setLocation(vel.getX() + pos.getX(), vel.getY() + pos.getY());
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval((int) pos.x - radius, (int) pos.y - radius, radius * 2, radius * 2);
	}
}
