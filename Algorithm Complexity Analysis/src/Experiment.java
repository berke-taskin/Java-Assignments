import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Experiment {
    public static void SortEXP(Integer[] A){
        int[] sizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        double[][] randomTimes = new double[3][10];
        double[][] sortedTimes = new double[3][10];
        double[][] reversedTimes = new double[3][10];
        int Count = 0;

        for (int size : sizes){
            int[] sortArray1 = new int[size];
            int[] sortedArray1 = new int[size];
            int[] reversedArray1 = new int[size];
            double sum = 0; double sum2 = 0; double sum3 = 0;
            double sum4 = 0; double sum5 = 0; double sum6 = 0;
            double sum7 = 0; double sum8 = 0; double sum9 = 0;

            for (int i=0; i < 10; i++){
                List<Integer> list = Arrays.asList(A);
                Collections.shuffle(list);  
                for(int j = 0; j < size; j++){
                    sortArray1[j] = list.get(j);
                }
                //Random Input Data
                //Insertion Sort
                long time1 = System.currentTimeMillis();
                sortedArray1 = InsertionSort.sort(sortArray1);
                long time2 = System.currentTimeMillis();
                sum = sum + (time2-time1);
                //Merge Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = MergeSort.sort(sortArray1);
                time2 = System.currentTimeMillis();
                sum2 = sum2 + (time2-time1);
                //Counting Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = CountingSort.sort(sortArray1);
                time2 = System.currentTimeMillis();
                sum3 = sum3 + (time2-time1);

                //Sorted Input Data
                //Insertion Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = InsertionSort.sort(sortedArray1);
                time2 = System.currentTimeMillis();
                sum4 = sum4 + (time2-time1);
                //Merge Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = MergeSort.sort(sortedArray1);
                time2 = System.currentTimeMillis();
                sum5 = sum5 + (time2-time1);
                //Counting Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = CountingSort.sort(sortedArray1);
                time2 = System.currentTimeMillis();
                sum6 = sum6 + (time2-time1);

                //Reversely Sorted Input Data
                for(int j = 0; j < sortedArray1.length; j++){
                    reversedArray1[j] = sortedArray1[sortedArray1.length-j-1];
                }
                //Insertion Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = InsertionSort.sort(reversedArray1);
                time2 = System.currentTimeMillis();
                sum7 = sum7 + (time2-time1);
                //Merge Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = MergeSort.sort(reversedArray1);
                time2 = System.currentTimeMillis();
                sum8 = sum8 + (time2-time1);
                //Counting Sort
                time1 = System.currentTimeMillis();
                sortedArray1 = CountingSort.sort(reversedArray1);
                time2 = System.currentTimeMillis();
                sum9 = sum9 + (time2-time1);
            }
            
            //Random Input Data
            randomTimes[0][Count] = sum/10;
            randomTimes[1][Count] = sum2/10;
            randomTimes[2][Count] = sum3/10;
            System.out.println("Random Input Data for n = "+ size + ": ");
            System.out.println("Insertion Sort: " + (sum/10) + " ms");
            System.out.println("Merge Sort: " + (sum2/10) + " ms");
            System.out.println("Counting Sort: " + (sum3/10) + " ms");
            System.out.println();

            //Sorted Input Data
            sortedTimes[0][Count] = sum4/10;
            sortedTimes[1][Count] = sum5/10;
            sortedTimes[2][Count] = sum6/10;
            System.out.println("Sorted Input Data for n = "+ size + ": ");
            System.out.println("Insertion Sort: " + (sum4/10) + " ms");
            System.out.println("Merge Sort: " + (sum5/10) + " ms");
            System.out.println("Counting Sort: " + (sum6/10) + " ms");
            System.out.println();

            //Reversely Sorted Input Data
            reversedTimes[0][Count] = sum7/10;
            reversedTimes[1][Count] = sum8/10;
            reversedTimes[2][Count] = sum9/10;
            System.out.println("Reversely Sorted Input Data for n = "+ size + ": ");
            System.out.println("Insertion Sort: " + (sum7/10) + " ms");
            System.out.println("Merge Sort: " + (sum8/10) + " ms");
            System.out.println("Counting Sort: " + (sum9/10) + " ms");
            System.out.println();

            Count++;
        }

        try {
            PlottingGraphs.showAndSaveChart("Tests on Random Data", sizes, randomTimes, "Insertion Sort", "Merge Sort", "Counting Sort");
            PlottingGraphs.showAndSaveChart("Tests on Sorted Data", sizes, sortedTimes, "Insertion Sort", "Merge Sort", "Counting Sort");
            PlottingGraphs.showAndSaveChart("Tests on Reversely Sorted Data", sizes, reversedTimes, "Insertion Sort", "Merge Sort", "Counting Sort");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SearchEXP(Integer[] A){
        int[] sizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};
        double[][] searchTimes = new double[3][10];
        int Count = 0;

        for (int size : sizes){
            int[] searchArray1 = new int[size];
            int[] sortedArray1 = new int[size];
            double sum = 0; double sum2 = 0; double sum3 = 0;
            List<Integer> list = Arrays.asList(A);
            Collections.shuffle(list);  

            for(int j = 0; j < size; j++){
                searchArray1[j] = list.get(j);
            }

            sortedArray1 = MergeSort.sort(searchArray1);
            Random random = new Random();
            int randomNum = searchArray1[random.nextInt(searchArray1.length)];

            for (int i=0; i < 1000; i++){
                //Random Linear Search
                long time1 = System.nanoTime();
                LinearSearch.search(searchArray1, randomNum);
                long time2 = System.nanoTime();
                sum = sum + (time2-time1);

                //Sorted Linear Search
                time1 = System.nanoTime();
                LinearSearch.search(sortedArray1, randomNum);
                time2 = System.nanoTime();
                sum2 = sum2 + (time2-time1);

                //Sorted Binary Search
                time1 = System.nanoTime();
                BinarySearch.search(sortedArray1, randomNum);
                time2 = System.nanoTime();
                sum3 = sum3 + (time2-time1);
            }

            //Search Results
            searchTimes[0][Count] = sum/1000;
            searchTimes[1][Count] = sum2/1000;
            searchTimes[2][Count] = sum3/1000;
            System.out.println("Search Times for n = "+ size + ": ");
            System.out.println("Random Number Picked: " + randomNum);
            System.out.println("Linear search (random data): " + (sum/1000) + " ns");
            System.out.println("Linear search (sorted data): " + (sum2/1000) + " ns");
            System.out.println("Binary search (sorted data): " + (sum3/1000) + " ns");
            if (size != sizes[sizes.length-1]){
                System.out.println();
            }
            Count++;
        }

        //Plotting Graph
        try {
            PlottingGraphs.showAndSaveChart("Tests on Searching", sizes, searchTimes, "Linear search (random data)", "Linear search (sorted data)",
            "Binary search (sorted data)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
