package assignments.assignment6_strings;

// 4. Write a program to validate email address (contains @ and proper domain format). Note: Don't use regular expressions

public class ValidateEmailAddress {

    public static boolean validateEmail(String email) {
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9') || ch == '@' || ch == '.')) {
                return false;
            }
        }
        
        if (email.contains("@")) {
            String[] parts = email.split("@");
            if (parts.length == 2) {
                String domain = parts[1];
                if (domain.contains(".")) {
                    return true;
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        String email = "example@gmail.com";
        System.out.println(validateEmail(email));
    }
}
