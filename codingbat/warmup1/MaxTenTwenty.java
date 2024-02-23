
// Given 2 positive int values, return the larger value that is in the range 10..20 inclusive, or return 0 if neither is in that range.


// max1020(11, 19) → 19
// max1020(19, 11) → 19
// max1020(11, 9) → 11

public int max1020(int a, int b) {
    boolean f1 = false;
    boolean f2 = false;
    if(a>=10 && a<=20) f1 = true;
    if(b>=10 && b<=20) f2 = true;
    
    if(f1 && f2) {
      return Math.max(a,b);
    } else if(f1 && !f2) {
      return a;
    } else if(!f1 && f2) {
      return b;
    } else {
      return 0;
    }
  }
  public int max1020(int a, int b) {
    if (b > a) {
      int t = a;
      a = b;
      b = t;
    }
    
    if (a >= 10 && a <= 20) {
        return a;
    }
    if (b >= 10 && b <= 20) {
        return b;
    }
    return 0;
  }  