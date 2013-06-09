import java.util.Random;

class LongestCommonSubsequence
{

	// zufaellige Folgen
	private static String zufaelligeFolge1;
	private static String zufaelligeFolge2;

	// 3. Feld enthaelt 0,1 oder 2 fuer links oben, oben, links
	private static int feld[][][];



	public static void main(String args[])
	{
		// Ungueltige Eingabe abfangen---------------------------------------------------
		if(args.length != 1)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		else
		{
			// Eingabe n
			int n=0;

			// Ungueltige Eingabe abfangen
			try
			{
				n = Integer.parseInt(args[0]);
			}
			catch(Exception e)
			{
				System.out.println("Bitte eine natuerliche Zahl fuer die Laenge der Zeichenfolgen eingeben!");
				return;
			}
			if(n<0)
			{
				System.out.println("Bitte eine natuerliche Zahl fuer die Laenge der Zeichenfolgen eingeben!");
				return;
			}

		//------------------------------------------------------------------------------
			
			// Zufallsfolgen erstellen
			zufaelligeFolge1 = gibZufaelligeFolge(n);
			zufaelligeFolge2 = gibZufaelligeFolge(n);	
			
			// Algorithmus ausfuehren
			long tStart = System.currentTimeMillis();
			String lcs = gibLCS(zufaelligeFolge1,zufaelligeFolge2);
			long tEnd = System.currentTimeMillis();


			if(n<25)
			{
				//Testausgabe---------------------------------------------------------------------
				System.out.print("\t");
				for(int j=0;j<zufaelligeFolge2.length();j++)
				{
					System.out.print("\t"+zufaelligeFolge2.charAt(j)+"");
				}
				System.out.println("");
				for(int i=0;i<feld.length;i++)
				{
					if(i>0)
						System.out.print(zufaelligeFolge1.charAt(i-1) + "\t");
					if(i==0)
						System.out.print("\t");
					for(int j=0;j<feld[1].length;j++)
					{
						
						System.out.print(feld[i][j][0]+"("+feld[i][j][1]+")\t");
					}
					System.out.println("");
				}
				//--------------------------------------------------------------------------------
			}


			//Ausgabe
			System.out.println("1. Folge "+zufaelligeFolge1);
			System.out.println("2. Folge "+zufaelligeFolge2);
			if(lcs.equals(""))	System.out.println("Keine gemeinsame Teilfolge!");
			else System.out.println("Die laengste gemeinsame Teilfolge: "+lcs);
			System.out.println("Benoetigte Zeit: "+(tEnd-tStart)+"ms");

		}//Ende else

	}//Ende main()



/*----------------------------------------------------------------------------------------*/
	// Algorithmus
	public static String gibLCS(String zufaelligeFolge1, String zufaelligeFolge2)
	{
		// Feld erzeugen
		feld = new int[zufaelligeFolge1.length()+1][zufaelligeFolge2.length()+1][2];
		//String teilFolge = "";

		// Anfangswerte initialisieren
		// 1. Zeile
		for(int i=0;i<feld[1].length;i++)
		{
			feld[1][i][0] = 0;
		}
		// 1. Spalte
		for(int i=0;i<feld.length;i++)
		{
			feld[i][1][0] = 0;
		}


		for(int i=1;i<feld.length;i++)
		{
			// Invariante: feld[i,j] ist max(feld[i-1,j-1],feld[i,j-1],feld[i-1,j])
			for(int j=1;j<feld[i].length;j++)
			{
				if (zufaelligeFolge1.charAt(i-1) == zufaelligeFolge2.charAt(j-1))
				{
					// feld[i,j] den Wert von "oben links" zuweisen
					feld[i][j][0] = feld[i-1][j-1][0] +1;

					// 0 steht fuer Pfeil nach oben links
					feld[i][j][1] = 0;
				}
				else
				{
					if(feld[i-1][j][0] >= feld[i][j-1][0])
					{
						// feld[i,j] den Wert von "oben" zuweisen
						feld[i][j][0] = feld[i-1][j][0];

						// 1 steht fuer Pfeil nach oben
						feld[i][j][1] = 1;
					}
					else
					{
						// feld[i,j] den Wert von "links" zuweisen
						feld[i][j][0] = feld[i][j-1][0];

						// 2 steht fuer Pfeil nach links
						feld[i][j][1] = 2;
					}
				}
			}
		}

		
		return teilFolge();
	}//Ende gibLCS()




/*----------------------------------------------------------------------------------------*/
	// teilFolge ermitteln

	public static String teilFolge()
	{
		return teilFolge(feld.length-1,feld[1].length-1);
	}

	public static String teilFolge(int i,int j)
	{
		if(i==0 || j==0)
		{
			return "";
		}
		if(feld[i][j][1] == 0)
		{
			return teilFolge(i-1,j-1) + zufaelligeFolge1.charAt(i-1);
		}
		if(feld[i][j][1] == 1)
		{
			return teilFolge(i-1,j);
		}
		else
		{
			return teilFolge(i,j-1);
		}
	}
	



/*----------------------------------------------------------------------------------------*/
	//Hilfsmethoden


	// Hilfsmethode zur Erstellung von zufaelligen Folgen
	public static String gibZufaelligeFolge(int n)
	{
		Random r = new Random();
		String alphabet ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder res = new StringBuilder(n);
		while (--n>=0)
			res.append(alphabet.charAt(r.nextInt(alphabet.length())));
		return res.toString();

	}//Ende gibZufaelligeFolge()

/*----------------------------------------------------------------------------------------*/



}//Ende Klasse
