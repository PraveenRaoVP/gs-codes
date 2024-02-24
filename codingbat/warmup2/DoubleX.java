// Given a string, return true if the first instance of "x" in the string is immediately followed by another "x".


// doubleX("axxbb") → true
// doubleX("axaxax") → false
// doubleX("xxxxx") → true

boolean doubleX(String str) {
    for(int i=0;i<str.length()-1;i++) {
      if(str.charAt(i)=='x' && str.charAt(i+1) == 'x') {
        return true;
      } else if(str.charAt(i) == 'x' && str.charAt(i+1)!='x') {
        return false;
      }
    }
      return false;
  }
  