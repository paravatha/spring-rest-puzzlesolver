package com.puzzle.solver.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
/**
 * Validator class to validate request grid
 * @Prasad Paravatha
 *
 */
@Service
public class RequestValidator {
	
	/*
	 * Check if input grid is valid
	 * @param gridSize
	 * @param grid as 2D Array
	 * @param input as String List
	 * @return boolean if grid is valid or not
	 */
	public boolean isInputGridValid(int gridSize, Integer[][] grid,
			List<String> input) throws Exception {
		boolean duplicates = false;

		for (int row = 0; row < gridSize; row++) {
			List<Integer> rowAray = new ArrayList<>();
			for (int column = 0; column < gridSize; ++column) {
				grid[row][column] = input.get(row * gridSize + column)
						.equalsIgnoreCase("x") ? 0 : Integer.valueOf(input
						.get(row * gridSize + column));
				if (grid[row][column] != 0)
					rowAray.add(grid[row][column]);
			}
			duplicates = hasDuplicatesInArray(rowAray);
			if (duplicates == true)
				return duplicates;
		}
		for (int column = 0; column < gridSize; column++) {
			List<Integer> columnAray = new ArrayList<>();
			for (int row = 0; row < gridSize; row++) {
				if (grid[row][column] != 0)
					columnAray.add(grid[row][column]);
			}
			duplicates = hasDuplicatesInArray(columnAray);
			if (duplicates == true)
				return duplicates;
		}
		return duplicates;
	}

	/*
	 *  Check if a given array(row or column) has duplicate values 
	 *  @param List of Integers
	 *  @return boolean if array contain duplicates
	 */
	public boolean hasDuplicatesInArray(List<Integer> array) {
		Set<Integer> duplicatedNumbersRemovedSet = new HashSet<>();
		Set<Integer> duplicatedNumbersSet = array.stream()
				.filter(n -> !duplicatedNumbersRemovedSet.add(n))
				.collect(Collectors.toSet());
		return duplicatedNumbersSet.size() > 0;
	}
}
