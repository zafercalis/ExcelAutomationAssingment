package com.excel.tests;

public class Calculator {

	public static double Operate(String Operation, double number1, double number2) {
		double result = 0.0;
		switch (Operation) {

		case "addition":
			result = number1 + number2;
			break;
		case "subtraction":
			result = number1 - number2;
			break;
		case "multiplication":
			result = number1 * number2;
			break;
		case "division":
			if (number2 != 0)
				result = number1 / number2;
			else
				System.out.println("Dividing number is 0!");
			break;
		}
		// it is my first comment))
		return result;
	}
}
