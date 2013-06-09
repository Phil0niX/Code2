
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.ArrayList;


class Editierdistanz
{
	public static void main(String args[])
	{




		// ein Argument -> Dateiname
		if(args.length == 1)
		{
			// Datei einlesen
			ArrayList<String> list = new ArrayList<String>();
			String a;
			RandomAccessFile file;
			int distanz = -1;

			try
			{
				file = new RandomAccessFile(args[0],"r");
				int i=0;

				while( (a = file.readLine()) != null)
				{
					list.add(a);
				}
			}
			catch(Exception e)
			{
				System.out.println("Fehler beim Lesen der Datei!");
			}

			for(int i=0;i<list.size();i++)
			{
				for(int j=i+1;j<list.size();j++)
				{
					// Algorithmus aufrufen
					distanz = berechneDistanz(list.get(i),list.get(j));

					// Ausgabe
					System.out.println("");
					System.out.println("1. String: \""+list.get(i)+"\"");
					System.out.println("2. String: \""+list.get(j)+"\"");
					System.out.println("Levenshtein-Distanz: "+distanz);
				}
			}

		}




		// zwei Argumente -> Distanz berechnen
		else if(args.length == 2)
		{
			System.out.println("1. String: \""+args[0]+"\"");
			System.out.println("2. String: \""+args[1]+"\"");
			System.out.println("Levenshtein-Distanz: "+berechneDistanz(args[0],args[1]));

		}



		// alles andere -> Fehler
		else
		{
			System.out.println("Fehler beim Lesen der Argumente!");
			return;
		}
	}


/*------------------------------------------------------------------------------------------*/
// Algorithmus

	public static int berechneDistanz(String a, String b)
	{
		int m = a.length();
		int n = b.length();
		
		int[][] D = new int[m+1][n+1];
		
		D[0][0] = 0;
		
		for(int i=1;i<D.length;i++)
		{
			// Invariante: D[i-1][0] = i-1
			D[i][0] = i;
		}

		for(int j=1;j<D[0].length;j++)
		{
			// Invariante: D[0][j-1] = j-1
			D[0][j] = j;
		}

		for(int i=1;i<D.length;i++)
		{
			
			for(int j=1;j<D[i].length;j++)
			{
				/* Invariante: 
				Wenn i-1 Buchstabe des ersten Strings gleich dem j-2 Bichstabe des zweiten Strings dann: D[i][j-1] = min(D[i-1][j-2],D[i][j-2]+1,D[i-1][j-1]+1)
				Andernfalls: D[i][j-1] = min(D[i-1][j-2]+1,D[i][j-2]+1,D[i-1][j-1]+1)
				*/ 
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

}//Ende Klasse


