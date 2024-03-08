
// Given an array of non-empty strings, return a Map<String, String> with a key for every different first character seen, with the value of all the strings starting with that character appended together in the order they appear in the array.


// firstChar(["salt", "tea", "soda", "toast"]) → {"s": "saltsoda", "t": "teatoast"}
// firstChar(["aa", "bb", "cc", "aAA", "cCC", "d"]) → {"a": "aaaAA", "b": "bb", "c": "cccCC", "d": "d"}
// firstChar([]) → {}

public Map<String, String> firstChar(String[] strings) {
    Map<String,String> map = new HashMap<>();
    for(String str: strings) {
      if(map.containsKey(str.substring(0,1))) {
        map.put(str.substring(0,1),map.get(str.substring(0,1))+str);
      } else {
        map.put(str.substring(0,1), str);
      }
    }
    return map;
  }
  