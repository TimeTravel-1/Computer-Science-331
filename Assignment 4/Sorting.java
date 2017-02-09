/**
 *  Implementation of various sorting algorithms
 *  Adapted from Data Structures: Abstraction and Design by Koffman and Wolfgang
 *  @author David Ng
 */

import java.util.Arrays;

public class Sorting {
	
		/**Sorts the array using insertion sort algorithm.
		 * pre: Integer array A contains integers
		 * post: Integer array A is sorted
		 * @param Integer array A to be sorted
		 */
		public static void insertionSort(int [] A)
		{
			for (int nextPos = 1; nextPos < A.length; nextPos++)
			{
				//insert element at position nextPos in the sorted subarray
				insert(A, nextPos);
			}
		}
		
		/**Inserts the element at nextPos
		 * pre: A[0...nextPos-1] is sorted
		 * post: A[0...nextPos] is sorted
		 * @param Integer array A to be sorted
		 * @param The position nextPos of the element to insert
		 */
		private static void insert(int [] A, int n)
		{
			int next = n;
			int nextVal = A[next];	//Element to insert
			while (next > 0 && nextVal<A[next-1])
			{
				A[next] = A[next-1];	//Shift down
				next--;								//Check next smaller element
			}
			
			//Insert nextVal at nextPos
			A[next] = nextVal;
		}
		
		/**Sorts the array using heap sort algorithm.
		 * pre: Integer array A contains integers
		 * post: Integer array A is sorted
		 * @param Integer array A to be sorted
		 */
		public static void heapSort(int [] A)
		{
			buildHeap(A);
			shrinkHeap(A);
		}
		
		/**buildheap transforms the array into a heap
		 * pre: The array contains at least one item
		 * post: All items in array are in heap order
		 * @param Integer array A to be transformed to a heap
		 */
		private static void buildHeap(int [] A)
		{
			int n = 1;
			while (n < A.length){
				n++;								//Add new item to heap and reheap
				int child = n-1;
				int parent = (child-1)/2;		//Find parent
				while (parent >= 0 && A[parent] < A[child])
				{
					swap(A, parent, child);
					child = parent;
					parent = (child-1)/2;
				}
			}
		}
		
		/**shrinkheap transforms the heap into a sorted array
		 * pre: All items in the array are in heap order
		 * post: The array is sorted
		 * @param Integer array A to be sorted
		 */
		private static void shrinkHeap(int [] A)
		{
			int n = A.length;
			//A[n...A.length-1] is sorted
			while(n > 0)
			{
				n--;
				swap(A, 0, n);
				//A[1...n-1] forms a heap
				//A[n...A..length-1] is sorted
				int parent = 0;
				while(true)
				{
					int leftChild = 2*parent+1;
					if(leftChild >= n)
					{
						break;	//No more children
					}
					int rightChild = leftChild + 1;
					int maxChild = leftChild;	
					if (rightChild < n && A[leftChild] < A[rightChild])	//There is a right child
					{
						maxChild = rightChild;
					}
					
					//If parent is smaller than the larger child,
					if (A[parent] < A[maxChild])
					{
						//Swap parent with child
						swap(A, parent, maxChild);
						//Continue at child level
						parent = maxChild;
					}
					else	//Heap property is restored
					{
						break;	//Exit loop
					}
				}
			}
		}
		
		/**Swaps the items in A[i] with A[j]
		 * @param Integer array A that contains the items
		 * @param i the index of one item
		 * @param j the index of the other item
		 */
		private static void swap(int [] A, int i, int j)
		{
			int temp = A[i];
			A[i] = A[j];
			A[j] = temp;
		}
		
		/**Sorts the array using quick sort algorithm.
		 * pre: Integer array A contains integers
		 * post: Integer array A is sorted
		 * @param Integer array A to be sorted
		 */
		public static void quicksort(int [] A)
		{
			//Sort the entire array
			quicksort(A, 0, A.length-1);
		}
		
		/**Sorts a part of the array using quick sort algorithm.
		 * pre: Integer array A contains integers
		 * post: The part of the array from low through high is sorted
		 * @param Integer array A to be sorted
		 * @param low the index of the low bound
		 * @param high the index of the high bound
		 */
		private static void quicksort(int [] A, int low, int high)
		{
			if (low < high)	//There is data to be sorted.
			{
				//Partition the array
				int q = DPartition(A, low, high);
				//Sort the left half
				quicksort(A, low, q-1);
				//Sort the right half
				quicksort(A, q+1, high);
			}
		}
		
		/**Partition the array so that the values from low 
		 * to pivot are less than or equal to the pivot value
		 * while the values from the pivot to high are greater 
		 * than the pivot
		 * @param A the array to be partitioned
		 * @param low the index of the low bound
		 * @param high the index of the high bound
		 * @return the location of the pivotvalue
		 */
		private static int DPartition(int [] A, int low, int high)
		{
			//Use this as the pivot value
			int p = A[high];
			int i = low;
			int j = high - 1;
			
			while (i <= j)
			{
				
				while (i <= j && A[i] <= p)
				{
					i = i + 1;
				}
				while (j >= i && A[j] >= p)
				{
					j = j - 1;
				}
				if (i < j)
				{
					//Exchange A[i] with A[j]
					swap(A, i, j);
				}
			}
			//Exchange A[i] with A[high], placing pivot where it belongs
			swap(A, i, high);
			//Return index of pivot value
			return i;
			
		}
		
		/**Sorts the array using quick sort algorithm.
		 * pre: Integer array A contains integers
		 * post: Integer array A is sorted
		 * @param Integer array A to be sorted
		 */
		public static void quicksortImproved(int [] A)
		{
			//Sort the entire array
			quicksortImproved(A, 0, A.length-1);
		}
		
		/**Sorts a part of the array using quick sort algorithm.
		 * pre: Integer array A contains integers
		 * post: The part of the array from low through high is sorted
		 * @param Integer array A to be sorted
		 * @param low the index of the low bound
		 * @param high the index of the high bound
		 */
		private static void quicksortImproved(int [] A, int low, int high)
		{
			//There is data to be sorted
			if (low < high)
			{
				//Use 22 as cutoff value to use insertion sort
				if (low + 22 > high)
				{
					insertion(A, low, high);
				}
				else
				{
					//Find median of three values, and use as pivot
					double median = medianOf3(A, low, high);
					int q = PivotPartition(A, low, high, median);
					
					//Sort left subarray
					quicksortImproved(A, low, q-1);
					//Sort right subarray
					quicksortImproved(A, q+1, high);
				}
			}
		}
		
		/**Sorts the array using insertion sort algorithm.
		 * pre: Integer array A contains integers
		 * post: Integer array A is sorted
		 * @param Integer array A to be sorted
		 * @param low is the lower bound of array to sort
		 * @param high is the uppe rbound of array to sort
		 */
		private static void insertion(int [] A, int low, int high)
		{
			for (int i = low+1; i <= high; i++)
			{
				int temp = A[i];
				int j;
				for (j = i; j > low && temp < A[j-1]; j--)
				{
					A[j] = A[j-1];
				}
				A[j] = temp;
			}
		}
		
		 /**Partition the array so that the values from low 
			 * to pivot are less than or equal to the pivot value
			 * while the values from the pivot to high are greater 
			 * than the pivot
			 * @param A the array to be partitioned
			 * @param low the index of the low bound
			 * @param high the index of the high bound
			 * @param pivot is the pivot value
			 * @return the location of the pivotvalue
			 */
		private static int PivotPartition(int [] A, int low, int high, double pivot)
		{
			double p = pivot;
			int i = low + 1;
			int j = high - 2;
			
			while (i <= j)
			{
				while (i <= j && A[i] <= p)
				{
					i = i + 1;
					
				}
				while (j >= i && A[j] >= p)
				{
					j = j - 1;
				}
				if (i < j)
				{
					swap(A, i, j);
				}
			}
			
			swap(A, i, high-1);
			
			return i;
			
		}
		
		//Determine median of low, high, and centre
		private static double medianOf3(int [] A, int low, int high)
		{
			int center = (low + high) / 2;
			
			//Sort the low, middle, and high values
			if (A[low] > A[center])
			{
				swap(A, low, center);
			}
			if (A[low] > A[high])
			{
				swap(A, low, high);
			}
			if (A[center] > A[high])
			{
				swap(A, center, high);
			}
			
			//Place the pivot at high-1
			swap(A, center, high-1);
			//Return value of pivot
			return A[high-1];
		}
		
		//Java's sorting algorithm used for testing
		public static void javasort(int [] A)
		{
			Arrays.sort(A);
		}
		
		//For Bonus question. It is close to working, but does not work.
		public static void bestcasequicksort(int [] A)
		{
				bestcasequicksort(A, 0, A.length-1);
		}
		
		private static void bestcasequicksort(int [] A, int p, int r)
		{
			if (p < r)
			{
				int i = (r - p + 1) / 2;
				int x = selection(A, p, r, i);
				int q = partition(A, p, r, x);
				bestcasequicksort(A, p, q-1);
				bestcasequicksort(A, q+1, r);
				
			}
		}
		
		private static int partition(int [] A, int low, int high, int pivot)
		{
			//Use this as the pivot value
			int p = pivot;
			int i = low;
			int j = high - 1;
			
			while (i <= j)
			{
				
				while (i <= j && A[i] <= p)
				{
					i = i + 1;
				}
				while (j >= i && A[j] >= p)
				{
					j = j - 1;
				}
				if (i < j)
				{
					//Exchange A[i] with A[j]
					swap(A, i, j);
				}
			}
			//Exchange A[i] with A[high], placing pivot where it belongs
			swap(A, i, high);
			//Return index of pivot value
			return i;
			
		}
		
		
		private static int selection(int a[], int s, int e, int k)

{

   // if the partition length is less than or equal to 5

   // we can sort and find the kth element of it

   // this way we can find the median of n/5 partitions

   if(e-s+1 <= 5)

   {

       insertion(a,s, e);

       return s+k-1;

   }

   /* if array is bigger we partition the array in sub-arrays of size 5
   no. of partitions = n/5 = (e+1)/5 iterate through each partition and
   recursively 
   calculate the median of all of them and keep putting the medians in the 
   starting of the array 
   */

   for(int i=0; i<(e+1)/5; i++)

   { 

      int left = 5*i;

      int right = left + 4;

      if(right > e) right = e;

      //left+(right-left)/2 median will be 3rd element e.g.ar[2] in zero index based array

      int median = selection(a,left, right, 2); 

      swap(a, median, i);

   }

  /*now we have array  a[0] = median of 1st 5 sized partition a[1] = median of 
  2nd 5 sized partition and so on till n/5 to find out the median of these n/5 
  medians we need to select the n/10th element of this set (i.e. middle of it)
 */

   return selection(a, 0, (e+1)/5, ((e+1)/10));

}
		
}
