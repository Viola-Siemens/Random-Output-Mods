package com.hexagram2021.dependency_sorter_output.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.hexagram2021.dependency_sorter_output.DependencySorterOutput.MODID;

@SuppressWarnings("unused")
public class DSOLogger {
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static void log(Level logLevel, Object object) {
		LOGGER.log(logLevel, String.valueOf(object));
	}

	public static void error(Object object) {
		log(Level.ERROR, object);
	}

	public static void info(Object object) {
		log(Level.INFO, object);
	}

	public static void warn(Object object) {
		log(Level.WARN, object);
	}

	public static void error(String message, Object... params) {
		LOGGER.log(Level.ERROR, message, params);
	}

	public static void info(String message, Object... params) {
		LOGGER.log(Level.INFO, message, params);
	}

	public static void warn(String message, Object... params) {
		LOGGER.log(Level.WARN, message, params);
	}
}
