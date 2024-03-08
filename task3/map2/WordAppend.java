
// Loop over the given array of strings to build a result string like this: when a string appears the 2nd, 4th, 6th, etc. time in the array, append the string to the result. Return the empty string if no string appears a 2nd time.


// wordAppend(["a", "b", "a"]) → "a"
// wordAppend(["a", "b", "a", "c", "a", "d", "a"]) → "aa"
// wordAppend(["a", "", "a"]) → "a"

public String wordAppend(String[] strings) {
    Map<String, Integer> map = new HashMap<>();
    String result = "";
    for(String str: strings) {
      if(map.containsKey(str)) {
        map.put(str, map.get(str)+1);
        if(map.get(str)%2==0) {
          result += str;
        }
      } else {
        map.put(str, 1);
      }
    }
    return result;    
}
