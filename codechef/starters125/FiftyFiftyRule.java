package codechef.starters125;

public class FiftyFiftyRule {
    
}

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(x < 50) System.out.println("Z");
            else if(y<50) System.out.println("F");
            else System.out.println("A");
        }
	}
}
