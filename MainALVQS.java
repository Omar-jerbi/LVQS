import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

public class MainALVQS {

	
	public static int NCOMP = 0; //numero confronti per run
	
	public static final int NUMELEMENTS = 10000; //dimensione array elementi da ordinare
	
	public static final int R = 100000; //numero di run da effettuare
	public static int[] RESRUN = new int[R]; 
	
//	public static final int BIN = 500;
	public static final int BIN = 50;
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void mixer(int[] s) {
		mixer(s, 0, s.length);
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
	

	//LVqs con chiamata funzione di concat 
	public static int[] LVQuickSort(int[] s) {
		if(s.length <= 1) {
			return s;
		}

		int piv = s[0];
		mixer(s);
		

		var inf = inf(s, piv);
		var sup = sup(s, piv);
		
		return concat(LVQuickSort(inf), piv, LVQuickSort(sup));
		
	}
	

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//LVqs con chiamata funzione di partizionamento su indici
	public static void LVQuickSort2(int[] seq) {
		  QS(seq,0, seq.length-1);
	  }
	  
	  
	  public static void QS(int[] seq, int left, int right) {
	      if (left < right){
				mixer(seq, left , right);//mischio la sequenda tra left e right
				int  pivot = seq[right];// pivot = ultimo elemento
				
	            int pi = partition(seq, left, right, pivot);//partizionamento QS "classico"

	            QS(seq, left, pi-1);
	            QS(seq, pi+1, right);
	        }
	  }
	  

	  public static int partition(int seq[], int left, int right, int pivot){	
			int i = left-1; // indice elemento piu piccolo
	        for (int j=left; j<right; j++){
	            if (seq[j] <= pivot)
	            {
	                i++;
	                NCOMP++;
	                swap(seq, i ,j);
	            }
	        }
	  
	        swap(seq, i+1 ,right);
	        return i+1;
		}
		

		
	  public static void swap(int[] sequence, int dex1, int dex2) {
	      int temp = sequence[dex1];
	      sequence[dex1] = sequence[dex2];
	      sequence[dex2] = temp;
	  }
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	public static void main(String[] args) {		
		

		
		for(int r = 0; r<R; r++) {
			int[] array = new int[NUMELEMENTS];
			var rand = new Random();
			for(int i = 0; i < NUMELEMENTS; i++) array[i] = rand.nextInt(NUMELEMENTS);
			
			
//			LVQuickSort(array); 			//valore medio dei confronti  (circa)= 10nLog(n) ; (circa)= 400k
			LVQuickSort2(array);				//valore medio dei confronti (circa)= 2nLog(n) ; (circa)=80k

			
			RESRUN[r] = NCOMP;
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

		
		//////////////////////////////////////////////////////////////////////////////////////////////////////costruzione istogramma
		 Histogram histogram = new Histogram(BIN);
		 int max = 0;
		 for(var Xr:RESRUN) {
			 if(Xr > max) max =Xr;
		 }
		 
		 
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
