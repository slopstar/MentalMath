package io.github.slopstar.mentalmath.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import io.github.slopstar.mentalmath.R;
import io.github.slopstar.mentalmath.utils.ExpressionGenerator;

public class MainActivity extends Activity {
	private TextView expressionTextView;
	private Button generateExpressionButton;
	private TextView difficultyLevelTextView;
	private Button setDifficultyButton;
	private ExpressionGenerator expressionGenerator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize UI components
		// --- Non-interactive components ---
		expressionTextView = findViewById(R.id.expression_textview);

		// --- Interactive components ---
		generateExpressionButton = findViewById(R.id.generate_expression_button);
		setDifficultyButton = findViewById(R.id.set_difficulty_button);

		// Methods
		// --- method utils ---

		// --- button listeners ---
		generateExpressionButton.setOnClickListener(v -> {
			String expression = expressionGenerator.generateRandomExpression();
			expressionTextView.setText(String.valueOf(expression));
		});

		setDifficultyButton.setOnClickListener(v -> {
			Intent intent = new Intent(MainActivity.this, DifficultySelectionActivity.class);
			startActivity(intent);
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
