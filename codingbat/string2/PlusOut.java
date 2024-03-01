
// Given a string and a non-empty word string, return a version of the original String where all chars have been replaced by pluses ("+"), except for appearances of the word string which are preserved unchanged.


// plusOut("12xy34", "xy") → "++xy++"
// plusOut("12xy34", "1") → "1+++++"
// plusOut("12xy34xyabcxy", "xy") → "++xy++xy+++xy"

public String plusOut(String str, String word) {
    String ans = "";
    int n = str.length();
    for(int i=0; i<n;i++) {
      if(i+word.length() <= n && str.substring(i,i+word.length()).equals(word)) {
        ans+=word;
        i+=word.length()-1;
      } else {
        ans=ans+'+';
      }
    }
    return ans;
  }
  
