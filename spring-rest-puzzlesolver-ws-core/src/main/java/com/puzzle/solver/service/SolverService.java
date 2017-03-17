package com.puzzle.solver.service;

/**
 * Interface for Solver Service
 * @author Prasad Paravatha
 *
 */
public interface SolverService {
	
	/**
	 * Solve method which returns true if Sudoku is solved
	 * @param grid 2 dimensional array 
	 * @return true or false
	 */
	boolean solve(Integer[][] grid);
	
}
