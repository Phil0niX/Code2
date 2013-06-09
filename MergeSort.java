


class MergeSort
{
	public static void main(String args[])
	{
		// Varibalen
		int n = 0;

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

		// Array mit mergeSort sortieren und Laufzeit messen
		long tStart = System.currentTimeMillis();
		mergeSort(array);
		long tEnd = System.currentTimeMillis();
		long msecs = tEnd - tStart;

		// Ausgabe
		if(isSorted(array)) System.out.println("Feld der Laenge "+array.length+" wurde in "+msecs+" ms sortiert!");
		else System.out.println("Feld ist nicht sortiert!");

	}

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
	
	// mergeSort() ---------------------------------------------------------------------------
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
	//----------------------------------------------------------------------------------------
	
	public static boolean isSorted(int[] array)
	{
		for(int i=0;i<array.length-1;i++)
		{
			if(array[i] > array[i+1]) return false;
		}
		return true;
	}

}//Ende Klasse

