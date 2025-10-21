package io.github.slopstar.mentalmath.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferencesUtil {

    private static final String PREFS_NAME = "mentalmath_prefs";

    // Keys
    public static final String KEY_MAX_NUMBERS = "pref_max_numbers"; // int: 2,3,4
    public static final String KEY_MAX_DIGITS = "pref_max_digits";   // int: 1,2,3
    public static final String KEY_OP_ADD = "pref_op_addition";     // boolean
    public static final String KEY_OP_SUB = "pref_op_subtraction";  // boolean
    public static final String KEY_OP_MUL = "pref_op_multiplication"; // boolean
    public static final String KEY_OP_DIV = "pref_op_division";     // boolean

    // Defaults
    private static final int DEFAULT_MAX_NUMBERS = 2;
    private static final int DEFAULT_MAX_DIGITS = 1;
    private static final boolean DEFAULT_OP_ADD = true;
    private static final boolean DEFAULT_OP_SUB = true;
    private static final boolean DEFAULT_OP_MUL = true;
    private static final boolean DEFAULT_OP_DIV = false;

    private PreferencesUtil() { /* no instances */ }

    private static SharedPreferences prefs(Context ctx) {
        return ctx.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Max numbers (2..4)
    public static int getMaxNumbers(Context ctx) {
        return prefs(ctx).getInt(KEY_MAX_NUMBERS, DEFAULT_MAX_NUMBERS);
    }

    public static void setMaxNumbers(Context ctx, int value) {
        if (value < 2 || value > 4) {
            throw new IllegalArgumentException("maxNumbers must be between 2 and 4");
        }
        prefs(ctx).edit().putInt(KEY_MAX_NUMBERS, value).apply();
    }

    // Max digits (1..3)
    public static int getMaxDigits(Context ctx) {
        return prefs(ctx).getInt(KEY_MAX_DIGITS, DEFAULT_MAX_DIGITS);
    }

    public static void setMaxDigits(Context ctx, int value) {
        if (value < 1 || value > 3) {
            throw new IllegalArgumentException("maxDigits must be between 1 and 3");
        }
        prefs(ctx).edit().putInt(KEY_MAX_DIGITS, value).apply();
    }

    // Addition
    public static boolean isAdditionEnabled(Context ctx) {
        return prefs(ctx).getBoolean(KEY_OP_ADD, DEFAULT_OP_ADD);
    }

    public static void setAdditionEnabled(Context ctx, boolean enabled) {
        prefs(ctx).edit().putBoolean(KEY_OP_ADD, enabled).apply();
    }

    // Subtraction
    public static boolean isSubtractionEnabled(Context ctx) {
        return prefs(ctx).getBoolean(KEY_OP_SUB, DEFAULT_OP_SUB);
    }

    public static void setSubtractionEnabled(Context ctx, boolean enabled) {
        prefs(ctx).edit().putBoolean(KEY_OP_SUB, enabled).apply();
    }

    // Multiplication
    public static boolean isMultiplicationEnabled(Context ctx) {
        return prefs(ctx).getBoolean(KEY_OP_MUL, DEFAULT_OP_MUL);
    }

    public static void setMultiplicationEnabled(Context ctx, boolean enabled) {
        prefs(ctx).edit().putBoolean(KEY_OP_MUL, enabled).apply();
    }

    // Division
    public static boolean isDivisionEnabled(Context ctx) {
        return prefs(ctx).getBoolean(KEY_OP_DIV, DEFAULT_OP_DIV);
    }

    public static void setDivisionEnabled(Context ctx, boolean enabled) {
        prefs(ctx).edit().putBoolean(KEY_OP_DIV, enabled).apply();
    }

    // Reset all preferences to defaults
    public static void resetToDefaults(Context ctx) {
        SharedPreferences.Editor e = prefs(ctx).edit();
        e.putInt(KEY_MAX_NUMBERS, DEFAULT_MAX_NUMBERS);
        e.putInt(KEY_MAX_DIGITS, DEFAULT_MAX_DIGITS);
        e.putBoolean(KEY_OP_ADD, DEFAULT_OP_ADD);
        e.putBoolean(KEY_OP_SUB, DEFAULT_OP_SUB);
        e.putBoolean(KEY_OP_MUL, DEFAULT_OP_MUL);
        e.putBoolean(KEY_OP_DIV, DEFAULT_OP_DIV);
        e.apply();
    }

    // Convenience: ensure at least one operation is enabled
    public static boolean hasEnabledOperation(Context ctx) {
        return isAdditionEnabled(ctx) || isSubtractionEnabled(ctx) || isMultiplicationEnabled(ctx) || isDivisionEnabled(ctx);
    }
}
