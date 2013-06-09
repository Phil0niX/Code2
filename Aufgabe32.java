
class Aufgabe32
{
	// angestrebte Zeit
	static long zeit = 0;
	static int mitte = 0;
	static int mitte2 = 0;
	public static void main(String args[])
	{
		//Variablen
		// aktuelle Zeit
		long x = 0;
		// aktuelle Feldlaenge
		int n = 1000;
		// nach Schleifenaustritt merken, ob n verdoppelt oder halbiert wurde
		boolean verdoppelt = true;

		//-------------------------------------------------------------------------------------
		// uebergebene Argumente auswerten, evtl. Programm abrechen
		if(args.length == 1)
		{
			try
			{
				float temp = Float.parseFloat(args[0]);
				zeit = (long) (temp * 1000);
			}
			catch(Exception e)
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
				return;
			}
		}
		else
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}

		if(zeit < 0)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}

		//-------------------------------------------------------------------------------------

		// vor erstem Schleifendurchlauf Zeit mit Standardwert messen
		x = go(n);
		
		while(x<zeit)
		{
			// Wenn die benoetigte Zeit kleiner als die angestrebte Zeit ist, Laenge des Feldes verdoppeln
			n = n*2;
			System.out.println("n = "+n);

			// Zeit bestimmen die fuer das aktuelle n benötigt wird
			x = go(n);
			System.out.println("Abstand zur angestrebten Zeit: "+ Math.abs(x-zeit)+" ms");
			System.out.println("Benoetigte Zeit: "+x+" ms");
			System.out.println("Angestrebte Zeit: " + zeit+" ms");
			System.out.println("");
		}

		if(Math.abs(x - zeit) <= 100)
		{
			System.out.println("Die Laenge des Feldes betraegt " +n+".");
			return;
		}

		//-------------------------------------------------------------------------------------

		// binaere Suche starten
		System.out.println("Starte binaere Suche mit: n/2 = "+ (n/2) +" und n = "+ n);
		binaereSuche(n/2,n);


	}//Ende main()

	public static void binaereSuche(int unten, int oben)
	{
		// Mitte des Arrays berechnen 
		mitte2 = mitte;
		mitte = ((oben - unten) / 2) + unten;
		// Zeit fuer Sortierung ermitteln
		long x = go(mitte);

		// Ausgabe
		System.out.println("Abstand zur angestrebten Zeit: "+Math.abs(x - zeit)+" ms");
		System.out.println("Benoetigte Zeit: "+x+" ms");
		System.out.println("Angestrebte Zeit: "+zeit+" ms");
		System.out.println("");

		// Abbruchbedingung
		if(Math.abs(x - zeit) <= 100)
		{
			System.out.println("Die Laenge des Feldes betraegt " + mitte+".");
			return;
		}

		// Laufzeitschwankungen versuchen zu beheben
		if(mitte2 == mitte)
		{
			binaereSuche(mitte,oben+500);
		}

		// rekursive Aufrufe
		// benoetigte Zeit ist größer als angestrebte Zeit -> mit unterer Hälfte weitermachen
		if(x >= zeit)
		{
			System.out.println("n = "+(mitte));
			binaereSuche(unten,mitte);
		}

		// benoetigte Zeit ist kleiner als angestrebte Zeit -> mit oberer Hälfte weitermachen
		else
		{
			System.out.println("n = "+(mitte));
			binaereSuche(mitte,oben);
		}

	}//Ende binaereSuche()
		
	// Methode um Array zu erstellen, zu sortieren und Zeit zu messen
	public static long go(int n)
	{
		// Garbage Collection
		System.gc();

		// Variablen zur Zeitmessung
		long tStart,tEnd,msec;

		// Array absteigend befuellen
		int[] array = new int[n];
		befuellen(array);
		
		// Zeit messen und Sortierung starten
		tStart = System.currentTimeMillis();
		BubbleSort.bubbleSort(array);
		tEnd = System.currentTimeMillis();
		msec = tEnd-tStart;

		// Zurückgeben
		return msec;
	}

	// Methode um Array absteigend zu befuellen
	public static void befuellen(int[] array)
	{
		for(int i=0;i< array.length;i++)
		{
			array[array.length-1-i] = i;
		}
	}

}//Ende Klasse
