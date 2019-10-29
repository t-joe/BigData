package homework.w1d1;

public class Pair implements Comparable{
	private String key;
	private int value;
	public Pair(String key, int value)
	{
		this.key = key;
		this.value = value;
	}
	
	@Override
	public boolean equals(Object ob)
	{
		if(ob == null) return false;
		if(!(ob instanceof Pair)) return false;
		Pair p = (Pair)ob;
		if(p.key.equalsIgnoreCase(this.key))
			return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		return "( " + key + ", " + value + ")"; 
	}

	@Override
	public int compareTo(Object o) {
		Pair p = (Pair) o;
		return this.key.compareToIgnoreCase(p.key);
	}
}
