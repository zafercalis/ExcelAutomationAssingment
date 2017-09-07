package com.excel.tests;

import org.junit.Test;

public class CalculatorTest {
	
	
	@Test
	public void testMyCalculator(){
		
		System.out.println(Calculator.Operate("addition", 3.5, 2.1));
		
		System.out.println(Calculator.Operate("subtraction", 678, 2.1));
		
		System.out.println(Calculator.Operate("multiplication",7, 2.1));

		System.out.println(Calculator.Operate("division", 3.5, 0));

		System.out.println(Calculator.Operate("addition",7, 2.1));

		System.out.println(Calculator.Operate("subtraction", 3.5, 0));
		// Changes made by Zafer on 9/7/2017
		// just for practice
		
		/*
		 * we like to collaborate
		 */

		//practice is good always
	}

}
