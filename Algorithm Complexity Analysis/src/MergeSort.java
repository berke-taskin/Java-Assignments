public class MergeSort {
    public static int[] sort(int A[]){
        int n = A.length;
        if (n <= 1){
            return A;
        }

        int[] left = new int[n/2];
        int[] right = new int[n-(n/2)];

        for (int i = 0; i < n/2; i++){
            left[i] = A[i];
        }
        
        for (int i = 0; i < n-(n/2); i++){
            right[i] = A[n/2 + i];
        }

        left = sort(left);
        right = sort(right);

        return merge(left,right);
    }

    public static int[] merge(int A[], int B[]){
        int[] C = new int[A.length+B.length]; 
        int i = 0, j = 0, k = 0;

        while (i < A.length && j < B.length){
            if (A[i] > B[j]){
                C[k++] = B[j++];
            } else {
                C[k++] = A[i++];
            }
        }

        while (i < A.length) {
            C[k++] = A[i++];
        }
        
        while (j < B.length) {
            C[k++] = B[j++];
        } 

        return C;
    }
}
