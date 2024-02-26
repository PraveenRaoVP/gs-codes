
// Given a string, if one or both of the first 2 chars is 'x', return the string without those 'x' chars, and otherwise return the string unchanged. This is a little harder than it looks.


// withoutX2("xHi") → "Hi"
// withoutX2("Hxi") → "Hi"
// withoutX2("Hi") → "Hi"
// withoutX2("xHi") → "Hi"	"Hi"	OK	
// withoutX2("Hxi") → "Hi"	"Hxi"	X	
// withoutX2("Hi") → "Hi"	"Hi"	OK	
// withoutX2("xxHi") → "Hi"	"Hi"	OK	
// withoutX2("Hix") → "Hix"	"Hix"	OK	
// withoutX2("xaxb") → "axb"	"axb"	OK	
// withoutX2("xx") → ""	""	OK	
// withoutX2("x") → ""	"x"	X	
// withoutX2("") → ""	""	OK	
// withoutX2("Hello") → "Hello"	"Hello"	OK	
// withoutX2("Hexllo") → "Hexllo"	"Hexllo"	OK	
// withoutX2("xHxllo") → "Hxllo"	"Hxllo"	OK	

public String withoutX2(String str) {
  if(str.length() > 1) {
    if(str.charAt(0) == 'x') {
      str = str.substring(1);
      if(str.charAt(0) == 'x') {
        str = str.substring(1);
      }
    } else if(str.charAt(1) == 'x') {
      str = str.charAt(0) + str.substring(2);
    }
  } else if(str.length() == 1 && str.charAt(0) == 'x') {
    str = "";
  }
  return str;
}



