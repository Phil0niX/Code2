
class Euclid
{
	public static void main(String args[])
	{
		// Eingaben auslesen und auf Fehler überprüfen
		try{
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			if( a<0 || b<0) System.out.println("Fehler! Bitte die Eingabe überprüfen!");
			// Algorithmus aufrufen
			else System.out.println(euclid(a,b));
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte die Eingabe überprüfen!");
		}
	}

	// Algorithmus
	static int euclid(int a, int b)
	{
		if(b==0) return a;
		else return euclid(b,a % b);
	}
}
