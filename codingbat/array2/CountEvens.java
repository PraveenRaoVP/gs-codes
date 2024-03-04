/*
Return the number of even ints in the given array. Note: the % "mod" operator computes the remainder, e.g. 5 % 2 is 1.


countEvens([2, 1, 2, 3, 4]) → 3
countEvens([2, 2, 0]) → 3
countEvens([1, 3, 5]) → 0
 */

public int strDist(String str, String sub) {
    if(str.length() < sub.length()) return 0;
    if(str.substring(0,sub.length()).equals(sub))
    {
      if(str.substring(str.length()-sub.length()).equals(sub)) {
        return str.length();
      }
      return strDist(str.substring(0,str.length()-1),sub);
    }
    return strDist(str.substring(1), sub);
  }
  