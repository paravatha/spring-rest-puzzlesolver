package com.puzzle.solver.service;


import org.springframework.stereotype.Service;

/**
 * @Prasad Paravatha
 * Service class implementing Sudoku with backtracking algorithm
 *
 */
@Service
public class SudokuSolverServiceImpl implements SolverService {

	/*
	 * (non-Javadoc)
	 * @see com.puzzle.solver.service.SolverService#solve(java.lang.Integer[][])
	 */
	public boolean solve(Integer[][] grid) {

		return solve(0, 0, grid);
	}
	
	/*
	 *  solve Sudoku grid using recursive approach
	 */
	private boolean solve(int row, int column, Integer[][] grid) {
		if (row == 9) {
			row = 0;
			if (++column == 9)
				return true;
		}
		if (grid[row][column] != 0) {
			return solve(row + 1, column, grid);
		}
		for (int possibleValue = 1; possibleValue <= 9; ++possibleValue) {
			if (isValidSolution(row, column, possibleValue, grid)) {
				grid[row][column] = possibleValue;
				if (solve(row + 1, column, grid))
					return true;
			}
		}
		grid[row][column] = 0;
		return false;
	}

	/*
	 *  Check if the solution is valid for a given value
	 */
	private boolean isValidSolution(int row, int column, int possibleValue,
			Integer[][] grid) {
		for (int rowTraverse = 0; rowTraverse < 9; ++rowTraverse)
			if (possibleValue == grid[rowTraverse][column])
				return false;

		for (int columnTraverse = 0; columnTraverse < 9; ++columnTraverse)
			if (possibleValue == grid[row][columnTraverse])
				return false;
		
		//Check 3x3 grid for possibleValue 
		int rowOffset = (row / 3) * 3;
		int columnOffset = (column / 3) * 3;
		for (int k = 0; k < 3; ++k)
			for (int m = 0; m < 3; ++m)
				if (possibleValue == grid[rowOffset + k][columnOffset + m])
					return false;

		return true;
	}

}
