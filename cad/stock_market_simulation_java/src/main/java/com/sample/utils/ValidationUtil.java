package com.sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_USERNAME_REGEX = Pattern.compile("^[a-zA-Z0-9._-]{3,}$"); // Minimum three characters, alphanumeric, dot, underscore, and hyphen

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"); // Minimum eight characters, at least one letter, one number and one special character

    public static boolean validateUserName(String userName) {
        Matcher matcher = VALID_USERNAME_REGEX.matcher(userName);
        return matcher.matches();
    }

    public static boolean validatePassword(String password) {
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return matcher.matches();
    }

    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean validateDate(String date) {
        return date.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
