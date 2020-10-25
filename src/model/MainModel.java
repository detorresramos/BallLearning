/**
 * 
 */
package model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 * @author davidtorres
 *
 */
public class MainModel {

	private IViewAdapter view;
	
	private ArrayList<Ball> balls;
	
	private int _timeslice = 50;
	
	private Timer _timer = new Timer(_timeslice, (e) -> repaint());
	
	public MainModel(IViewAdapter view) {
		this.view = view;
		this.balls = new ArrayList<Ball>();
	}
	
	public void start() {

	}
	
	public void startSimulation(int numBalls, Point dimensions) {
		for (int i = 0; i < numBalls; i++) {
			balls.add(new Ball(dimensions));
		}
		_timer.start();
	}

	public void update(Graphics g) {
		for (Ball ball : balls) {
			ball.update(g);
		}
	}
	
	public void repaint() {
		view.repaint();
	}
}
