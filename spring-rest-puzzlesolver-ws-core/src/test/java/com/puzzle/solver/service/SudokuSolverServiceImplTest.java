package com.puzzle.solver.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.puzzle.solver.util.SOLVER_CONSTANTS;
import com.puzzle.solver.validation.RequestValidator;

/**
 * @author Prasad Paravatha
 *
 */
public class SudokuSolverServiceImplTest {

	@Autowired
	SudokuSolverServiceImpl sudokuSolverService;
	
	@Autowired
	RequestValidator requestValidator;
	
	Integer[][] grid;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		requestValidator = new RequestValidator();
		sudokuSolverService = new SudokuSolverServiceImpl();		
		String request = "x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
		List<String> input = Stream.of(request.trim().split(",")).collect(Collectors.toList());		
		grid = new Integer[SOLVER_CONSTANTS.GRID_SIZE][SOLVER_CONSTANTS.GRID_SIZE];

		
		requestValidator.isInputGridValid(SOLVER_CONSTANTS.GRID_SIZE, grid, input);		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.puzzle.solver.service.SudokuSolverServiceImpl#solve(java.lang.Integer[][])}.
	 */
	@Test
	public void testSolve() {
		sudokuSolverService.solve(grid);
		String responseAsString = convertGridToString(SOLVER_CONSTANTS.GRID_SIZE, grid);
		Assert.assertNotNull(responseAsString);
		Assert.assertTrue(responseAsString.equals("[4, 3, 5, 2, 6, 9, 7, 8, 1, 6, 8, 2, 5, 7, 1, 4, 9, 3, 1, 9, 7, 8, 3, 4, 5, 6, 2, 8, 2, 6, 1, 9, 5, 3, 4, 7, 3, 7, 4, 6, 8, 2, 9, 1, 5, 9, 5, 1, 7, 4, 3, 6, 2, 8, 5, 1, 9, 3, 2, 6, 8, 7, 4, 2, 4, 8, 9, 5, 7, 1, 3, 6, 7, 6, 3, 4, 1, 8, 2, 5, 9]"));
	}

	public static String convertGridToString(int gridSize, Integer[][] grid) {
		
		List<Integer> intList = Arrays.stream(grid).flatMap(Arrays::stream)
	            .collect(Collectors.toList());
		List<String> strList = intList.stream().map(Object::toString)
                .collect(Collectors.toList());
		return strList.stream().map(Integer::parseInt).collect(Collectors.toList()).toString();
	}
}
