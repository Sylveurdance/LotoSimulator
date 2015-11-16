package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Output of the application
 * @author Sylvain
 * @since 28/12/13
 */
public class Log
{
	/** Log file */
	private FileWriter outfile;
	/** Descriptor of the output */
	private PrintWriter out;
	/** Descriptor of the output */
	private PrintWriter res;
	/** Name of the log file */
	private static final String journal = "journal.log";
	
	/**
	 * Constructor of Log object
	 */
	public Log()
	{
		try
		{
			outfile = new FileWriter(journal, true);
	 		out		= new PrintWriter(outfile);
		}
		catch (IOException e) {
			System.err.println("Log : error opening "+ journal);
		}
	}
	
	/**
	 * Tests if the files exists
	 * @param index of the file to test
	 * @return true if exists, false otherwise
	 */
	public boolean exists()
	{
		return (res != null);
	}
	
	/**
	 * Print to result file
	 */
	public void print(String s)
	{ 
		res.print(s);
		res.flush();

	}
	
	/**
	 * Begins session
	 */
	public void start()
	{ 
		Date 		date 		= new Date();
		DateFormat 	dateFormat 	= new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String 		dateString 	= dateFormat.format(date);
		
		if (out != null) {
			out.println("----------------------------------------------------------------------");
			out.println("NEW PROGRAM SESSION");
			out.println("Date : " + dateString);
			out.println("----------------------------------------------------------------------");			
			out.println("");
		}
	}
	
	/**
	 * Ends session
	 */
	public void end()
	{
		System.out.println("Program complete");
		
		if (out != null) {
			out.println("Program complete");
			out.println("");
			out.flush();
			out.close();
		}
	}

	/**
	 * Displays an error message
	 * @param message content of the message to display
	 */
	public void error(String message)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String dateString = dateFormat.format(date);
		
		if (out != null) {
			out.print("On " + dateString + " : ");
			out.println("FATAL ERROR : " + message);
			out.println("Ending program");
			out.println("");
			out.println("");
			out.close();
		}
		System.out.println("FATAL ERROR : " + message + "!");
		System.out.println("Ending program !");
	}

	/**
	 * Displays an error message due to an exception
	 * @param err description of the exception
	 * @param s content of the message to display
	 */
	public void error(Exception err, String s)
	{	
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String dateString = dateFormat.format(date);
		
		if (out != null) {
			out.print("On " + dateString);
			out.print(", on class "+s+" : ");
			out.println(err.toString());
			out.println("Ending program");
			out.println("");
			out.println("");
			err.printStackTrace();
			out.close();
		}
	}
	
	/**
	 * Displays a warning message
	 * @param message content of the message to display
	 */
	public void warning(String message)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String dateString = dateFormat.format(date);
		
		if (out != null) {
			out.print("On " + dateString + " : ");
			out.println("WARNING : " + message);
		}
		System.out.println("WARNING : " + message + "!");
	}
	
	/**
	 * Displays an information message
	 * @param message content of the message to display
	 */
	public void info(String message)
	{
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String dateString = dateFormat.format(date);
		
		if (out != null) {
			out.print("On " + dateString + " : ");
			out.println("Info : " + message);
		}
		System.out.println(message);
	}
}