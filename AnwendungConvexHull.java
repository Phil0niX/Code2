

class AnwendungConvexHull
{
	public static void main(String args[])
	{
		java.util.Random generator = new java.util.Random();
		int n = 1000;
		Punkt P[] = new Punkt[n];
		for(int i=0;i<P.length;i++)
		{
			P[i] = new Punkt(generator.nextInt(),generator.nextInt());
		}

		java.util.LinkedList<Punkt> list = ConvexHull.simpleConvex(P);

		//while(list.getFirst() != null)
		try
		{
			Punkt x = list.remove();
			System.out.println("("+x.getX()+","+x.getY()+")");
		}
		catch(Exception e)
		{
			return;
		}


	}//Ende main()
}//Ende Klasse
