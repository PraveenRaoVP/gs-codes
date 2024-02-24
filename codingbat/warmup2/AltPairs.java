
// Given a string, return a string made of the chars at indexes 0,1, 4,5, 8,9 ... so "kittens" yields "kien".


// altPairs("kitten") → "kien"
// altPairs("Chocolate") → "Chole"
// altPairs("CodingHorror") → "Congrr"

public String altPairs(String str) {
    boolean flag = false;
    String result = "";
    int i=0;
    while(i < str.length()) {
      result += str.charAt(i);
      if(!flag) {
        i++;
      } else {
        i+=3;
      }
      flag = !flag;
    }
    return result;
  }
  