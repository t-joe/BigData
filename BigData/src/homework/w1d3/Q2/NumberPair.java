package homework.w1d3.Q2;

public class NumberPair {
	private int key;
	private int val;
	public NumberPair(int key, int val)
	{
		this.key = key;
		this.val = val;
	}
	
	public int getKey()
	{
		return this.key;
	}
	public int getVal()
	{
		return this.val;
	}
	
	@Override
	public String toString()
	{
		return "[" + key+ ", " + val + "]";
	}
}
