
// We'll say that a String is xy-balanced if for all the 'x' chars in the string, there exists a 'y' char somewhere later in the string. So "xxy" is balanced, but "xyx" is not. One 'y' can balance multiple 'x's. Return true if the given string is xy-balanced.


// xyBalance("aaxbby") → true
// xyBalance("aaxbb") → false
// xyBalance("yaaxbb") → false

public boolean xyBalance(String str) {
    if(str.length() < 1) return true;
    int xLastIndex = -1;
    int yLastIndex = -1;
    for(int i=0;i<str.length();i++) {
      if(str.charAt(i)=='x') {
        xLastIndex = i;
      }
      if(str.charAt(i) == 'y') {
        yLastIndex = i;
      }
    }
    if(xLastIndex!=-1 && yLastIndex == -1) return false;
    if(xLastIndex==-1 && yLastIndex == -1) return true;
    return xLastIndex < yLastIndex;
  }
  

