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
	
	private boolean started = false;
	
	private Random random = new Random();
	
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
		this.targetPos = new Double(dimensions.getX(), 0.0);
		started = true;
	}

	public void update(Graphics g) {
		if (allDead()) {
			balls = getNewGeneration();
		}
		for (int i = 0; i < numBalls; i++) {
			balls[i].update(g);
		}
		if (started) {
			paintTarget(g);
		}
	}
	
	private void paintTarget(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int) targetPos.x - targetRadius, (int) targetPos.y - targetRadius, targetRadius * 2, targetRadius * 2);
	}

	private Ball[] getNewGeneration() {
		Ball[] newBalls = new Ball[numBalls];
		double fit = calculateFitness(balls);
		newBalls[0] = getBestPrevBall();
		for (int i = 1; i < numBalls; i++) {
			 Ball parent = chooseParent();
			 newBalls[i] = parent.makeChild();
		}
		return newBalls;
	}

//	private Ball[] refreshBalls() {
//		Ball[] refresh = new Ball[numBalls];
//		for (int i = 0; i < numBalls; i++) {
//			 refresh[i] = new Ball(balls[i].getDimensions(), balls[i].getBrain());
//		}
//		return refresh;
//	}

	private Ball getBestPrevBall() {
		double max = 0;
		Ball ball = null;
		for (int i = 0; i < numBalls; i++) {
			double fit = balls[i].getFitness(targetPos);
			 if (fit > max) {
				 max = fit;
				 ball = balls[i];
			 }
		}
		return new Ball(ball.getDimensions(), ball.getBrain());
	}

	private Ball chooseParent() {
		double num = random.nextDouble() * fitnessSum;
		double temp = 0;
		for (int i = 0; i < numBalls; i++) {
			temp += balls[i].getFitness(targetPos);
			if (temp > num) {
				return balls[i];
			}
		}
		System.out.println("SHOULDNT GET HERE");
		return null;
	}

	private double calculateFitness(Ball[] ballArray) {
		fitnessSum = 0;
		for (int i = 0; i < numBalls; i++) {
			fitnessSum += ballArray[i].getFitness(targetPos);
		}
		return fitnessSum;
	}

	private boolean allDead() {
		for (int i = 0; i < numBalls; i++) {
			if (!balls[i].isDead()) {
				return false;
			}
		}
		return balls != null;
	}

	public void repaint() {
		view.repaint();
	}
}
