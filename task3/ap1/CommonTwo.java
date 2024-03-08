// Start with two arrays of strings, a and b, each in alphabetical order, possibly with duplicates. Return the count of the number of strings which appear in both arrays. The best "linear" solution makes a single pass over both arrays, taking advantage of the fact that they are in alphabetical order.


// commonTwo(["a", "c", "x"], ["b", "c", "d", "x"]) → 2
// commonTwo(["a", "c", "x"], ["a", "b", "c", "x", "z"]) → 3
// commonTwo(["a", "b", "c"], ["a", "b", "c"]) → 3

public int commonTwo(String[] a, String[] b) {
    int count = 0;
    for(int i=0,j=0;i<a.length && j<b.length;) {
      if(a[i].compareTo(b[j]) < 0) {
        i++;
      } else if(a[i].compareTo(b[j]) > 0) {
        j++;
      } else {
        count++;
        i++;
        j++;
      }
      while(i>0 && i<a.length && a[i].equals(a[i-1])) i++;
      while(j>0 && j<b.length && b[j].equals(b[j-1])) j++;
    }
    return count;
  }