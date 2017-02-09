
import java.util.Arrays;

public class A5Q6 {

	public static void main  (String [] args) {
		
		//m is the size of the array taken from command line
		//We use 128, 1024, and 16384
		int m = Integer.parseInt(args[0]);
		
		//Set up array of size m
    int[] first = new int [m];
    int[] second = new int [m];
    int[] third = new int [m];
    int[] fourth = new int [m];
    int[] fifth = new int [m];
    
    //Loop for 1000 times
    for (int j = 0; j < 1000; j++)
    {
    	for (int i = 0; i<m; i++)
    	{
    		//Retrieve a random integer value
    		int value = (int) Math.round(Math.random());
    	
    		//Store in the arrays
    		first[i] = value;
    		second[i] = value;
    		third[i] = value;
    		fourth[i] = value;
    		fifth[i] = value;
    	}
    
    	//Sort these arrays
    	Arrays.sort(first);
    	Arrays.sort(second);
    	Arrays.sort(third);
    	Arrays.sort(fourth);
    	Arrays.sort(fifth);
    	
    	//Call sorting algorithms to test their speed on sorted arrays
    	Sorting.insertionSort(first);
    	Sorting.heapSort(second);
    	Sorting.quicksort(third);
    	Sorting.quicksortImproved(fourth);
    	Sorting.javasort(fifth);
    }
    
	}

	
}
