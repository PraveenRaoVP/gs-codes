// Given two strings, word and a separator sep, return a big string made of count occurrences of the word, separated by the separator string.


// repeatSeparator("Word", "X", 3) → "WordXWordXWord"
// repeatSeparator("This", "And", 2) → "ThisAndThis"
// repeatSeparator("This", "And", 1) → "This"

public String repeatSeparator(String word, String sep, int count) {
    String s = "";
    for(int i=0;i<count;i++) {
      s+=word;
      if(i<count-1) s+=sep;
    }
    return s;
}

public String repeatSeparator(String word, String sep, int count) {
    if(count==0) return "";
    int counts = count;
    int rep = count-1;
    String str = "";
    
    while(counts > 0 && rep>0 ) {
      str+=word;
      str+=sep;
      counts--;
      rep--;
    }
    str+=word;
    return str;
  }
  
