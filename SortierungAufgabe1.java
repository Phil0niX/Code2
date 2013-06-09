import java.util.Random;

class SortierungAufgabe1
{
	
	// Aufgabe 2.1
	// Methode insertionSort()
	public static void insertionSort(int[] array)
	{
		// Variablen
		int key = 0;
		int i = 0;

		for(int j=1;j<array.length;j++)
		{
			key = array[j];
			i = j-1;

			while(i>=0 && array[i]>key)
			{
				array[i+1] = array[i];
				i--;
			}

			array[i+1] = key;
		}
	}

	// Methode isSorted
	public static boolean isSorted(int[] array)
	{
		for(int i=0;i<array.length-1;i++)
		{
			if(array[i]>array[i+1])  return false;
		}
		return true;
	}

	// Ende Aufgabe 2.1
	public static void main(String args[])
	{
		
		int n = 0;
		int array[];
		Random generator = new Random();
		long tStart = 0;
		long tEnd = 0; 
		long msecs = 0;
		boolean istSortiert = false;
		
		// erstes Argument auswerten
		try{
		n = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte Eingabe ueberprüfen!");
		}
		
		// Array befüllen
		array = new int[n];

		for(int i=0;i<n;i++)
		{
			array[i] = generator.nextInt();
		}

		// Abarbeitung der 2. Argumente
		if(args.length >= 2)
		{
			// Argument ist "rand"
			if(args[1].equals("rand")) 
			{
				istSortiert = isSorted(array);
			}

			// Argument ist "auf"
			else if(args[1].equals("auf"))
			{
				// Array sortieren
				tStart = System.currentTimeMillis();
				insertionSort(array);	
				tEnd = System.currentTimeMillis();
				istSortiert = true;
			}

			// Argument ist "ab"
			else if(args[1].equals("ab"))
			{
				// Array sortieren
				tStart = System.currentTimeMillis();
				insertionSort(array);
				tEnd = System.currentTimeMillis();
				// Array invertieren
				for(int i=0;i<array.length/2+1;i++)
				{
					int temp = array[i];
					array[i] = array[array.length-i-1];
					array[array.length-i-1] = temp;
				}
				istSortiert = true;

			}

			// Argument ist unbekannt
			else
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
				return;
			}
		}
		// kein Argument, also rand
		else
		{
			istSortiert = isSorted(array);
		}	


		// Array ausgeben
		if(array.length<=100)
		{
			for(int i=0;i<array.length;i++)
			{
				System.out.print(array[i] + " ");
			}
		}
		System.out.println("");
		if(istSortiert) System.out.println("Feld sortiert!");
		else System.out.println("Feld NICHT sortiert!");
		msecs = tEnd - tStart;
		System.out.println("Dauer der Sortierung: " + msecs +" Millisekunden.\n");

		/*
		int bla[] = {41,-2,340,42};
		insertionSort(bla);
		System.out.println(isSorted(bla));
		for(int i = 0;i<bla.length;i++)
		{
			System.out.print(bla[i] + " ");
		}
		*/
	}//Ende main()


	/*
	// Aufgabe 2.2
	public static void mergeSort(int[] array)
	{
		mergeFkt(array,0,array.length);
		
	}

	public static void mergeSort(int[] array,int unten, int oben)
	{
		if( p < r )
		{
			int mitte = (unten+oben)/2;
			mergeSort(array,unten,mitte);
			mergeSort(array,mitte+1,oben);
			merge(array,unten,mitte,oben);
		}
		
	}
	
	public static void merge(int[] array,unten,mitte,oben)
	{
		int[] neu = new int[array.length];
		int neuUnten = unten;
		int neuOben = oben;
		while(unten <= mitte && mitte+1 <= oben)
		{
			if(array[unten] <= array[mitte+1])
			{
				hintenEinfuegen(neu,array[unten]);
				unten++;
			}
			else
			{
				vorneEinfuegen(neu,array[mitte+1]);
				mitte++;
			}
		}
		while(unten <= mitte)
		{
			hintenEinfuegen(neu,array[unten];
			unten++;
		}
		while(mitte <= oben)
		{
			hintenEinfuegen(neu,array[mitte+1];
		}
		schreibe(neu,array);
			
	}
	public static void vorneEinfuegen(int [] array,int x)
	{
		int temp = array[0];
		array[0] = x;
		for(int i=1;i<array.length-1;i++)
		{
			array[i] = temp;
			temp = array[i+1];
		}
		array[array.length-1] = temp;
	}

	public static void hintenEinfuegen(int [] array, int x)
	{
		int i = 0;
		while(array[i] != null)
		{
			i++;
		}
		array[i] = x;
	}

	public static void schreiben(int[] neu,int[] array)
	{
		for(int i = 0;i<array.length;i++)
		{
			array[i] = neu[i];
		}
	}
	*/

	// Ende Aufgabe 2.2
}//Ende Klasse
