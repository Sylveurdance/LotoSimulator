package main;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.*;

public class LotoSimulator {

	/**
	 * Main class: launchs the app
	 */
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
		final LotoPanel content = new LotoPanel();
		frame.add(content);
		frame.setMinimumSize(new Dimension(1000,800));
		frame.setTitle("Loto Simulator");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setVisible(true);
		frame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent arg0) {
				if (JOptionPane.showConfirmDialog(frame, "Are you sure to close this window?", "Really Closing?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
							content.getCookie().updateCookie(content.getCurrentUser());
							frame.setVisible(false);
							frame.dispose();
							System.exit(0);
			        }
		    }
			
			public void windowClosed(WindowEvent arg0) {}
			public void windowActivated(WindowEvent arg0) {}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
	}

}
