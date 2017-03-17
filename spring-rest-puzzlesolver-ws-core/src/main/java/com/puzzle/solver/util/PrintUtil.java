package com.puzzle.solver.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

/**
 * Util class to format 2D grid
 * @Prasad Paravatha
 *
 */

public class PrintUtil {
	private static final Logger logger = Logger.getLogger(PrintUtil.class);

	/*
	 * Prints the Grid in 2D
	 * @param gridSize
	 * @param grid as 2D Array
	 */
	public static void printGrid(int gridSize, Integer[][] grid) {
		logger.debug((Arrays.deepToString(grid)));
	}
	
	/*
	 * Converts the entire 2D grid to an array as String
	 * @param gridSize
	 * @param grid as 2D Array
	 * @return 2D array as a String
	 */
	public static String convertGridToString(int gridSize, Integer[][] grid) {
		
		List<Integer> intList = Arrays.stream(grid).flatMap(Arrays::stream)
	            .collect(Collectors.toList());
		List<String> strList = intList.stream().map(Object::toString)
                .collect(Collectors.toList());
		return strList.stream().map(Integer::parseInt).collect(Collectors.toList()).toString();
	}
}
