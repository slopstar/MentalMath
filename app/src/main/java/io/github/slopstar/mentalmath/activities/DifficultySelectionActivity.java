package io.github.slopstar.mentalmath.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;

import io.github.slopstar.mentalmath.R;

public class DifficultySelectionActivity extends ComponentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_difficulty_selection);

		// --- Initialize interactive components ---
		Button backButton = findViewById(R.id.difficulty_selection_back_button);

		RadioGroup maxNumbersGroup = findViewById(R.id.max_numbers_radio_group);
		int checkedId = maxNumbersGroup.getCheckedRadioButtonId();

		RadioGroup maxDigitsGroup = findViewById(R.id.max_digits_radio_group);
		int checkedIdDigits = maxDigitsGroup.getCheckedRadioButtonId();

		CheckBox operationAddition = findViewById(R.id.operation_addition_cb);
		CheckBox operationSubtraction = findViewById(R.id.operation_subtraction_cb);
		CheckBox operationMultiplication = findViewById(R.id.operation_multiplication_cb);
		CheckBox operationDivision = findViewById(R.id.operation_division_cb);

		// --- Exiting the activity ---
		// Back button
		backButton.setOnClickListener(v -> {
			// simply finish this activity to return to the previous one
			finish();
		});

		// System back button
		OnBackPressedCallback callback = new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				finish();
			}
		};
		getOnBackPressedDispatcher().addCallback(this, callback);
	}
}
