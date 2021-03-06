package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Cookies;
import model.RandomDrawing;
import model.User;

public class LotoPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton makeGrid;				// action button (valids a grid)
	private JButton drawing;				// action button (launch draw)
	private Vector<JButton> choices; 		// choices of the user to make his grid
	private Vector<JButton> choices_chance; // choices of the user to make his grid for n_chance
	private JLabel user_status; 			// contains user status (login & money left)
	private JLabel choose_numeros; 			// contains text (Choose your numeros)
	private JLabel choose_n_chances; 		// contains text (Choose tour n_chances)
	private JLabel current_grids; 			// contains user current grids
	private JPanel p_grid_numeros; 			// contains pictures of the grid to make
	private JPanel p_grid_chance; 			// contains pictures of the grid to make
	private JPanel p_makeGrid; 				// contains pictures of the grid to make
	private JPanel p_drawing; 				// contains pictures of the draw
	private JPanel p_loto; 					// contains loto
	private JPanel buttons; 				// contains the two actions buttons
	
	private Set<Integer> tmp_numeros;		// contains temporary choices of numbers by the user
	private Set<Integer> tmp_n_chances;		// contains temporary choices of chance numbers by the user
	
	private User currentUser; 				// Current user of the app
	private Cookies cookie;					// Cookies to store user login and money
	
	public LotoPanel() {
		// General settings
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());

		// Connexion
		Connexion connexion = new Connexion(this);
		this.add(connexion, BorderLayout.CENTER);
	}
	
	/**
	 * Init the main loto panel after connexion
	 */
	public void init() {
		// General settings
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
		
		// Initializations
		user_status 		= new JLabel();
		choose_numeros 		= new JLabel();
		choose_n_chances 	= new JLabel();
		current_grids		= new JLabel();
		p_grid_numeros 		= new JPanel();
		p_grid_chance 		= new JPanel();
		p_makeGrid 			= new JPanel();
		p_drawing 			= new JPanel();
		p_loto 				= new JPanel();
		buttons 			= new JPanel();
		
		tmp_numeros 		=  new TreeSet<Integer>();
		tmp_n_chances 		=  new TreeSet<Integer>();
		
		// user label
		user_status.setText(this.getCurrentUser().getLogin()+" : "+this.getCurrentUser().getMoney());
		user_status.setHorizontalAlignment(JLabel.CENTER);
		
		// current grids
		JPanel p_current_grids = new JPanel();
		JLabel your_current_grids = new JLabel("Your current grids");
		your_current_grids.setHorizontalAlignment(JLabel.CENTER);
		p_current_grids.setLayout(new BorderLayout());
		p_current_grids.add(your_current_grids, BorderLayout.NORTH);
		p_current_grids.add(current_grids, BorderLayout.CENTER);
		
		// loto = make Grid + drawing
		this.makeChooseGrid();
		p_drawing.setLayout(new GridLayout(1, 6));

		p_loto.setLayout(new BorderLayout());
		p_loto.add(p_makeGrid, BorderLayout.NORTH);
		p_loto.add(p_drawing, BorderLayout.CENTER);
		p_loto.add(p_current_grids, BorderLayout.SOUTH);
		
		// JPanel buttons
		makeGrid = new JButton("Valid a grid");
		drawing = new JButton("Make the draw");

		makeGrid.addActionListener(new MakeGridListener());
		drawing.addActionListener(new DrawingListener());
		drawing.setEnabled(false); // Can't launch a draw until a bet was made
		
		buttons.add(makeGrid);
		buttons.add(drawing);
		
		// Add all SubPanels to the LotoPanel
		this.add(user_status, BorderLayout.NORTH);
		this.add(p_loto, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
	}
	
	/*
	 * Gets the numeros images buttons
	 * Fills it if empty
	 */
	public Vector<JButton> getChoicesNumeros() {
		if((choices == null) || (choices.isEmpty())) {
			choices = new Vector<JButton>();
			for (int i=1;i<50;i++) {
				ImageIcon icon = new ImageIcon("./img/boule"+i+".jpg");
				Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				choices.add(i-1, new JButton(String.valueOf(i)));
				choices.get(i-1).setIcon(icon);
				choices.get(i-1).setSize(30, 30);
				choices.get(i-1).addActionListener(new NumeroListener());
			}
		}
		return choices;
	}
	
	/*
	 * Gets the NChances images buttons
	 * Fills it if empty
	 */
	public Vector<JButton> getChoicesNChances() {
		if((choices_chance == null) || (choices_chance.isEmpty())) {
			choices_chance = new Vector<JButton>();
			for (int i=1;i<11;i++) {
				ImageIcon icon = new ImageIcon("./img/boule"+i+".jpg");
				Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
				icon = new ImageIcon(img);
				choices_chance.add(i-1, new JButton(String.valueOf(i)));
				choices_chance.get(i-1).setIcon(icon);
				choices_chance.get(i-1).setSize(30, 30);
				choices_chance.get(i-1).addActionListener(new NChanceListener());
			}
		}
		return choices_chance;
	}
	
	/*
	 * Make the start grid (before the user choose any numbers)
	 */
	public void makeChooseGrid() {
		p_grid_numeros.setLayout(new GridLayout(7, 7));
		p_grid_chance.setLayout(new GridLayout(1, 10));
		// Make choices for numeros
		for (int i=1;i<50;i++) {
			this.getChoicesNumeros();
			this.getChoicesNChances();
			p_grid_numeros.add(choices.get(i-1));
			
			if(i<11) {
				// Make choices for n_chance
				p_grid_chance.add(choices_chance.get(i-1));
			}
		}
		
		// Sets JLabels for the grid
		choose_numeros.setText("Choose your numbers");
		choose_numeros.setHorizontalAlignment(JLabel.CENTER);
		choose_n_chances.setText("Choose your chance numbers");
		choose_n_chances.setHorizontalAlignment(JLabel.CENTER);
		
		p_makeGrid.setLayout(new BoxLayout(p_makeGrid, BoxLayout.PAGE_AXIS));
		p_makeGrid.add(choose_numeros);
		p_makeGrid.add(p_grid_numeros);
		p_makeGrid.add(choose_n_chances);
		p_makeGrid.add(p_grid_chance);
	}
	
	/*
	 * Displays on the screen a numero in a JLabel
	 */
	public void addImagetoPanel(Integer boule_num) {
		ImageIcon icon = new ImageIcon("./img/boule"+boule_num+".jpg");
		Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		JLabel image = new JLabel(icon);
		p_drawing.add(image);
	}
	
	/*
	 * Displays on the screen numeros (winning or made by a grid)
	 */
	public void displayImages(Set<Integer> numeros, Integer n_chance) {
		p_drawing.removeAll(); // removes all (previous) images before displaying the new ones
		Iterator<Integer> iterator1 = numeros.iterator();
		while(iterator1.hasNext()) {
			this.addImagetoPanel(iterator1.next());
		}
		this.addImagetoPanel(n_chance);
	}

	/*
	 * Action to do when the user chooses a specific numero
	 */
	class NumeroListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Integer buttonNum = Integer.valueOf(((JButton) e.getSource()).getText());
			if((buttonNum != null) && (buttonNum < 50)) {
				tmp_numeros.add(buttonNum);
				((JButton) e.getSource()).setEnabled(false);
			}
		}
	}
	
	/*
	 * Action to do when the user chooses a specific chance number
	 */
	class NChanceListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Integer buttonNum = Integer.valueOf(((JButton) e.getSource()).getText());
			if((buttonNum != null) && (buttonNum <= 10)) {
				tmp_n_chances.add(buttonNum);
				((JButton) e.getSource()).setEnabled(false);
			}
		}
	}
	
	/*
	 * Action to do when the user valids his choices
	 */
	class MakeGridListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			// Add the current user his grid
			getCurrentUser().addGrid(tmp_numeros, tmp_n_chances);
			
			// Enables again buttons
			Iterator<Integer> iterator1 = tmp_numeros.iterator();
			Iterator<Integer> iterator2 = tmp_n_chances.iterator();
			while(iterator1.hasNext()) {
		    	Integer index = Integer.valueOf(iterator1.next());
		    	choices.get(index-1).setEnabled(true);
			}
			while(iterator2.hasNext()) {
		    	Integer index = Integer.valueOf(iterator2.next());
		    	choices_chance.get(index-1).setEnabled(true);
			}

			// Clears tmp Sets
			tmp_numeros.clear();
			tmp_n_chances.clear();
			
			if (!getCurrentUser().getGrids().isEmpty()) { // if checkGrids has returned true
				// Updates User status
				user_status.setText(getCurrentUser().getLogin()+" : "+getCurrentUser().getMoney());
				
				// Displays the grid in the UI
				current_grids.setText(getCurrentUser().getGrids().toString());
				current_grids.setHorizontalAlignment(JLabel.CENTER);
				
				// Enables the draw button
				drawing.setEnabled(true);
			}
		}
	}
	
	/*
	 * Action to do when the user wants to launch a draw
	 */
	class DrawingListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			// Drawing phase
			RandomDrawing draw = new RandomDrawing();
			
			// Displays draw
			displayImages(draw.getNumeros(),draw.getN_chance());
			
			// Checks if the user did win or not
			getCurrentUser().setWins(draw);
			
			// Updates User status
			user_status.setText(getCurrentUser().getLogin()+" : "+getCurrentUser().getMoney());
			
			// Clear User played grids
			getCurrentUser().getGrids().clear();
			
			//Updates the view the grid is cleared
			current_grids.setText("");
			
			// Desables the draw button
			drawing.setEnabled(false);
			
		}
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public Cookies getCookie() {
		return cookie;
	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	public void setCookie(Cookies cookie) {
		this.cookie = cookie;
	}
}
