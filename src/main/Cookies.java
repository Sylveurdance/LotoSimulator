package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import model.*;

/*
 * This class handles cookies (backup without connexion)
 */
public class Cookies {
	
	private File open;
	private File exit;
	private FileReader infile;
	private BufferedReader in;
	private FileWriter outfile;
	private BufferedWriter out;
	/** Name of the cookie file */
	private final String filename = "cookies.txt";
	
	private static User currentUser; // Current logged in user
	
	public Cookies() {
		try
		{
			open 	= new File(filename);
			open.createNewFile(); // Creates an empty file if not exists
			exit 	= new File("tmp"+filename);
			
	 		infile 	= new FileReader(open);
	 		outfile = new FileWriter(exit);
	 		
			in 		= new BufferedReader(infile);
			out		= new BufferedWriter(outfile);
		}
		catch (IOException e) {
			System.err.println("Log : error opening "+ filename);
		}
	}
	
	/*
	 * Access to the current user object
	 */
	public static User getCurrentUser() {
		return currentUser;
	}
	
	/*
	 * Reads the cookie file and fill User attributes
	 */
	public void readCookie(String login) {
		String line = new String("");
		try {
			line = in.readLine();
			while (line != null) {
				if (line.startsWith(login)) {
					String s_money = line.split(";")[1];
					Integer money = Integer.decode(s_money);
					if (money != null) {
						currentUser = new User(login,money);
						return;
					}
				}
				line = in.readLine();
			}
			currentUser = new User(login,1000); // new user in the game
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints user infos in the cookie file
	 */
	public void updateCookie(User loggedInUser) { 
		String s = new String("");
		s = loggedInUser.getLogin()+";"+loggedInUser.getMoney()+"\n";
		
		String line = new String("");
		try {
			line = in.readLine();
			if (line == null) { // Empty cookie file
				out.write(s);
				out.flush();
			}
			while (line != null) {
				if (line.startsWith(loggedInUser.getLogin())) {
					out.write(s);
				}
				else {
					out.write(line+"\n");
				}
				out.flush();
				line = in.readLine();
			}
			in.close();
			out.close();
			
			open.delete();
			exit.renameTo(new File(filename));
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
