
// Given three ints, a b c, one of them is small, one is medium and one is large. Return true if the three values are evenly spaced, so the difference between small and medium is the same as the difference between medium and large.


// evenlySpaced(2, 4, 6) → true
// evenlySpaced(4, 6, 2) → true
// evenlySpaced(4, 6, 3) → false

public boolean evenlySpaced(int a, int b, int c) {
    int max = Math.max(a, Math.max(b,c));
    int min = Math.min(a, Math.min(b,c));
    int mid = a + b + c - max - min;
    return Math.abs(max-mid) == Math.abs(mid-min);
  }
  