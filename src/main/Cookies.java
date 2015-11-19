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
	 * Reads the cookie file and fill User attributes
	 */
	public User readCookie(String login) {
		String line = new String("");
		try {
			in.mark(1+(int)open.length());
			line = in.readLine();
			while (line != null) {
				if (line.startsWith(login)) {
					String s_money = line.split(";")[1];
					Integer money = Integer.decode(s_money);
					if (money != null) {
						in.reset();
						return new User(login,money);
					}
				}
				line = in.readLine();
			}
			in.reset();
			return new User(login,1000); // new user in the game
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Prints user infos in the cookie file
	 */
	public void updateCookie(User loggedInUser) { 
		String s = new String(loggedInUser.getLogin()+";"+loggedInUser.getMoney()+"\n");	
		String line = new String("");
		boolean flag = false; // flag to know if the current User was found after reading all lines
		
		try {
			line = in.readLine();
			if (line == null) { // Empty cookie file
				out.write(s);
				out.flush();
			}
			while (line != null) { // update the user status
				if (line.startsWith(loggedInUser.getLogin())) {
					out.write(s);
					flag = true;
				}
				else { // copy the in file line into the out file
					out.write(line+"\n");
				}
				out.flush();
				line = in.readLine();
			}
			if(!flag) { // current user not found
				out.write(s);
				out.flush();
			}
			
			// closing files
			in.close();
			out.close();
			
			// delete the read file and replace it by the new updated out file
			open.delete();
			exit.renameTo(new File(filename));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
