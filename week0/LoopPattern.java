package week0;

public class LoopPattern {

    public static int calculateNoOfElements(int n) {
        if(n==0) return 0;
        return n+calculateNoOfElements(n-1);
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] matrix = new int[n][n];
        int noOfElements = calculateNoOfElements(n);
        int k=1;
        int i=0;
        int j=0;
        int turns = 1;
        int bx2=n-1,by2=n-1;
        int bx1=0,by1=0;
        while(noOfElements>0) {
            if(turns%3==1) 
            {
                bx1++;by1++;
                while(i<bx2 && j<by2) {
                    matrix[i++][j++] = k++;
                }
                turns++;
            } else if(turns%3==2) {
                by1++;
                by2--;
                while(j>=by1) {
                    matrix[i][j--] = k++;
                }
                turns++;
            } else {
                bx2--;
                bx1++;
                while(i>=bx1) {
                    matrix[i--][j] = k++;
                }
                turns++;
            }
            noOfElements--;
        }
        for(int l=0;i<n;i++) {
            for(int m=0;m<n;m++) {
                // if(matrix[l][m]==0) {
                //     System.out.print("  ");
                // } else {
                    System.out.print(matrix[l][m]+" ");
                // }
            }
            System.out.println();
        }
    }
}
