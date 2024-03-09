package exercism;

public class SqueakyClean {
    static String clean(String identifier) {
        StringBuilder result = new StringBuilder();
        for(int i=0;i<identifier.length();i++) {
            if(identifier.charAt(i)==' ') {
                result.append("_");
            } else if(identifier.charAt(i)=='-') {
                if(i < identifier.length()-1 && Character.isAlphabetic(identifier.charAt(i+1))) {
                    result.append(identifier.substring(i+1, i+2).toUpperCase());
                    i++;
                }
            } else if(Character.isDigit(identifier.charAt(i))) {
                if(identifier.charAt(i)=='4') {
                    result.append("a");
                } else if(identifier.charAt(i)=='3') {
                    result.append("e");
                } else if(identifier.charAt(i)=='0') {
                    result.append("o");
                } else if(identifier.charAt(i)=='1') {
                    result.append("l");
                } else if(identifier.charAt(i)=='7') {
                    result.append("t");
                } else {
                    result.append(identifier.charAt(i));
                }
            } else if(Character.isAlphabetic(identifier.charAt(i))) {
                result.append(identifier.charAt(i));
            }
        }
        return result.toString();
    }
}

