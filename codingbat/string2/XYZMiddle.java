// Given a string, does "xyz" appear in the middle of the string? To define middle, we'll say that the number of chars to the left and right of the "xyz" must differ by at most one. This problem is harder than it looks.


// xyzMiddle("AAxyzBB") → true
// xyzMiddle("AxyzBB") → true
// xyzMiddle("AxyzBBB") → false


public boolean xyzMiddle(String str) {
    if(str.length() < 3) return false;
    int mid = str.length()/2;
    
    if(str.length()%2==1) {
      return str.substring(mid-1,mid+2).equals("xyz");
    } else {
      return str.substring(mid-1,mid+2).equals("xyz") || str.substring(mid-2,mid+1).equals("xyz") ;
    }
}

