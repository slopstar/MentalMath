package io.github.slopstar.mentalmath.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExpressionGenerator {
	private int maxNumbers;
	private int maxDigits;
	private boolean additionEnabled;
	private boolean subtractionEnabled;
	private boolean multiplicationEnabled;
	private boolean divisionEnabled;
	private final List<String> enabledOperations = new ArrayList<>();

	private final Random rand = new Random();

	// --- Construction ---
	public ExpressionGenerator(Context ctx) {
		initFromPreferences(ctx);
	}

	private void initFromPreferences(Context ctx) {
		this.maxNumbers = PreferencesUtil.getMaxNumbers(ctx);
		this.maxDigits = PreferencesUtil.getMaxDigits(ctx);
		this.additionEnabled = PreferencesUtil.isAdditionEnabled(ctx);
		this.subtractionEnabled = PreferencesUtil.isSubtractionEnabled(ctx);
		this.multiplicationEnabled = PreferencesUtil.isMultiplicationEnabled(ctx);
		this.divisionEnabled = PreferencesUtil.isDivisionEnabled(ctx);
		rebuildEnabledOperations();
	}

	private void rebuildEnabledOperations() {
		enabledOperations.clear();
		if (additionEnabled) {
			enabledOperations.add("+");
		}
		if (subtractionEnabled) {
			enabledOperations.add("-");
		}
		if (multiplicationEnabled) {
			enabledOperations.add("*");
		}
		if (divisionEnabled) {
			enabledOperations.add("/");
		}
	}

	// --- Helpers ---
	private int generateRandomNumber(int digits) {
		int d = Math.max(1, digits);
		int bound = (int) Math.pow(10, d);
		return rand.nextInt(bound);
	}

	private String generateRandomOperation() {
		if (enabledOperations.isEmpty()) {
			throw new IllegalStateException("Must enable at least one operation");
		}

		return enabledOperations.get(rand.nextInt(enabledOperations.size()));
	}

	// --- Full expression ---
	public String generateRandomExpression() {
		int numCount = rand.nextInt(maxNumbers - 1) + 2; // at least 2 numbers
		StringBuilder expressionBuilder = new StringBuilder();

		for (int i = 0; i < numCount; i++) {
			int number = generateRandomNumber(maxDigits);
			expressionBuilder.append(number);

			if (i < numCount - 1) {
				String operation = generateRandomOperation();
				expressionBuilder.append(" ").append(operation).append(" ");
			}
		}

		return expressionBuilder.toString();
	}
}
