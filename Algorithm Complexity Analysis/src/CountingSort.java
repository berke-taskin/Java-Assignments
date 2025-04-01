public class CountingSort {
    public static int[] sort(int A[]){
        int size = A.length;
        int[] output = new int[A.length];
        int k = 0;
        for (int i = 0; i < size; i++){
            if (A[i] > k){
                k = A[i];
            }
        }
        int[] count = new int[k+1];

        for (int i = 0; i < size; i++){
            int j = A[i];
            count[j] = count[j]+1;
        }

        for (int i = 1; i < k+1; i++){
            count[i] = count[i] + count[i-1];
        }

        for (int i = size-1; i >= 0; i--){
            int j = A[i]; 
            count[j] = count[j]-1; 
            output[count[j]] = A[i];
        }

        return output;
    }
}
