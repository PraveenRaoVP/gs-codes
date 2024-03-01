
// Given a string, return the length of the largest "block" in the string. A block is a run of adjacent chars that are the same.


// maxBlock("hoopla") → 2
// maxBlock("abbCCCddBBBxx") → 3
// maxBlock("") → 0

public int maxBlock(String str) {
    if(str.length() < 1) return 0;
    int maxCount = 1;
    for(int i=0;i<str.length()-1;i++) {
      if(str.charAt(i)==str.charAt(i+1)) {
        int count = 2;
        for(int j=i+1;j<str.length()-1;j++) {
          if(str.charAt(j)==str.charAt(j+1)) {
            count++;
          } else {
            break;
          }
        }
        if(count > maxCount) maxCount = count;
      }
    }
    return maxCount;
  }
  
