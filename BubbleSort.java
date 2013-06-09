
class BubbleSort
{
	public static void main(String args[])
	{
		// Varibalen
		int n = 0;

		// Eingabe ueberpruefen und auswerten
		if(args.length == 0 || args.length > 1)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		try
		{
			n = Integer.parseInt(args[0]);
		}
		catch(Exception e)
		{
			System.out.println("Fehler! Bitte Eingabe ueberpruefen!");
			return;
		}
		
		// Array der Laenge n mit Zufallszahlen erstellen
		java.util.Random generator = new java.util.Random();
		int[] array = new int[n];
		for(int i=0;i<array.length;i++)
		{
			array[i] = generator.nextInt();
		}

		// Array mit bubbleSort sortieren und Laufzeit messen
		long tStart = System.currentTimeMillis();
		bubbleSort(array);
		long tEnd = System.currentTimeMillis();
		long msecs = tEnd - tStart;

		// Ausgabe
		if(isSorted(array)) System.out.println("Feld der Laenge "+array.length+" wurde in "+msecs+" ms sortiert!");
		else System.out.println("Feld ist nicht sortiert!");

	}

	public static void bubbleSort(int[] array)
	{
		for(int i=0;i<array.length-1;i++)
		{
			for(int j=i+1;j<array.length;j++)
			{
				if(array[i] > array[j])
				{
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
	}

	public static boolean isSorted(int[] array)
	{
		for(int i=0;i<array.length-1;i++)
		{
			if(array[i] > array[i+1]) return false;
		}
		return true;
	}

}
