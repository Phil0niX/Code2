

import java.util.ArrayList;

class Partition
{
	public static void main(String args[])
	{
		ArrayList<Integer> list;
		try
		{
			list = toList(args);
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		if(Partition(list))
		{
			System.out.println("Menge von Zahlen kann partitioniert werden!");
		}
		else
		{
			System.out.println("Menge von Zahlen kann NICHT partitioniert werden!");
		}
	}

	public static ArrayList<Integer> toList(String args[]) throws Exception
	{
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<args.length;i++)
		{
			int n = Integer.parseInt(args[i]);
			if(n < 0)
			{
				throw new Exception();
			}
			else
			{
				list.add(n);
			}
		}
		return list;
	}

	public static boolean Partition(ArrayList<Integer> z)
	{
		// Summe berechnen
		int summe = 0;
		for(int i=0;i<z.size();i++)
		{
			summe += z.get(i);
		}

		// Pruefen, ob die Summe ungerade ist, wenn ja, false zurueckgeben
		if(summe % 2 == 0)
		{
		
			int[][] feld = new int[z.size()+1][summe+1];
			for(int i=0;i<=z.size();i++)
			{
				for(int j=0;j<=summe/2;j++)
				{
					if(j==0)
					{
						feld[i][j] = 1;
					}
					else if(i==0)
					{
						feld[i][j] = 0;
					}
					else if(feld[i-1][j] == 1 || (z.get(i-1) <= j && feld[i-1][j-z.get(i-1)] == 1))
					{
						feld[i][j] = 1;
					}
					else
					{
						feld[i][j] = 0;
					}
				}
			}
			if(feld[z.size()][summe/2] == 1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}

	}//Ende Partition()

}//Ende Klasse
