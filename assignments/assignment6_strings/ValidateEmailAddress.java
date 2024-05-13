package assignments.assignment6_strings;

// 4. Write a program to validate email address (contains @ and proper domain format). Note: Don't use regular expressions

public class ValidateEmailAddress {

    public static boolean validateEmail(String email) {
        boolean hasAt = false;
        int lastOccurenceOfDot = -1;
        for(int i=0;i<email.length();i++) {
            if(email.charAt(i) == '@') {
                hasAt = true;
            }
        }
        if(hasAt) {
            return true;
        }
        for(int i=email.length()-1;i>=0;i--) {
            if(email.charAt(i) == '.') {
                lastOccurenceOfDot = i;
                break;
            }
        }
        if(lastOccurenceOfDot != -1 && lastOccurenceOfDot != email.length()-1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String email = "example@example.com";
        System.out.println(validateEmail(email));
    }
}
