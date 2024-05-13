package codechef.start130d;

import java.util.*;
import java.lang.*;
import java.io.*;

public class PoweredParams {


    public static void main(String[] args) throws java.lang.Exception
    {
        // your code goes here
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
             int n = sc.nextInt();
		    int arr[] = new int[n+1];
		    
		    for(int i=1;i<=n;i++){
		        arr[i] = sc.nextInt();
		    }
		    
		    long ans = 0;
		    
		    for(int i=1;i<=n;i++){
		        
		        long l =(long) arr[i];
		        if(l==1){
		            ans=ans+(long)n;
		        }else{
		            long power = 1;
    		       for(int j=1;j<=n;j++){
    		           
    		           power = l * power;
    		           if(power>1000000000L){
    		               break;
    		           }else{
    		               long r = (long) arr[j];
    		               if(power<=r){
    		                   ans++;
    		               }
    		           }
    		       }
		        }
		        
		    }
		  //  System.out.println(ans);

            System.out.println(ans);
        }
    }
}
}
