
// Given two strings, a and b, create a bigger string made of the first char of a, the first char of b, the second char of a, the second char of b, and so on. Any leftover chars go at the end of the result.


// mixString("abc", "xyz") → "axbycz"
// mixString("Hi", "There") → "HTihere"
// mixString("xxxx", "There") → "xTxhxexre"

public String mixString(String a, String b) {
    int i=0,j=0;
    String newStr = "";
    while(i<a.length() && j<b.length()) {
      newStr+=a.charAt(i);
      newStr+=b.charAt(j);
      i++;
      j++;
    }
    while(i<a.length()) {
      newStr+=a.charAt(i);
      i++;
    }
    while(j<b.length()) {
      newStr+=b.charAt(j);
      j++;
    }
    return newStr;
}

