
class Dreieck
{
	// Variablen
	private Punkt p1;
	private Punkt p2;
	private Punkt p3;

	// Konstruktor
	public Dreieck(Punkt p1, Punkt p2, Punkt p3) throws Exception
	{
		if(abhaengig(p1,p2) || abhaengig(p2,p3) || abhaengig(p3,p1)) 
		{
			throw new Exception();
		}
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	// get-Methoden
	// 1. Punkt
	public Punkt getP1()
	{
		return p1;
	}
	// 2. Punkt
	public Punkt getP2()
	{
		return p2;
	}
	// 3. Punkt
	public Punkt getP3()
	{
		return p3;
	}

	public boolean abhaengig(Punkt p, Punkt q)
	{
		int pqx = p.getX()*q.getY();
		int pqy = q.getX()*p.getY();
		if(pqx - pqy == 0) return true;
		else return false;
	}

	// euclidDistance-Methode, Abstand zwischen zwei Punkten berechnen
	public double euclidDistance(Punkt p1, Punkt p2)
	{
		// Abstände der x und y Koordinate berechnen	
		int x = p1.getX() - p2.getX();
		int y = p1.getY() - p2.getY();
		
		// Satz des Pythagoras
		return Math.sqrt((x*x) + (y*y));
	}

	public double umfang()
	{
		return euclidDistance(p1,p2) + euclidDistance(p2,p3) + euclidDistance(p3,p1);
	}

}//Ende Klasse
