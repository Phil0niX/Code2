

class Muenzwechselproblem
{
	public static void main(String args[])
	{
		// Wert des Wechselgeldes
		int b=0;

		// Eingabefehler behandeln
		if(args.length != 2)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		
		else
		{
			// zweites Argument auslesen, evtl. Fehler ausgeben
			try
			{
				b = Integer.parseInt(args[1]);
				if(b<0)
				{
					System.out.println("falsche Eingabe");
					return;
				}
			}
			catch(Exception e)
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			}

			// erstes Argument ist "Euro"
			if(args[0].equals("Euro"))
			{
				// Array mit Waehrungswerten anlegen
				int w[] = {200,100,50,20,10,5,2,1};
				ausgebenEuro(change(b,w));

			}
			// erstes Argument ist "Alternative
			else if(args[0].equals("Alternative"))
			{
				// Array mit Waehrungswerten anlegen
				int w[] = {200,100,50,20,10,5,4,2,1};
				ausgebenAlternative(change(b,w));
			}
			else
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
				return;
			}
		}
	}//Ende main()

	//Methode change()
	public static int[] change(int b, int[] w)
	{
		int x[] = new int[w.length];

		for(int i=0;i<w.length;i++)
		{
			// Invariante: Summe(x[0..i-1]) + b = eingabe
			if(b >= w[i])
			{
				x[i] = b / w[i];
				b = b % w[i];
			}
			else
			{
				x[i] = 0;
			}
		}
		return x;
	}

	// Methode ausgebenEuro() 
	public static void ausgebenEuro(int[] x)
	{
		System.out.println("Folgendes Wechselgeld wuerde gegeben werden: ");
		System.out.println("Euro:\t200\t100\t50\t20\t10\t5\t2\t1");
		System.out.print("Anzahl:");
		for(int i=0;i<x.length;i++)
		{
			System.out.print("\t"+x[i]);
		}
		System.out.println("");
	}

	//Methode ausgebenAlternative()
	public static void ausgebenAlternative(int[] x)
	{
		System.out.println("Folgendes Wechselgeld wuerde gegeben werden: ");
		System.out.println("Alternative:\t200\t100\t50\t20\t10\t5\t4\t2\t1");
		System.out.print("Anzahl:\t");

		for(int i=0;i<x.length;i++)
		{
			System.out.print("\t"+x[i]);
		}
		System.out.println("");
	}

}//Ende Klasse
