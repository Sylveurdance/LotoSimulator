package view;


import javax.swing.JFrame;

/*
 * This class represents the main view of the Loto Simulator
 */
@SuppressWarnings("serial")
public class LotoGui extends JFrame {

	public LotoGui() {
		this.setTitle("Loto Simulator");
	    this.setSize(700, 600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setContentPane(new LotoPanel());
	    this.setVisible(true);
	}
	
	
}
