package io.github.slopstar.mentalmath.utils;

public class ExpressionGenerator {
	/*
	 * Generates a random expression.
	 */
	public String generateRandomExpression(String difficulty) {
		int num1;
		int num2;

		if (difficulty.equals("easy")) {
			num1 = (int) (Math.random() * 100);
			num2 = (int) (Math.random() * 100);
		} else {
			num1 = 0;
			num2 = 0;
		}

		return num1 + " + " + num2;
	}
}
