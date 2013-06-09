
//kein Import

class Anwendung
{
	public static void main(String args[])
	{
		// Koordinaten fuer 3 Punkte
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		int x3 = 0;
		int y3 = 0;

		// Parameter verarbeiten, evtl. falsche Eingaben abfangen
		if(args.length != 0 && args.length != 6) 
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		// 6 Parameter uebergeben
		else if(args.length == 6)
		{
			try
			{
				x1 = Integer.parseInt(args[0]);
				y1 = Integer.parseInt(args[1]);
				x2 = Integer.parseInt(args[2]);
				y2 = Integer.parseInt(args[3]);
				x3 = Integer.parseInt(args[4]);
				y3 = Integer.parseInt(args[5]);
			}
			catch(Exception e)
			{
				System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			}
		}
		// keine Parameter uebergeben -> Zufallszahlen zwischen -1000 und 1000
		else
		{
			// Generator
			java.util.Random generator = new java.util.Random();
			// Grenzen
			int grenze = 1000;
			x1 = generator.nextInt(2*grenze+1)-grenze;
			y1 = generator.nextInt(2*grenze+1)-grenze;
			x2 = generator.nextInt(2*grenze+1)-grenze;
			y2 = generator.nextInt(2*grenze+1)-grenze;
			x3 = generator.nextInt(2*grenze+1)-grenze;
			y3 = generator.nextInt(2*grenze+1)-grenze;
		}
		
		// Punkte erstellen
		Punkt p1 = new Punkt(x1,y1);
		Punkt p2 = new Punkt(x2,y2);
		Punkt p3 = new Punkt(x3,y3);

		// Dreieck erstellen
		Dreieck d;
		try
		{
			d = new Dreieck(p1,p2,p3);
		}
		catch(Exception e)
		{
			System.out.println("Punkte liegen auf einer Geraden!");
			return;
		}
		System.out.println("1. Punkt: ("+ p1.getX()+","+p1.getY()+")");
		System.out.println("2. Punkt: ("+ p2.getX()+","+p2.getY()+")");
		System.out.println("3. Punkt: ("+ p3.getX()+","+p3.getY()+")");
		System.out.println("Der Umfang des Dreiecks betraegt: "+d.umfang());

	}//Ende main()

}//Ende Klasse
