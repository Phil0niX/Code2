import java.util.*;
import java.awt.geom.Line2D;

class ConvexHull
{
	private static LinkedList<Punkt[]> list = new LinkedList<Punkt[]>();
	private static LinkedList<Punkt> x = new LinkedList<Punkt>();
	// Linked List wird benoetigt, um die Punkte, die zur Zeit die Convexe Huelle darstellen, zu speichern 
	public static LinkedList<Punkt> simpleConvex(Punkt[] P)
	{
		//LinkedList<Punkt[]> list = new LinkedList<Punkt[]>();

		boolean valid = false;
		for(int p = 0; p < P.length;p++)
		{
			for(int q = p; q < P.length;q++)
			{
				valid = true;
				for(int r = q; r < P.length;r++)
				{
					if(liegtLinks(P[p],P[q],P[r])) valid = false;
				}
				
				if(valid)
				{
					Punkt[] temp = { P[p] , P[q] };
					list.add(temp);
				}
			}
			
		}
		konstruiere(0);
		return x;
		
	}//Ende simpleConvex()
	
	
	public static boolean liegtLinks(Punkt p, Punkt q, Punkt r)
	{
		//Line2D.Double pq = new Line2D.Double(p.getX(),p.getY(),q.getX(),q.getY());
		/*
		int bla = Line2D.relativeCCW(p.getX(),p.getY(),q.getX(),q.getY(),r.getX(),r.getY());
		if(bla > 0) return true;
		else return false;
		*/
		int pqx = q.getX() * p.getX();
		int pqy = q.getY() * p.getY();
		int prx = r.getX() * p.getX();
		int pry = r.getX() * p.getX();

		if((pqx*pry)-(prx*pqy)>0)
		{
			return true;
		}
		return false;
		
	}
	
	
	
/*	
	public static boolean liegtLinks(Punkt p, Punkt q, Punkt r)
	{
		// Steigung ist positiv -> y-Wert von r muss größer als y-Wert von x-Stelle der Geraden pq
		double steigung = 0;
		if(q.getX() - p.getX() != 0)
		{
			if((q.getY() - p.getY())/(q.getX() - p.getX()) >= 0) return yWert(p,q,r.getX()) > r.getY();
		
		// Steigung ist negativ -> y-Wert von r muss kleiner als y-Wert an der x-Stelle der Geraden pq
			else return yWert(p,q,r.getX()) > r.getY();
		}
		// wenn Steigung "unendlich" d.h. durch 0 dividiert wird
		else
		{
			return r.getX() < p.getX();
		}
	}//Ende liegtLinks()

	// Hilfsmethode yWert(), liefert y Wert der Geraden pq an der Stelle x
	public static double yWert(Punkt p, Punkt q, int x)
	{
		double steigung = 0;
		double start = 0;
		// y = mx + b 
		// steigung = m = (y2 - y1)/(x2 - x1)
		if(q.getX() - p.getX() != 0)
			steigung = (q.getY() - p.getY()) / (q.getX() - p.getX());
		else
			return q.getX();
		// start = b = y1 - m*x1
		start = p.getY() - (steigung*p.getX());
		return steigung * x + start;
	}
*/	

	// Hilfsmethode konstruiere(), konstruiert eine LinkedList in dem alle Punkte der convexen Huelle enthalten sind
	public static void konstruiere(int index)
	{
		try{
		Punkt[] temp = list.remove(index);
		for(int i=0;i<list.size();i++)
		{
			if(temp[1] == list.get(i)[0])
			{
				x.add(temp[0]);
				x.add(temp[1]);
				konstruiere(i);
				return;
			}
			else return;
		}
		}
		catch(Exception e)
		{
			return;
		}
	}//Ende suche()
				
}//Ende Klasse
