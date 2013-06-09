import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.ArrayList;

class IntervalScheduling
{
	// Interval-Array
	// private Interval[] array;

	public static void main(String args[])
	{
		if(args.length != 1)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		else
		{
			ArrayList<Interval> list;
			RandomAccessFile file;
			String zeile;
			StringTokenizer st;
			Interval[] array;
			try
			{
				// Array einlesen
				Interval[] unsortiertesArray = einlesen(args[0]);
				System.out.println("Bearbeite die Datei \""+args[0]+"\".\n");
				System.out.println("Es wurden "+unsortiertesArray.length+" Zeilen mit folgendem Inhalt gelesen: ");
				gibAus(unsortiertesArray);
				System.out.println("");
				// Algorithmus ausfuehren
				list = intervalScheduling(unsortiertesArray);
				System.out.println("Berechnetes Intervallscheduling: ");
				gibAus(list);

			}
			catch(Exception e)
			{
				System.out.println("Angegebenen Datei existiert nicht oder ist fehlerhaft!");
				return;
			}
		}//Ende else
	}//Ende main()


	// Algorithmus ---------------------------------------------------------------------------
	public static ArrayList<Interval> intervalScheduling(Interval[] unsortiertesArray)
	{
		// Array erzeugen und sortieren
		Interval[] array = kopiere(unsortiertesArray);
		sortiere(array);
		System.out.println("Sortiert: ");
		gibAus(array);
		System.out.println("");

		// array in ArrayList ueberfuehren
		ArrayList<Interval> list = gibArrayList(array);

		// Intervalle entfernen die nicht zueinander kompatibel sind
		for(int i=0;i<list.size();i++)
		{
			// Invariante: list[0..i-1] sind kompatibel
			for(int j=i+1;j<list.size();j++)
			{
				if(!kompatibel(list.get(i),list.get(j)))
				{
					list.remove(j);
					j--;
				}
			}
		}

		return list;
	}//Ende intervalScheduling()

	public static boolean kompatibel(Interval a, Interval b)
	{
		if(a.getEnd() <= b.getStart() && a.getStart() != b.getStart())
			return true;
		else 
			return false;
	}//Ende kompatibel()

	//----------------------------------------------------------------------------------------


	// Hilfsmethoden -------------------------------------------------------------------------

	// Ausgabe
	// gibAus()
	public static void gibAus(Interval[] array)
	{
		System.out.print("[");
		for(int i=0;i<array.length;i++)
		{
			System.out.print("["+array[i].getStart()+","+array[i].getEnd()+"]");
		}
		System.out.print("]\n");
	}

	public static void gibAus(ArrayList<Interval> list)
	{
		System.out.print("[");
		for(int i=0;i<list.size();i++)
		{
			System.out.print("["+list.get(i).getStart()+","+list.get(i).getEnd()+"]");
		}
		System.out.println("]");
	}
	// gibArrayList()
	public static ArrayList<Interval> gibArrayList(Interval[] array)
	{
		ArrayList<Interval> list = new ArrayList<Interval>();
		for(int i=0;i<array.length;i++)
		{
			list.add(array[i]);
		}
		return list;
	}//Ende gibArrayList()

	//kopiere()
	public static Interval[] kopiere(Interval[] eingabe)
	{
		Interval[] ausgabe = new Interval[eingabe.length];
		for(int i=0;i<eingabe.length;i++)
		{
			ausgabe[i] = eingabe[i];
		}
		return ausgabe;
	}//Ende kopiere()

	// sortieren()
	public static void sortiere(Interval[] array)
	{
		quickSort(array,0,array.length-1);
	}//Ende sortiere()

	// quickSort()
	public static void quickSort(Interval[] A,int l,int r)
	{
		// Abbruchbedingung: linke Grenze l >= rechte Grenze r
		if(l < r)
		{
			// Arraygrenzen
			int i = l;
			int j = r;
			// Pivotelement, um Array in linkes und rechtes Teilarray aufzuteilen
			int pivot = A[(l+r)/2].getEnd();

			while( i <= j )
			{
				// Invariante: A[i-1] < pivot < A[j+1]
				// 2. Invariante: A[0..i-1] < pivot < A[j-1..A.length]		
				//assert kleiner(A,l,i-1,pivot);
				//assert groesser(A,j+1,r,pivot);
				// Sucht ein Element, welches kleiner als das Pivotelement ist
				while( A[i].getEnd() < pivot )
				{
					i++;
				}
				// Sucht ein Element, welches groesser als das Pivotelement ist
				while( A[j].getEnd() > pivot )
				{
					j--;
				}
				// wenn Index des kleineren Elements kleiner gleich Index des groesseren Elements, dann tausche
				if( i <= j )
				{
					// A[i] mit A[j] tauschen
					Interval temp = A[i];
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

	// einlesen()
	public static Interval[] einlesen(String pfad) throws Exception
	{
		// ArrayList fuer die Intervalle
		// wird spaeter in Interval-Array umgewandelt
		ArrayList<Interval> list = new ArrayList<Interval>();

		// Variablen fuers Einlesen der Datei
		RandomAccessFile file = new RandomAccessFile(pfad,"r");
		String zeile;
		StringTokenizer st;

		//while schleife
		while( (zeile = file.readLine()) != null)
		{
			// neuen Tokenizer fuer entsprechende Zeile erstellen
			st = new StringTokenizer(zeile,",");

			// Zahlen aus der Zeile lesen
			int start = Integer.parseInt(st.nextToken());
			int ende = Integer.parseInt(st.nextToken());

			// neues Intervall erzeugen und zur ArrayList hinzufügen
			// kann Exception werfen, siehe Klasse Intervall
			list.add(new Interval(start,ende));

		}//Ende while()
		
		// ArrayList in Array konvertieren und zurueckgeben
		// (neues Array gibt den Typ vom zurueckgegebenen Array an)
		return list.toArray(new Interval[0]);

	}//Ende einlesen()

}//Ende Klasse
