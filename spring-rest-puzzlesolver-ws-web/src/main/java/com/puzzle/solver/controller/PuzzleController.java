package com.puzzle.solver.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.puzzle.solver.service.SudokuSolverServiceImpl;
import com.puzzle.solver.util.PrintUtil;
import com.puzzle.solver.util.SOLVER_CONSTANTS;
import com.puzzle.solver.validation.RequestValidator;


/**
 * Controller for Puzzle solver
 * @Prasad Paravatha
 */
@RestController
public class PuzzleController {
	private static final Logger LOGGER = Logger.getLogger(PuzzleController.class);
	
	@Autowired
	RequestValidator requestValidator;
	
	@Autowired
	SudokuSolverServiceImpl sudokuSolverService;
	
	/**
	 * This method solves Sudoku puzzle and sends response as string
	 * @param request as String
	 * @param accept
	 * @return ResponseEntity<String>
	 * @throws Exception 
	 */
	@RequestMapping(
	        value = "/solve/sudoku",
	        method = RequestMethod.GET,
	        headers = "Accept=application/json",
	        produces = { "application/json" })
	public ResponseEntity<String> solveSudoku(@RequestParam("request") String request) throws Exception {
		LOGGER.debug(" in solveSudoku : request : \n" + request);
		
		String errorMessage = null;
		String responseAsString= null;
		try {
			List<String> input = Stream.of(request.trim().split(",")).collect(Collectors.toList());
			
			int inputSize = input.size();
			if (inputSize != SOLVER_CONSTANTS.GRID_SIZE * SOLVER_CONSTANTS.GRID_SIZE)
				errorMessage = SOLVER_CONSTANTS.INVALID_GRID_SIZE_ERROR;
			else {
				Integer[][] grid = new Integer[SOLVER_CONSTANTS.GRID_SIZE][SOLVER_CONSTANTS.GRID_SIZE];
				boolean duplicates = requestValidator.isInputGridValid(SOLVER_CONSTANTS.GRID_SIZE, grid, input);
				if (duplicates) {
					errorMessage = SOLVER_CONSTANTS.INPUT_DUPLICATE_ERROR;
				} else {
					LOGGER.debug("Input Sudoku grid:");
					PrintUtil.printGrid(SOLVER_CONSTANTS.GRID_SIZE, grid);
					if (sudokuSolverService.solve(grid)) {
						LOGGER.debug("Solved Sudoku grid:");
						PrintUtil.printGrid(SOLVER_CONSTANTS.GRID_SIZE, grid);
						LOGGER.debug("Reponse as String");
						responseAsString = PrintUtil.convertGridToString(SOLVER_CONSTANTS.GRID_SIZE, grid);
						LOGGER.debug(responseAsString);
						
					} else {
						errorMessage = SOLVER_CONSTANTS.NO_SOLUTION_ERROR;
					}
				}
			}
			if (errorMessage != null) {
				LOGGER.error(errorMessage);
				throw new IllegalArgumentException(errorMessage);	
			}
		} catch(Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
		return new ResponseEntity<String>(responseAsString, HttpStatus.OK);	
	}
	
	/**
	 * @return the requestValidator
	 */
	public RequestValidator getRequestValidator() {
		return requestValidator;
	}

	/**
	 * @param requestValidator the requestValidator to set
	 */
	public void setRequestValidator(RequestValidator requestValidator) {
		this.requestValidator = requestValidator;
	}

	/**
	 * @return the sudokuSolverService
	 */
	public SudokuSolverServiceImpl getSudokuSolverService() {
		return sudokuSolverService;
	}

	/**
	 * @param sudokuSolverService the sudokuSolverService to set
	 */
	public void setSudokuSolverService(SudokuSolverServiceImpl sudokuSolverService) {
		this.sudokuSolverService = sudokuSolverService;
	}	
}
