package io.github.slopstar.mentalmath.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;

import io.github.slopstar.mentalmath.R;
import io.github.slopstar.mentalmath.utils.PreferencesUtil;

public class DifficultySelectionActivity extends ComponentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_difficulty_selection);

		// --- Initialize interactive components ---
		Button backButton = findViewById(R.id.difficulty_selection_back_button);

		RadioGroup maxNumbersGroup = findViewById(R.id.max_numbers_radio_group);
		int checkedIdNumbers = maxNumbersGroup.getCheckedRadioButtonId();

		RadioGroup maxDigitsGroup = findViewById(R.id.max_digits_radio_group);
		int checkedIdDigits = maxDigitsGroup.getCheckedRadioButtonId();

		CheckBox operationAddition = findViewById(R.id.operation_addition_cb);
		CheckBox operationSubtraction = findViewById(R.id.operation_subtraction_cb);
		CheckBox operationMultiplication = findViewById(R.id.operation_multiplication_cb);
		CheckBox operationDivision = findViewById(R.id.operation_division_cb);

		// --- Handling user input ---
		// Max numbers selection
		maxNumbersGroup.setOnCheckedChangeListener((group, checkedId1) -> {
			RadioButton selectedButton = findViewById(checkedIdNumbers);
			int maxNumbers = Integer.parseInt(selectedButton.getText().toString());
			// Save the selected max numbers to preferences
			PreferencesUtil.setMaxNumbers(this, maxNumbers);
		});

		// Max digits selection
		maxDigitsGroup.setOnCheckedChangeListener((group, checkedId1) -> {
			RadioButton selectedButton = findViewById(checkedIdDigits);
			int maxDigits = Integer.parseInt(selectedButton.getText().toString());
			PreferencesUtil.setMaxDigits(this, maxDigits);
		});

		// Operation selection
		operationAddition.setOnCheckedChangeListener((buttonView, isChecked) -> PreferencesUtil.setAdditionEnabled(this, isChecked));
		operationSubtraction.setOnCheckedChangeListener((buttonView, isChecked) -> PreferencesUtil.setSubtractionEnabled(this, isChecked));
		operationMultiplication.setOnCheckedChangeListener((buttonView, isChecked) -> PreferencesUtil.setMultiplicationEnabled(this, isChecked));
		operationDivision.setOnCheckedChangeListener((buttonView, isChecked) -> PreferencesUtil.setDivisionEnabled(this, isChecked));

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
