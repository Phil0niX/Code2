import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.ArrayList;

class LatenessScheduling
{
	// berechnete Verspaetung der Jobs
	private static int verspaetung;

	public static void main(String args[])
	{
		if(args.length != 2)
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
				if(args[0].equals("Interval"))
				{

					// Array einlesen
					Interval[] unsortiertesArray = einlesen(args[1]);
					System.out.println("Bearbeite die Datei \""+args[1]+"\".\n");
					System.out.println("Es wurden "+unsortiertesArray.length+" Zeilen mit folgendem Inhalt gelesen: ");
					gibAus(unsortiertesArray);
					System.out.println("");


					// Interval-Algorithmus
					list = intervalScheduling(unsortiertesArray);
					System.out.println("Berechnetes Intervallscheduling: ");
					gibAus(list);
				}
				else if(args[0].equals("Lateness"))
				{

					// Array einlesen
					Job[] unsortiertesArray = einlesenJobs(args[1]);
					System.out.println("Bearbeite die Datei \""+args[1]+"\".\n");
					System.out.println("Es wurden "+unsortiertesArray.length+" Zeilen mit folgendem Inhalt gelesen: ");
					gibAus(unsortiertesArray);
					System.out.println("");


					//Lateness-Algorithmus
					int[] jobs = latenessScheduling(unsortiertesArray);
					System.out.println("Berechnetes Latenessscheduling: ");
					gibAus(jobs);
					System.out.println("Berechnete maximale Verspätung: "+verspaetung);
				}
				else
				{
					System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
					return;
				}

			}
			catch(Exception e)
			{
				System.out.println("Angegebenen Datei existiert nicht oder ist fehlerhaft!");
				return;
			}
		}//Ende else


	}//Ende main()





	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	// Algorithmus


	// intervalScheduling()
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
	

	// latenessScheduling()
	public static int[] latenessScheduling(Job[] unsortiertesArray)
	{
		Job[] array = kopiere(unsortiertesArray);
		sortiere(array);
		System.out.println("Sortiert: ");
		gibAus(array);
		System.out.println("");
		
		// Jobs in neues Array ueberfuehren
		int summe = 0;
		int[] rueckgabe = new int[unsortiertesArray.length+1];
		rueckgabe[0] = 0;

		for(int i=0;i<array.length;i++)
		{
			
			rueckgabe[i+1] = rueckgabe[i] + array[i].getDauer();
			/*	
			if(rueckgabe[i+1] > array[i].getDeadline())
			{
				verspaetung += (rueckgabe[i+1] - array[i].getDeadline());
			}
			*/
			
		}
		
		if(rueckgabe[rueckgabe.length-1] > array[array.length-1].getDeadline())
		{
			verspaetung = rueckgabe[rueckgabe.length-1] - array[array.length-1].getDeadline();
		}
		

		return rueckgabe;
	}//Ende latenessScheduling()

	public static boolean kompatibel(Interval a, Interval b)
	{
		if(a.getEnd() <= b.getStart() && a.getStart() != b.getStart())
			return true;
		else 
			return false;
	}//Ende kompatibel()




	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	// Ausgabe



	// gibAus()
	public static void gibAus(Interval[] array)
	{
		System.out.print("[");
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i].toString());
		}
		System.out.print("]\n");
	}

	public static void gibAus(int[] array)
	{
		System.out.print("[");
		for(int i=0;i<array.length-1;i++)
		{
			if(i != array.length-2)
				System.out.print(array[i]+",");
			else System.out.print(array[i]);
		}
		System.out.println("]");
	}

	public static void gibAus(ArrayList<Interval> list)
	{
		System.out.print("[");
		for(int i=0;i<list.size();i++)
		{
			System.out.print(list.get(i).toString());
		}
		System.out.println("]");
	}

	public static void gibAus(Job[] array)
	{
		System.out.print("[");
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i].toString());
		}
		System.out.print("]\n");
	}
	



	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	// Array in ArrayList umwandeln

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

	public static ArrayList<Job> gibArrayList(Job[] array)
	{
		ArrayList<Job> list = new ArrayList<Job>();
		for(int i=0;i<array.length;i++)
		{
			list.add(array[i]);
		}
		return list;
	}//Ende gibArrayList()




	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	// Kopieren

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

	public static Job[] kopiere(Job[] eingabe)
	{
		Job[] ausgabe = new Job[eingabe.length];
		for(int i=0;i<eingabe.length;i++)
		{
			ausgabe[i] = eingabe[i];
		}
		return ausgabe;
	}//Ende kopiere()





	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	// MergeSort()



	// Interval
	// sortieren() Interval
	public static void sortiere(Interval[] array)
	{
		mergeSort(array,0,array.length-1);
	}//Ende sortiere()
	
	public static void mergeSort(Interval[] array,int unten, int oben)
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
	
	public static void merge(Interval[] array,int unten,int mitte,int oben)
	{
		// neues Array erzeugen, in dem die Werte sortiert eingefuegt werden
		Interval[] neu = new Interval[oben-unten+1];
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
				if(array[unten].getEnd() < array[mitteOben].getEnd())
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
	
	
	// Job
	// sortieren() Job
	public static void sortiere(Job[] array)
	{
		mergeSort(array,0,array.length-1);
	}//Ende sortiere()
	
	public static void mergeSort(Job[] array,int unten, int oben)
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
	
	// mergeSort() ----------------------------------------------------------------------
	public static void merge(Job[] array,int unten,int mitte,int oben)
	{
		// neues Array erzeugen, in dem die Werte sortiert eingefuegt werden
		Job[] neu = new Job[oben-unten+1];
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
				if(array[unten].getDeadline() <= array[mitteOben].getDeadline())
				{
					if(array[unten].getDeadline() == array[mitteOben].getDeadline())
					{
						if(array[unten].getDauer() > array[mitteOben].getDauer())
						{
							neu[i] = array[mitteOben];
							mitteOben++;
						}
						else
						{
							neu[i] = array[unten];
							unten++;
						}
					}
					else
					{
						neu[i] = array[unten];
						unten++;
					}
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
	

	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------
	// Datei einlesen und Token auslesen



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

	// einlesenJobs()
	public static Job[] einlesenJobs(String pfad) throws Exception
	{
		// ArrayList fuer die Intervalle
		// wird spaeter in Interval-Array umgewandelt
		ArrayList<Job> list = new ArrayList<Job>();

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
			int dauer = Integer.parseInt(st.nextToken());
			int deadline = Integer.parseInt(st.nextToken());

			// neues Intervall erzeugen und zur ArrayList hinzufügen
			// kann Exception werfen, siehe Klasse Intervall
			list.add(new Job(dauer,deadline));

		}//Ende while()
		
		// ArrayList in Array konvertieren und zurueckgeben
		// (neues Array gibt den Typ vom zurueckgegebenen Array an)
		return list.toArray(new Job[0]);

	}//Ende einlesenJobs()

	
	}//Ende Klasse
