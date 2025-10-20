package io.github.slopstar.mentalmath.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferencesUtil {
	private static final String PREFS_NAME = "mental_math_prefs";
	private static final String KEY_DIFFICULTY_LEVEL = "pref_difficulty_level";

	private PreferencesUtil() {}

	public static void saveDifficulty(Context ctx, String difficulty) {
		SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		prefs.edit().putString(KEY_DIFFICULTY_LEVEL, difficulty).apply();
	}

	public static String getDifficulty(Context ctx) {
		SharedPreferences prefs = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		return prefs.getString(KEY_DIFFICULTY_LEVEL, "easy"); // default to "easy"
	}
}
