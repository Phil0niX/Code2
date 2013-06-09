

class Vergleich
{
	public static void main(String args[])
	{
		// Varibalen
		int n = 0;
		long tStart = 0;
		long tEnd = 0;
		long msecsQuick = 0;
		long msecsMerge = 0;
		long msecsBubble = 0;

		// Eingabe ueberpruefen und auswerten
		if(args.length == 0 || args.length > 1)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		try
		{
			n = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		
		// Array der Laenge n mit Zufallszahlen erstellen
		java.util.Random generator = new java.util.Random();
		int[] array = new int[n];
		for(int i=0;i<array.length;i++)
		{
			array[i] = generator.nextInt();
		}

		int[] arrayQuick = kopiere(array);
		int[] arrayMerge = kopiere(array);
		int[] arrayBubble = kopiere(array);
		
		// Ausgabe
		System.out.println("Laenge des Feldes: "+ array.length);

		// Array mit QuickSort sortieren und Laufzeit messen
		// QuickSort
		tStart = System.currentTimeMillis();
		QuickSort.quickSort(arrayQuick);
		tEnd = System.currentTimeMillis();
		msecsQuick = tEnd - tStart;
		// Ausgabe
		if(isSorted(arrayQuick)) System.out.println("QuickSort: "+msecsQuick+" ms");
		else System.out.println("QuickSort: Feld ist nicht sortiert!");

		// MergeSort
		tStart = System.currentTimeMillis();
		MergeSort.mergeSort(arrayMerge);
		tEnd = System.currentTimeMillis();
		msecsMerge = tEnd - tStart;
		// Ausgabe
		if(isSorted(arrayMerge)) System.out.println("MergeSort: "+msecsMerge+" ms");
		else System.out.println("MergeSort: Feld ist nicht sortiert!");

	/* zu langsam...
		// BubbleSort
		tStart = System.currentTimeMillis();
		BubbleSort.bubbleSort(arrayBubble);
		tEnd = System.currentTimeMillis();
		msecsBubble = tEnd - tStart;
		// Ausgabe
		if(isSorted(arrayBubble)) System.out.println("BubbleSort: "+msecsBubble+" ms");
		else System.out.println("BubbleSort: Feld ist nicht sortiert!");
	*/

	}//Ende main()

	public static int[] kopiere(int[] array)
	{
		int[] neu = new int[array.length];
		for(int i = 0;i<neu.length;i++)
		{
			neu[i] = array[i];
		}
		return neu;
	}

	public static void quickSort(int[] array)
	{
		quickSort(array,0,array.length-1);
	}

	private static void quickSort(int[] A,int l, int r)
	{
		if(l < r)
		{
			int i = l;
			int j = r;
			int pivot = A[(l+r)/2];
			while( i <= j )
			{
				while( A[i] < pivot )
				{
					i++;
				}
				while( A[j] > pivot )
				{
					j--;
				}
				if( i <= j )
				{
					int temp = A[i];
					A[i] = A[j];
					A[j] = temp;
					i++;
					j--;
				}
			}
			quickSort(A,l,j);
			quickSort(A,i,r);
		}
	}//Ende quickSort()

	public static boolean isSorted(int[] array)
	{
		for(int i=0;i<array.length-1;i++)
		{
			if(array[i] > array[i+1]) return false;
		}
		return true;
	}

}//Ende Klasse
