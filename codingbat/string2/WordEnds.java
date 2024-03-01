
// Given a string and a non-empty word string, return a string made of each char just before and just after every appearance of the word in the string. Ignore cases where there is no char before or after the word, and a char may be included twice if it is between two words.


// wordEnds("abcXY123XYijk", "XY") → "c13i"
// wordEnds("XY123XY", "XY") → "13"
// wordEnds("XY1XY", "XY") → "11"

public String wordEnds(String str, String word) {
    String ans = "";
    int n = str.length();
    int m = word.length();
    for(int i=0; i<n;i++) {
      if(i+m <= n && str.substring(i,i+m).equals(word)) {
        if(i>0) ans+=str.charAt(i-1);
        if(i+m<n) ans+=str.charAt(i+m);
        i+=m-1;
      }
    }
    return ans;
  }
