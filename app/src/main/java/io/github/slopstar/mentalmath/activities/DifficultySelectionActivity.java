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
		RadioGroup maxDigitsGroup = findViewById(R.id.max_digits_radio_group);

		CheckBox operationAddition = findViewById(R.id.operation_addition_cb);
		CheckBox operationSubtraction = findViewById(R.id.operation_subtraction_cb);
		CheckBox operationMultiplication = findViewById(R.id.operation_multiplication_cb);
		CheckBox operationDivision = findViewById(R.id.operation_division_cb);

		// --- Initialize UI from persisted preferences ---
		int prefMaxNumbers = PreferencesUtil.getMaxNumbers(this);
		switch (prefMaxNumbers) {
			case 3:
				maxNumbersGroup.check(R.id.max_numbers_3_rb);
				break;
			case 4:
				maxNumbersGroup.check(R.id.max_numbers_4_rb);
				break;
			case 2:
			default:
				maxNumbersGroup.check(R.id.max_numbers_2_rb);
				break;
		}

		int prefMaxDigits = PreferencesUtil.getMaxDigits(this);
		switch (prefMaxDigits) {
			case 2:
				maxDigitsGroup.check(R.id.max_digits_2_rb);
				break;
			case 3:
				maxDigitsGroup.check(R.id.max_digits_3_rb);
				break;
			case 1:
			default:
				maxDigitsGroup.check(R.id.max_digits_1_rb);
				break;
		}

		operationAddition.setChecked(PreferencesUtil.isAdditionEnabled(this));
		operationSubtraction.setChecked(PreferencesUtil.isSubtractionEnabled(this));
		operationMultiplication.setChecked(PreferencesUtil.isMultiplicationEnabled(this));
		operationDivision.setChecked(PreferencesUtil.isDivisionEnabled(this));

		// --- Handling user input ---
		// Max numbers selection
		maxNumbersGroup.setOnCheckedChangeListener((group, checkedId) -> {
			RadioButton selectedButton = findViewById(checkedId);
			if (selectedButton != null) {
				Object tag = selectedButton.getTag();
				int maxNumbers;
				try {
					maxNumbers = Integer.parseInt(String.valueOf(tag != null ? tag : selectedButton.getText()));
				} catch (NumberFormatException e) {
					return;
				}
				PreferencesUtil.setMaxNumbers(this, maxNumbers);
			}
		});

		// Max digits selection
		maxDigitsGroup.setOnCheckedChangeListener((group, checkedId) -> {
			RadioButton selectedButton = findViewById(checkedId);
			if (selectedButton != null) {
				Object tag = selectedButton.getTag();
				int maxDigits;
				try {
					maxDigits = Integer.parseInt(String.valueOf(tag != null ? tag : selectedButton.getText()));
				} catch (NumberFormatException e) {
					return;
				}
				PreferencesUtil.setMaxDigits(this, maxDigits);
			}
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
