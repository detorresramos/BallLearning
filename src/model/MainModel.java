/**
 * 
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D.Double;
import java.util.Random;

import javax.swing.Timer;

/**
 * @author davidtorres
 *
 */
public class MainModel {

	private IViewAdapter view;
	
	private Ball[] balls;
	
	private int _timeslice = 50;
	
	private Timer _timer = new Timer(_timeslice, (e) -> repaint());

	private int numBalls;

	private double fitnessSum;
	
	private Double targetPos;
	
	private int targetRadius;
	
	public MainModel(IViewAdapter view) {
		this.view = view;
	}
	
	public void startSimulation(int numBalls, Point dimensions) {
		this.numBalls = numBalls;
		balls = new Ball[numBalls];
		for (int i = 0; i < numBalls; i++) {
			balls[i] = new Ball(dimensions);
		}
		_timer.start();
		this.targetRadius = 5;
		this.targetPos = new Double(dimensions.getX() / 2.0, 0.0);
	}

	public void update(Graphics g) {
		if (allDead()) {
			balls = getNewGeneration();
		}
		for (int i = 0; i < numBalls; i++) {
			balls[i].update(g);
		}
		paintTarget(g);
	}
	
	private void paintTarget(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) targetPos.x - targetRadius, (int) targetPos.y + targetRadius, targetRadius * 2, targetRadius * 2);
	}

	private Ball[] getNewGeneration() {
		Ball[] newBalls = new Ball[numBalls];
		calculateFitness();
		for (int i = 0; i < numBalls; i++) {
			 Ball parent = chooseParent();
			 newBalls[i] = parent.makeChild();
		}
		return newBalls;
	}

	private Ball chooseParent() {
		Random random = new Random();
		double num = random.nextDouble() * fitnessSum;
		double temp = 0;
		for (int i = 0; i < numBalls; i++) {
			if (temp > num) {
				return balls[i];
			}
			temp += balls[i].getFitness(targetPos);
		}
		return null;
	}

	private void calculateFitness() {
		fitnessSum = 0;
		for (int i = 0; i < numBalls; i++) {
			fitnessSum += balls[i].getFitness(balls[i].getLocation());
		}
	}

	private boolean allDead() {
		for (int i = 0; i < numBalls; i++) {
			if (!balls[i].isDead()) {
				return false;
			}
		}
		return true;
	}

	public void repaint() {
		view.repaint();
	}
}
