
class Eratosthenes
{
	public static void main(String args[])
	{
		boolean b = false;
		int n = 0;

		// Obergrenze n auslesen, evtl. Exceptions abfangen
		try{
			n = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte die Eingabe überprüfen!");
			return;
		}	
		if(n<0) 
		{
			System.out.println("Fehler! Bitte die Eingabe überprüfen!");
			return;
		}

		// Prüfen, ob -o als Argument übergeben wurde
		if(args.length != 1)
		{
			if(args[1].equals("-o")) b = true;
		}

		// Algorithmus ausführen
		boolean a[] = eratosthenes(n);

		// Je nach Argumente, das Array oder die Anzahl der Primzahlen ausgeben

		// mit "-o"
		if(b)
		{
			for(int i = 2; i<=n;i++)
			{
				if(a[i]) System.out.print(i + " ");
			}
			System.out.println("");

			int j = 0;
			for(int i=2; i<=n;i++)
			{
				if(a[i]) j++;
			}
			System.out.println("Anzahl der Primzahlen: " + j);


		}
		// ohne "-o"
		else
		{
			int j = 0;
			for(int i=2; i<=n;i++)
			{
				if(a[i]) j++;
			}
			System.out.println("Anzahl der Primzahlen: " + j);
		}

	}

	// Algorithmus
	static boolean[] eratosthenes(int n)
	{
		boolean a[] = new boolean[n+1];
		// Alle Werte im Array auf "true" setzen
		for(int i=2;i<=n;i++) a[i] = true;
		/* 	if Array[i] == true then
			Vielfachen von i im Array auf false setzen
			
			Invariante (?): Array[1..i] == true dann ist i Primzahl
		*/
		for(int i=2;i<=n;i++)
		{
			// Prüfen, ob Array[i] true ist
			if(a[i])
			{
				// Vielfache auf false setzen
				for(int j = 2;(j*i) <= (n);j++)
				{
					a[j*i] = false;
				}
			}
		}
		// Array zurückgeben
		return a;
	}
}
