
import java.io.RandomAccessFile;
import java.util.StringTokenizer;


class Editierdistanz
{

	// globales Feld
	int[][] D;
	// globale Strings
	String a, b;
	// globale Zahl
	int zahl = 1;

	public static void main(String args[])
	{




		// ein Argument -> Dateiname
		if(args.length == 1)
		{

			// Datei einlesen
			RandomAccessFile file;
			int distanz = -1;

			try
			{
				// Strings einlesen
				file = new RandomAccessFile(args[0],"r");

				while( (a = file.readLine()) != null && (b= file.readLine()) != null)
				{
					// Algorithmus aufrufen
					distanz = berechneDistanz(a,b);

					// Ausgabe
					System.out.println("1. String: \""+a+"\"");
					System.out.println("2. String: \""+b+"\"");
					System.out.println("Levenshtein-Distanz: "+berechneDistanz(a,b));

					//System.out.println("Fuer \""+a+"\" und \""+b+"\" betraegt die Levenshtein-Distanz "+ berechneDistanz(a,b)+".");
				}
			}
			catch(Exception e)
			{
				System.out.println("Fehler beim Lesen der Datei!");
				return;
			}

		}


		// Dateiname + "-o" als Argumente -> Schritte ausgeben
		else if(args.length == 2 && args[2].equals("-o")
		{
			// Datei einlesen
			RandomAccessFile file;
			int distanz = -1;

			try
			{
				// Strings einlesen
				file = new RandomAccessFile(args[0],"r");

				while( (a = file.readLine()) != null && (b= file.readLine()) != null)
				{
					// Algorithmus aufrufen
					distanz = berechneDistanz(a,b);

					System.out.println("Loesung fuer \""+a+"\" --> \""+b+"\" mit Gesamtkosten "+ berechneDistanz(a,b)+":");
					System.out.println("==================================================");
					
					// Ausgabe der Editieroperationen
					ausgabeEditieroperationen();
				}
			}
			catch(Exception e)
			{
				System.out.println("Fehler beim Lesen der Datei!");
				return;
			}

		}

		// zwei Strings als Argumente -> Distanz berechnen
		else if(args.length == 2)
		{
			System.out.println("1. String: \""+args[0]+"\"");
			System.out.println("2. String: \""+args[1]+"\"");
			System.out.println("Levenshtein-Distanz: "+berechneDistanz(args[0],args[1]));

		}
		

		// zwei Strings + "-o" als Argumente -> Schritte ausgeben
		else if(args.length == 3 && args[2].equals("-o");
		{
			distanz = berechneDistanz(a,b);	
			System.out.println("Loesung fuer \""+a+"\" --> \""+b+"\" mit Gesamtkosten "+ berechneDistanz(a,b)+":");
			System.out.println("==================================================");
			ausgabeEditieroperationen();
			
		}
		// alles andere -> Fehler
		else
		{
			System.out.println("Fehler beim Lesen der Argumente!");
			return;
		}
	}//Ende main()


/*------------------------------------------------------------------------------------------*/
// Algorithmus

	public static int berechneDistanz(String a, String b)
	{
		int m = a.length();
		int n = b.length();
		
		D = new int[m+1][n+1];
		
		D[0][0] = 0;
		
		for(int i=1;i<D.length;i++)
		{
			D[i][0] = i;
		}

		for(int j=1;j<D[0].length;j++)
		{
			D[0][j] = j;
		}

		for(int i=1;i<D.length;i++)
		{
			for(int j=1;j<D[i].length;j++)
			{
				if(a.charAt(i-1)==b.charAt(j-1))
				{
					D[i][j] = min(D[i-1][j-1],D[i][j-1]+1,D[i-1][j]+1);
				}
				else
				{
					D[i][j] = min(D[i-1][j-1]+1,D[i][j-1]+1,D[i-1][j]+1);
				}
			}
		}

		return D[m][n];

	}//Ende berechneDistanz()


// Ausgabe der Operationen
	public static String ausgabeEditieroperationen()
	{
		ausgabeEditieroperationen(i,j-1);
	}
	
	
	public static String ausgabeEditieroperationen(int i,int j);
	{
		String s;

		// Loesche
		if(i>0 && D[i-1][j] + 1 == D[i][j])
		{
			
			s = ausgabeEditieroperationen(i-1, j);
			
			// String anpassen
			char x = a.charAt(i-1);
			a = delete(a,i-1);

			return s+(i++)+") Kosten 1: Loesche "+x+ "an Position "+i+" --> "+a+"\n";
		}

		// Fuege ein
  		if j>0 && D[i][j-1] + 1 == D[i][j]
		{
   			s = ausgabeEditieroperationen(i, j-1);

			// String anpaseen
			a = fuegeEin(a,i-1);

			return s+(i++)+") Kosten 1: Fuege "+
		}

		// Ersetze
  		if i>0 && j>0 && D[i-1][j-1] + 1 == D[i][j]
		{
    		s = ausgabeEditieroperationen(i-1, j-1);
		}

		// Gleich
  		if i>0 && j>0 && D[i-1][j-1]  == D[i][j]
		{
    		s = ausgabeEditieroperationen(i-1, j-1);
		}
  		return zahl++;
	}





/*------------------------------------------------------------------------------------------*/
// Hilfsmethoden

	public static int min(int x,int y,int z)
	{
		if(x <= y && x <= z)
		{
			return x;
		}
		else
		{
			if(y <= z)
			{
				return y;
			}
			else
			{
				return z;
			}
		}
	}//Ende min()

	public static String delete(String a, int j)
	{
		boolean x = true
		for(int i=0;i<a.length();i++)
		{
			if(i==j && x)
			{
				x = false;
				i--;
			}
			else
			{
				ausgabe += a.charAt(i);
			}
		}
	}

}//Ende Klasse


