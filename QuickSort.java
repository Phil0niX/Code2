

class QuickSort
{
	public static void main(String args[])
	{
		// Varibalen
		int n = 0;
		//----------------------------------------------------------------------------------
		// Eingabe ueberpruefen und auswerten
		if(args.length == 0 || args.length > 1)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		try
		{
			n = Integer.parseInt(args[0]);
			if(n<1)
			{
				System.out.println("...");
				return;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}

		//----------------------------------------------------------------------------------
		
		// Array der Laenge n mit Zufallszahlen erstellen
		java.util.Random generator = new java.util.Random();
		int[] array = new int[n];
		for(int i=0;i<array.length;i++)
		{
			array[i] = generator.nextInt();
		}

		//----------------------------------------------------------------------------------

		// Array mit QuickSort sortieren und Laufzeit messen
		long tStart = System.currentTimeMillis();
		quickSort(array);
		long tEnd = System.currentTimeMillis();
		long msecs = tEnd - tStart;

		//----------------------------------------------------------------------------------

		// Ausgabe
		if(isSorted(array)) System.out.println("Feld der Laenge "+array.length+" wurde in "+msecs+" ms sortiert!");
		else System.out.println("Feld ist nicht sortiert!");

	}//Ende main()

	public static void quickSort(int[] array)
	{
		quickSort(array,0,array.length-1);
	}

	// Methode quickSort()
	private static void quickSort(int[] A,int l, int r)
	{
		// Abbruchbedingung: linke Grenze l >= rechte Grenze r
		if(l < r)
		{
			// Arraygrenzen
			int i = l;
			int j = r;
			// Pivotelement, um Array in linkes und rechtes Teilarray aufzuteilen
			int pivot = A[(l+r)/2];

			while( i <= j )
			{
				// Invariante: A[i-1] < pivot < A[j+1]
				// 2. Invariante: A[0..i-1] < pivot < A[j-1..A.length]		
				assert kleiner(A,l,i-1,pivot);
				assert groesser(A,j+1,r,pivot);
				// Sucht ein Element, welches kleiner als das Pivotelement ist
				while( A[i] < pivot )
				{
					i++;
				}
				// Sucht ein Element, welches groesser als das Pivotelement ist
				while( A[j] > pivot )
				{
					j--;
				}
				// wenn Index des kleineren Elements kleiner gleich Index des groesseren Elements, dann tausche
				if( i <= j )
				{
					// A[i] mit A[j] tauschen
					int temp = A[i];
					A[i] = A[j];
					A[j] = temp;
					// i inkrementieren
					i++;
					// j dekrementieren
					j--;
				}
			}
			// quickSort fuer linkes und rechtes Teilarray aufrufen
			quickSort(A,l,j);
			quickSort(A,i,r);
		}
	}//Ende quickSort()

	// Methode isSorted()
	public static boolean isSorted(int[] array)
	{
		for(int i=0;i<array.length-1;i++)
		{
			if(array[i] > array[i+1]) return false;
		}
		return true;
	}//Ende isSorted()

	// Array von Index n bis m auf Sortierung ueberpruefen
	public static boolean isSorted(int[] array,int n,int m)
	{
		if(n>=0 && m<array.length)
		{
			for(int i=n;i<m-1;i++)
			{
				if(array[i] > array[i+1]) return false;
			}
		}
		return true;
	}//Ende isSorted()

	//Hilfsmethode gibAus()
	public static void gibAus(int[] array)
	{
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i]+" ");
		}
		System.out.println("");
	}

	public static boolean kleiner(int[] array,int l, int j, int pivot)
	{
		for(int i=l;i<j;i++)
		{
			if(array[i] > pivot) return false;
		}
		return true;
	}

	public static boolean groesser(int[] array, int j, int r, int pivot)
	{
		for(int i=j;i<r;i++)
		{
			if(array[i] < pivot) return false;
		}
		return true;
	}

}//Ende Klasse
