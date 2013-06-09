
class Job
{
	private int dauer = 0;
	private int deadline = 0;

	public Job(int dauer,int deadline) throws Exception
	{
		if(dauer < 0 || deadline < 0)
		{
			throw new Exception();
		}
		this.dauer = dauer;
		this.deadline = deadline;
	}

	public int getDauer()
	{
		return dauer;
	}

	public int getDeadline()
	{
		return deadline;
	}

	public String toString()
	{
		return "["+getDauer()+","+getDeadline()+"]";
	}

}
