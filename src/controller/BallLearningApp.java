/**
 * 
 */
package controller;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;

import model.MainModel;
import model.IViewAdapter;
import view.MainView;
import view.IModelAdapter;

/**
 * @author davidtorres
 *
 */
public class BallLearningApp {
	
	private MainModel model;
	
	private MainView view;

	public BallLearningApp() {
		this.model = new MainModel(new IViewAdapter() {

			@Override
			public void repaint() {
				view.repaint();
			}});
		
		this.view = new MainView(new IModelAdapter() {

			@Override
			public void paint(Graphics g) {
				model.update(g);
			}

			@Override
			public void startSimulation(int numBalls, Point dimensions) {
				model.startSimulation(numBalls, dimensions);
			}});
	}
	
	public void start() {
		view.start();
		model.start();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                (new BallLearningApp()).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
        });
	}

}
