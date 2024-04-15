package leetcode.mentor_questions.april13;
public class CountPrimes {
    public int countPrimes(int n) {
        if(n<=2) return 0;
        boolean[] isPrime = new boolean[n+1];
        for(int i=0;i<=n;i++) {
            isPrime[i] = true;
        }


        for (int p=2;p*p<=n;p++) {
            if (isPrime[p]==true) {
                for (int i=p*p;i<=n;i+=p)
                    isPrime[i] = false;
            }
        }

        int count = 0;
        for(int i=2; i<n;i++) {
            if(isPrime[i]) {
                count++;
            }
        }

        return count;

    }
}
