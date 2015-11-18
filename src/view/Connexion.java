package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.Cookies;

/*
 * This class handles the user connexion
 */
public class Connexion extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private String login; // login of the user
	private JTextField login_field;
	private LotoPanel loto; // main app
	
	public Connexion(LotoPanel loto) {
		
		this.loto = loto;
		
		this.setLayout(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel login_label = new JLabel("Enter your login");
		login_label.setHorizontalAlignment(JLabel.CENTER);
		
		login_field = new JTextField("");
		login_field.setPreferredSize(new Dimension(150, 30));
		login_field.addKeyListener(new loginListener());
		
		this.add(login_label, cs);
		this.add(login_field, cs);

	}
	
	class loginListener implements KeyListener {

		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
                login = login_field.getText();
                if (login != null) {
                	setVisible(false);
                	loto.setCookie(new Cookies());
        			loto.setCurrentUser(loto.getCookie().readCookie(getLogin()));
        			loto.init();
                }
            }
			
		}

		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}
		
	}
	
	public String getLogin() {
        return login;
    }
	
}
