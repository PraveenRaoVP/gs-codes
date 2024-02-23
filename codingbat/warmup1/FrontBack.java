
// Given a string, return a new string where the first and last chars have been exchanged.


// frontBack("code") → "eodc"
// frontBack("a") → "a"
// frontBack("ab") → "ba"]
import java.util.Collections;

public String frontBack(String str) {
    if(str.length()<=1) {
      return str;
    }
    String s = "";
    s += str.charAt(str.length()-1);
    for(int i=1;i<str.length()-1;i++) {
      s+=str.charAt(i);
    }
    s+=str.charAt(0);
    return s;
  }
  
  public String front3(String str) {
    String front = "";
    if(str.length() >=3) {
      front = str.substring(0,3);
    } else {
      front = str;
    }
    return String.join("", Collections.nCopies(3,front));
  }
  