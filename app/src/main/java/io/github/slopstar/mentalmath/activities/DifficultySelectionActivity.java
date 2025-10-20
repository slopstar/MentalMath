package io.github.slopstar.mentalmath.activities;

import android.os.Bundle;
import android.widget.Button;
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
		RadioGroup difficultyRadioGroup = findViewById(R.id.difficulty_selection_radio_group);

		// --- Load saved difficulty selection ---
		String saved = io.github.slopstar.mentalmath.utils.PreferencesUtil.getDifficulty(this);
		switch (saved) {
			case "easy":
				difficultyRadioGroup.check(R.id.difficulty_radio_easy);
				break;
			case "medium":
				difficultyRadioGroup.check(R.id.difficulty_radio_medium);
				break;
			case "hard":
				difficultyRadioGroup.check(R.id.difficulty_radio_hard);
				break;
		}

		// --- Radio group listener ---
		difficultyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
					String selectedDifficulty;
					if (checkedId == R.id.difficulty_radio_easy) {
						selectedDifficulty = "easy";
					} else if (checkedId == R.id.difficulty_radio_medium) {
						selectedDifficulty = "medium";
					} else if (checkedId == R.id.difficulty_radio_hard) {
						selectedDifficulty = "hard";
					} else {
						return; // unknown selection
					}

					io.github.slopstar.mentalmath.utils.PreferencesUtil.saveDifficulty(this, selectedDifficulty);
		});

		// --- Button listeners ---
		backButton.setOnClickListener(v -> {
			// simply finish this activity to return to the previous one
			finish();
		});

		// Handle system back gestures and hardware back consistently using the OnBackPressedDispatcher
		OnBackPressedCallback callback = new OnBackPressedCallback(true) {
			@Override
			public void handleOnBackPressed() {
				finish();
			}
		};
		getOnBackPressedDispatcher().addCallback(this, callback);
	}
}
