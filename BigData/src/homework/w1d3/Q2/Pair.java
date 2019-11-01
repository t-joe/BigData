package homework.w1d3.Q2;

public class Pair implements Comparable<Pair>{
	private String key;
	private NumberPair value;
	public Pair(String key, NumberPair value)
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
		if(p.key == this.key)
			return true;
		return false;
	}
	
	@Override
	public String toString()
	{
		return "( " + key + ", " + value + ")"; 
	}

	public String getKey()
	{
		return this.key;
	}
	public NumberPair getValue()
	{
		return this.value;
	}
	public void setValue(NumberPair val)
	{
		this.value = val;
	}

	@Override
	public int compareTo(Pair o) {
		return this.key.compareToIgnoreCase(o.key);
	}
}
