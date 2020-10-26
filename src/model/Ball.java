/**
 * 
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.util.Random;

/**
 * @author davidtorres
 *
 */
public class Ball {
	
	private Brain brain;
	private Double pos;
	private Double vel;
	private int radius = 5;
	private boolean dead = false;
	private Point dims;
	private double mutationProb = 0.1;

	public Ball(Point dimensions) {
		this.pos = new Double(radius, dimensions.y - radius);
		this.vel = new Double(0,0);
		this.dims = dimensions;
		this.brain = new Brain(400);
	}
	
	public Ball(Point dimensions, Brain brain) {
		this.pos = new Double(radius, dimensions.y - radius);
		this.vel = new Double(0,0);
		this.dims = dimensions;
		this.brain = brain;
	}
	
	public void update(Graphics g) {
		if (!dead) {
			move();
			if (pos.x < 0 || pos.y < 0 || pos.x > dims.getX() || pos.y > dims.getY()) {
				dead = true;
			}
		}
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

	public boolean isDead() {
		return dead;
	}

	public Ball makeChild() {
		int brainSize = brain.getSize();
		Double[] directions = brain.getDirections();
		Double[] newDirections = new Double[brainSize];
		Random random = new Random();
		for (int i = 0; i < brainSize; i ++) {
			if (random.nextInt(1) < mutationProb) {
				newDirections[i] = new Double(random.nextDouble() * 2 - 1, random.nextDouble() * 2 - 1);
			} else {
				newDirections[i] = directions[i];
			}
		}
		return new Ball(dims, new Brain(brainSize, newDirections));
	}

	public double getFitness(Double target) {
		return 1.0 / (1 + target.distance(pos));
	}
	
	public Double getLocation() {
		return pos;
	}
}
