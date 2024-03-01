/*
Given a string, compute a new string by moving the first char to come after the next two chars, so "abc" yields "bca". Repeat this process for each subsequent group of 3 chars, so "abcdef" yields "bcaefd". Ignore any group of fewer than 3 chars at the end.


oneTwo("abc") → "bca"
oneTwo("tca") → "cat"
oneTwo("tcagdo") → "catdog"
 */

public String oneTwo(String str) {
    int howManyTrio = str.length()/3;
    String ans = "";
    int i=0;
    while(howManyTrio>0) {
      String s = str.substring(i,i+3);
      ans = ans + str.charAt(i+1)+str.charAt(i+2)+str.charAt(i);
      i+=3;
      howManyTrio--;
    }
    return ans;
  }
  