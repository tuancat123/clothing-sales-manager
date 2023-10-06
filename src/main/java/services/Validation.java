package services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static boolean isMatch(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        String regex = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$";
        return isMatch(username, regex);
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+$";
        return isMatch(password, regex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        return isMatch(phoneNumber, regex);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return isMatch(email, regex);
    }

}
