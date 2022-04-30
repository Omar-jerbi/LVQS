
import java.util.Random;

public class Main2
{

  public static int NCOMP =0;	
  public static int NUMELEMENTS = 10000;
  public static int[] sequence = new int[NUMELEMENTS];

	
	public static final int R = 100;
	public static int[] RESRUN = new int[R]; 
	
	
  public static void LVQuickSort(int[] seq) {
	  QuickSort(0, seq.length-1);
  }
  
  
  public static void QuickSort(int left, int right) 
  {
      if (right - left <= 0)
          return;
      else 
      {
          Random rand = new Random();
          int pivotIndex = left + rand.nextInt(right - left + 1);
          swap(pivotIndex, right);

          int pivot = sequence[right];
          int partition = partitionIt(left, right, pivot);
          QuickSort(left, partition - 1);
          QuickSort(partition + 1, right);
      }
  }

  public static int partitionIt(int left, int right, long pivot) 
  {
      int leftPtr = left - 1;
      int rightPtr = right;
      while (true) 
      {
    	  
          while (sequence[++leftPtr] < pivot)
              ;
          while (rightPtr > 0 && sequence[--rightPtr] > pivot)
          {
        	  NCOMP++;
          }

          if (leftPtr >= rightPtr)
              break;
          else
              swap(leftPtr, rightPtr);
      }
      swap(leftPtr, right);
      return leftPtr;
  }

  public static void swap(int dex1, int dex2) 
  {
      int temp = sequence[dex1];
      sequence[dex1] = sequence[dex2];
      sequence[dex2] = temp;
  }


	public static void mixer(int[] s) {
		var randomizer = new Random();
		for(int i = 0; i<s.length ; i++) {
			var pos = randomizer.nextInt(s.length);
			var aux = s[i];
			s[i] = s[pos];
			s[pos] = aux;
		}
	}
  
  
  public static void main(String args[]) 
  {
      for (int i = 0; i < NUMELEMENTS; i++)
          sequence[i] = i;

      mixer(sequence);
      

      LVQuickSort(sequence);
      
      for(var i : sequence)System.out.print(i + " ");
      
      System.out.println();
      System.out.println(NCOMP);
  }
}
