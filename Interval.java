
class Interval 
{
	private int start = 0;
	private int end = 0;
	
	public Interval(int a, int b) throws Exception
	{
		if(a <= b)
		{
			start = a;
			end = b;
		}
		else
		{
			// Exception werfen, wenn a > b
			throw new Exception();
		}
	}

	public int getStart()
	{
		return start;
	}

	public int getEnd()
	{
		return end;
	}

	public String toString()
	{
		return "["+getStart()+","+getEnd()+"]";
	}

}
