

//package com.test.logging;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logging {

	static final Logger logger = Logger.getLogger(Logging.class.getName());
	static final String filePath = "sample_log.txt";

	public static void main(String[] args) {
		try {

			FileHandler fileHandler = new FileHandler(filePath, false);
			fileHandler.setFormatter(new SimpleFormatter());
			logger.addHandler(fileHandler);
			logger.setLevel(Level.FINE);
			System.out.println("filehandlerのした　");
			
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.CONFIG);
			logger.addHandler(consoleHandler);
			logger.setUseParentHandlers(false);
			System.out.println("consolehandlerのした　");

			logger.finest("FNST");
			logger.finer("FNR");
			logger.fine("FN");
			logger.config("CFG");
			logger.info("INF");
			logger.warning("WNG");
			logger.severe("SVR");
			System.out.println("logger.~のした　");

			throw new IOException();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {

			logger.log(Level.FINE, "エラー発生", e);
			System.out.println("エラー");

		}
	}

}