package assignments.assignment4_unconditional_statements;

public class PrintPrimeNumbers {
    public static boolean isPrime(int n) {
        // sieve of eratosthenes
        if(n==2 || n==3) {
            return true;
        }
        if(n%2==0 || n%3==0) {
            return false;
        }
        for(int i=5;i<=Math.sqrt(n);i+=6){
            if(n%i==0 || n%(i+2)==0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        for(int i=2;i<=30;i++) {
            if(isPrime(i)) {
                System.out.print(i+" ");
            }
        }
    }
}
