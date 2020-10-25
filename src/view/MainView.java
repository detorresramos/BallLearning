package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

/**
 * @author davidtorres
 *
 */
public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private IModelAdapter model;
	private JPanel contentPane;
	private final JPanel centerPanel = new JPanel() {
		/**
		 * Auto generated id used for panel serialization.
		 */
		private static final long serialVersionUID = -1541461513238897110L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			model.paint(g);
		}
	};
	private final JPanel panel = new JPanel();
	private final JButton startButton = new JButton("Start Simulation");
	private final JTextField numBallsTextField = new JTextField();

	/**
	 * Create the frame.
	 */
	public MainView(IModelAdapter model) {
		numBallsTextField.setToolTipText("numballs for simulation");
		numBallsTextField.setColumns(10);
		this.model = model;
		initGUI();
	}
	
	public void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(centerPanel, BorderLayout.CENTER);
		setContentPane(contentPane);
		
		contentPane.add(panel, BorderLayout.NORTH);
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Point dimensions = new Point(centerPanel.getWidth(), centerPanel.getHeight());
				try {
					model.startSimulation(Integer.parseInt(numBallsTextField.getText()), dimensions);
				} catch (NumberFormatException exception) {
					model.startSimulation(50, dimensions);
				}
				
			}
		});
		startButton.setToolTipText("start the simulation");
		
		panel.add(startButton);
		
		panel.add(numBallsTextField);
	}
	
	public void start() {
		this.setVisible(true);
	}

}
