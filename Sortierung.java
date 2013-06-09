import java.util.Random;

class Sortierung
{
	//--------------------------------------------------------------------------------------

	// Aufgabe 2.1
	// Methode insertionSort()
	public static void insertionSort(int[] array)
	{
		// Variablen
		int key = 0;
		int i = 0;

		for(int j=1;j<array.length;j++)
		{
			// Invariante: A[0..j] ist sortiert
			assert isSorted(array,0,j) : "Array ist nicht sortiert!";
			key = array[j];
			i = j-1;
			
			while(i>=0 && array[i]>key)
			{
				//Invariante: A[0..j] \ A[i+1] ist sortiert
				array[i+1] = array[i];
				i--;
			}

			array[i+1] = key;
		}
	}

	// Methode isSorted
	public static boolean isSorted(int[] array)
	{
		//Invariante: A[i] < A[i+1] fuer A[1..j], 1<=i<j
		for(int i=0;i<array.length-1;i++)
		{
			if(array[i]>array[i+1])  return false;
		}
		return true;
	}

	// Ende Aufgabe 2.1

	//---------------------------------------------------------------------------------------

	//main()
	public static void main(String args[])
	{
		// Anzahl der Zahlen im Array
		int n = 0;
		// Array das mit Zufallszahlen befuehlt wird und Generator dafuer
		int array[];
		Random generator = new Random();
		// Variablen fuer die Messung der Zeit
		long tStart = 0;
		long tEnd = 0; 
		long msecs = 0;
		// gibt an, ob das Array sortiert ist
		boolean istSortiert = false;
		// Variablen fuer die Argumente
		boolean isInsert = false;
		boolean isMerge = false;
		boolean auf = false;
		boolean ab = false;
		boolean rand = false;
		
		// erstes Argument auswerten
		try{
		n = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}

		if(n < 0) 
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		
		//-----------------------------------------------------------------------------------

		// Array befüllen
		array = new int[n];

		for(int i=0;i<n;i++)
		{
			array[i] = generator.nextInt();
		}


		//-----------------------------------------------------------------------------------


		// Abarbeitung des 2. Argument [insert|merge]

		// Direkt abbrechen, wenn zu viele Argumente uebergeben wurden
		if(args.length > 3 || args.length < 1)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}

		// Nur ein Argument -> merge auf
		if(args.length < 2)
		{
			
			isMerge = true;
			auf = true;
		}
		
		// 2. Argument
		if(args.length >= 2)
		{
			if(args[1].equals("insert")) isInsert = true;

			else if(args[1].equals("merge")) isMerge = true;

			// else Teil
		}

		// nur 2 Argumente
		if(args.length == 2)
		{
			if(args[1].equals("insert") || args[1].equals("merge")) auf = true;
			else
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
				return;
			}
		}
		
		// 3. Argumente
		else if(args.length == 3)
		{
			if(args[2].equals("auf")) auf = true;

			else if(args[2].equals("ab")) ab = true;

			else if (args[2].equals("rand")) rand = true;

			else
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen");
				return;
			}
		}
		// Ende Abarbeitung der Argumente


		// ----------------------------------------------------------------------------------


		// Je nach Argument Methoden aufrufen
		// MergeSort
		if( isMerge )
		{
			// mergeSort, auf
			if(auf)
			{
				System.out.println("\nSortierung: MergeSort aufsteigend!");
				tStart = System.currentTimeMillis();
				mergeSort(array);
				tEnd = System.currentTimeMillis();
				istSortiert = true;
			}
			
			// mergeSort, ab
			else if(ab) 
			{
				System.out.println("\nSortierung: MergeSort absteigend!");
				tStart = System.currentTimeMillis();	
				mergeSort(array);
				tEnd = System.currentTimeMillis();
				dreheUm(array);
				istSortiert = true;
			}
			
			// random
			else
			{
				System.out.println("\nSortierung: Keine (merge)!");
				istSortiert = isSorted(array);
			}
		}

		// InsertionSort
		else if( isInsert )
		{
			// insertionSort, auf
			if(auf)
			{
				System.out.println("\nSortierung: InsertionSort aufsteigend!");
				tStart = System.currentTimeMillis();
				insertionSort(array);
				tEnd = System.currentTimeMillis();
				istSortiert = true;
			}

			// insertionSort, ab
			else if(ab)
			{
				System.out.println("\nSortierung: InsertionSort absteigend!");
				tStart = System.currentTimeMillis();
				insertionSort(array);
				tEnd = System.currentTimeMillis();
				dreheUm(array);
				istSortiert = true;
			}

			//random
			else
			{
				System.out.println("\nSortierung: Keine (insert)!");
				istSortiert = isSorted(array);
			}
		}

		//random
		else
		{
			System.out.println("\nSortierung: Keine (rand)!");
			istSortiert = isSorted(array);
		}
		//Ende Methoden aufrufen


		//-----------------------------------------------------------------------------


		// Array ausgeben
		if(array.length <= 100) ausgeben(array);
		if(istSortiert) System.out.println("Feld sortiert!");
		else System.out.println("Feld NICHT sortiert!");
		msecs = tEnd - tStart;
		System.out.println("Dauer der Sortierung: " + msecs +" Millisekunden.\n");
	
	}//Ende main()


	//--------------------------------------------------------------------------------------

	
	// Aufgabe 2.2
	public static void mergeSort(int[] array)
	{
		// mergeSort() mit den Anfangsgrenzen 0 und Laenge des Arrays aufrufen
		mergeSort(array,0,array.length-1);
		
	}

	public static void mergeSort(int[] array,int unten, int oben)
	{
		// Grenzen vergleichen, um zu ueberpruefen, dass das Array noch mindestens ein Element enthaelt, sonst nichts ausfuehren
		if( unten < oben )
		{
			// Mitte des Arrays ermitteln
			int mitte = (unten+oben)/2;

			// rekursiv Aufrufen fuer unteren und oberen Teilarray
			mergeSort(array,unten,mitte);
			mergeSort(array,mitte+1,oben);

			// sortierte Teilarrays zu einem sortieren Array zusammenfuegen
			merge(array,unten,mitte,oben);
		}
		
	}
	
	public static void merge(int[] array,int unten,int mitte,int oben)
	{
		// neues Array erzeugen, in dem die Werte sortiert eingefuegt werden
		int[] neu = new int[oben-unten+1];
		// int um zu wissen bis wohin das neue Array befüllt ist

		// Anfangswerte zwischenspeichern, um später Werte vom neuen Array ins alte zu kopieren
		int neuUnten = unten;
		int neuOben = oben;

		// Grenzen lesbarer machen
		int mitteUnten = mitte;
		int mitteOben = mitte+1;

		// durch das neue Array laufen
		for(int i=0;i<neu.length;i++)
		{
			//Invariante: neu[1..i] ist sortiert
			assert isSorted(neu,0,i) : "Array nicht sortiert!";
			// beide Arrays sind noch mit Werten gefuellt
			if(unten <= mitteUnten && mitteOben <= oben)
			{
				// jeweils die ersten Werte der Arrays vergleichen und das kleinere ins neue Array einfuegen
				if(array[unten] < array[mitteOben])
				{
					neu[i] = array[unten];
					unten++;
				}
				else
				{
					neu[i] = array[mitteOben];
					mitteOben++;
				}
			}


			// wenn oberes Teilarray leer, restliche Werte aus unterem Array in neues schreiben
			else if(unten <= mitteUnten)
			{
				neu[i] = array[unten];
				unten++;
			}

			// wenn unteres Teilarray leer, restliche Werte aus oberen Array in neues schreiben
			else
			{
				neu[i] = array[mitteOben];
				mitteOben++;
			}
		}

		// Werte des neuen Arrays in das alte Array schreiben
		for(int i=0;i+neuUnten <= neuOben;i++)
		{
			array[i+neuUnten] = neu[i];
		}

	}//Ende merge()
	

	//---------------------------------------------------------------------------


	// Hilfsmethoden

	// Array ausgeben
	public static void ausgeben(int[] array)
	{
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println("");
	}

	// Array umdrehen
	public static void dreheUm(int[] array)
	{
		for(int i=0;i<array.length/2+1;i++)
		{
			int temp = array[i];
			array[i] = array[array.length-i-1];
			array[array.length-i-1] = temp;
		}
	}

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
	}
			

}//Ende Klasse
