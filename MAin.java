package apa;

import java.util.Random;

public class MAin {
	
	public static int NCOMP = 0;
	
	  public static void LVQuickSortRandPivot(int[] seq) {
		  QS(seq,0, seq.length-1);
	  }
	  
	  
	  private static void QS(int[] seq, int left, int right) 
	  {
//	      if (left >= right)
//	          return;
//	      else 
//	      {
////	          Random rand = new Random();
////	          int pivotPos = left + rand.nextInt(right - left + 1); //INDICE RANDOM IN [LEFT;RIGHT]
////	          
////	          swap(seq, pivotPos, left);//pivot == primo elemento
////	          int pivot = seq[left];//pivot == primo elemento
//
////	    	  int pivot = seq[right];
////	    	  mixer(seq);
//	    	  
//	          int partition = partition(seq, left, right);
//	          QS(seq, left, partition - 1);
//	          QS(seq, partition + 1, right);
//	      }
	      if (left < right)
	        {
	            /* pi is partitioning index, arr[pi] is 
	              now at right place */
	            int pi = partition(seq, left, right, seq[right]);
	  
	            // Recursively sort elements before
	            // partition and after partition
	            QS(seq, left, pi-1);
	            QS(seq, pi+1, right);
	        }
	  }

//	  
//	    public static int partition(int arr[], int low, int high)
//	    {
//	    	mixer(arr, low , high);
//	    	int  pivot = arr[high];
//	
//	    	
//	        int i = (low-1); // index of smaller element
//	        for (int j=low; j<high; j++)
//	        {
//	            // If current element is smaller than or
//	            // equal to pivot
//	            if (arr[j] <= pivot)
//	            {
//	                i++;
//	                NCOMP++;
//	                // swap arr[i] and arr[j]
//	                int temp = arr[i];
//	                arr[i] = arr[j];
//	                arr[j] = temp;
//	            }
//	        }
//	  
//	        // swap arr[i+1] and arr[high] (or pivot)
//	        int temp = arr[i+1];
//	        arr[i+1] = arr[high];
//	        arr[high] = temp;
//	  
//	        return i+1;
//	    }
//	  
	  
	  
	  public static int partition(int[] sequence, int left, int right, long pivot) 
	  {
	      int leftPtr = left-1;
	      int rightPtr = right;
	      
	      while (true) 
	      {
	          while (sequence[++leftPtr] < pivot) ;
	          
	          while (rightPtr > 0 && sequence[--rightPtr] > pivot)
	          {
	        	 NCOMP++;
	          }

	          if (leftPtr >= rightPtr)
	              break;
	          else
	              swap(sequence, leftPtr, rightPtr);
	      }
	      
	      swap(sequence, leftPtr, right);
	      
	      return leftPtr;
	  }
	  
	  
	  
	  

	  public static void swap(int[] sequence, int dex1, int dex2) 
	  {
	      int temp = sequence[dex1];
	      sequence[dex1] = sequence[dex2];
	      sequence[dex2] = temp;
	  }
	  
	  
		

		public static void mixer(int[] s, int from, int to) {
			var randomizer = new Random();
			for(int i = from; i<=to ; i++) {
				var pos = from + randomizer.nextInt(to-from) ;
				var aux = s[i];
				s[i] = s[pos];
				s[pos] = aux;
			}
		}
	  
	  
	  
	  public static void main(String[] args) {
	  
		  var rand = new Random();		  
	  
		  int N = 10000;
		  
		  for(int j = 0; j<100; j++) {
			  
		  
		  int[] array = new int[N];
		  for(int i = 0; i<N; i++) {
			  array[i] = rand.nextInt(N);
		  }
		  
		  
	  
		  
		  LVQuickSortRandPivot(array);
//		  for(var x : array ) {
//			  System.out.print(x + " ");
//		  }
		  
		  System.out.print(" "+NCOMP);
		
		  NCOMP = 0;
		  }
		  
		    
	  }
	
}
