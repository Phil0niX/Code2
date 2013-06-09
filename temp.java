
class PotenzDandC
{
	public static void main(String args[])
	{
		int a,n;

		if(args.length == 2)
		{
			try
			{
				a = Integer.parseInt(args[0]);
				n = Integer.parseInt(args[1]);
				System.out.println(berechne(a,n));
			}
			catch(Exception e)
			{
				System.out.println("Fehler bei Eingabe!");
			}
		}
		else
		{
			System.out.println("Fehler bei Argumenten!");
		}
	}

	public static int berechne(int a,int n)
	{
		if(n == 1)
		{
			return a;
		}
		else
		{
			if(n % 2 == 0)
			{	
				int x = berechne(a,n/2);
				return x*x;
			}
			else
			{
				int x = berechne(a,n/2);
				return x*x*a;
			}
		}
	}

}
