import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class MainALVQS {

	
	public static int NCOMP = 0;
	
	public static final int NUMELEMENTS = 10000;
	
	public static final int R = 100000;
	public static int[] RESRUN = new int[R]; 
	
	public static final int BIN = 500;
//	public static final int BIN = 50;
	
	
	public static void mixer(int[] s) {
		var randomizer = new Random();
		for(int i = 0; i<s.length ; i++) {
			var pos = randomizer.nextInt(s.length);
			var aux = s[i];
			s[i] = s[pos];
			s[pos] = aux;
		}
	}

	
	public static int[] convert(Object[] array) {
		int[] res = new int[array.length];
		
		int i = 0;
		for(var x : array) {
			res[i] = (int)x;
			i++;
		}
		
		return res;
	}
	
	
	public static int[] sup(int[] s, int piv) {
		var res = new ArrayList<Integer>();
		
		for(var x : s) {
			NCOMP++;
			if(x > piv) {
				res.add(x);
			}
		}
		
		return convert(res.toArray());
		
	}
	
	
	public static int[] inf(int[] s, int piv) {
		var res = new ArrayList<Integer>();
		
		for(var x : s) {
			NCOMP++;
			if(x < piv) {
				res.add(x);
			}
		}
		
		return convert(res.toArray());
	}
	
	
	public static int[] concat(int[] inf, int p, int[] sup) {
		var res = new ArrayList<Integer>();
		for(var i : inf) {
			res.add(i);
		}
		res.add(p);
		for(var s : sup) {
			res.add(s);
		}
		
		return convert(res.toArray());
	}
	

	public static int[] LVQuickSortMix(int[] s) {
		if(s.length <= 1) {
			return s;
		}

		int piv = s[0];
		mixer(s);
		

		var inf = inf(s, piv);
		var sup = sup(s, piv);
		
		return concat(LVQuickSortMix(inf), piv, LVQuickSortMix(sup));
		
	}
	

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	  public static void LVQuickSortRandPivot(int[] seq) {
		  QS(seq,0, seq.length-1);
	  }
	  
	  
	  public static void QS(int[] sequence, int left, int right) 
	  {
	      if (right - left <= 0)
	          return;
	      else 
	      {
	          Random rand = new Random();
	          int pivotIndex = left + rand.nextInt(right - left + 1);
	          swap(sequence, pivotIndex, right);

	          int pivot = sequence[right];
	          int partition = partitionIt(sequence, left, right, pivot);
	          QS(sequence, left, partition - 1);
	          QS(sequence, partition + 1, right);
	      }
	  }

	  public static int partitionIt(int[] sequence, int left, int right, long pivot) 
	  {
	      int leftPtr = left - 1;
	      int rightPtr = right;
	      while (true) 
	      {
	    	  
	          while (sequence[++leftPtr] < pivot)
	              ;
	          while (rightPtr > 0 && sequence[--rightPtr] > pivot)
	          {
	        	  MainALVQS.NCOMP++;
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
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	public static void main(String[] args) {		
		

		
		for(int r = 0; r<R; r++) {
			int[] array = new int[NUMELEMENTS];
			
			for(int i = 0; i < NUMELEMENTS; i++) array[i] = i;
			
			mixer(array);
			
//			LVQS(array);
			LVQuickSortRandPivot(array);
			

			
			RESRUN[r] = NCOMP;//10;/////////////////////////////////////////////////////////////////////
			NCOMP = 0;
		}		
		
		
		for(var t: RESRUN) System.out.print(t + " ");
		System.out.println();
		
		
		//VALORE MEDIO
		long VMEDIO = 0;
		for(var Xr : RESRUN) {
			VMEDIO +=Xr;
		}
		VMEDIO = VMEDIO / R;
		
		//VARIANZA
		long VAR = 0;
		for(var Xr : RESRUN) {
			VAR = VAR + ((Xr-VMEDIO) * (Xr-VMEDIO));
		}
		VAR = VAR / (R-1);
		
		
		System.out.println(VMEDIO);
		System.out.println(VAR);

		
		//costruzione istogramma
		 Histogram histogram = new Histogram(BIN);
		 int max = 0;
		 for(var Xr:RESRUN) {
			 if(Xr > max) max =Xr;
		 }
		 
		 
//		 int passo = (2*max)/BIN;
		 int passo = max/BIN;
		 		 
		 
		 for(var Xr : RESRUN) {
			 for(int i = 1 ; i< BIN; i++) {
				 if(Xr < passo*i) {
					 histogram.addDataPoint(i-1);
					 break;
				 }
			 }
		 }
		 
	        StdDraw.setCanvasSize(1000, 400);

	        StdDraw.textLeft(0, 0, "0");
	        StdDraw.textLeft(1, 0, Integer.toString(passo*BIN));
	        StdDraw.text(0.5, 0, Integer.toString(passo*(BIN/2)));
	        StdDraw.text(0.25 , 0, Integer.toString(passo*(BIN/4)));
	        StdDraw.text(0.75 , 0, Integer.toString(passo*(3*BIN/4)));
	        histogram.draw();
		 
	        Toolkit.getDefaultToolkit().beep();
	        
	}

}
