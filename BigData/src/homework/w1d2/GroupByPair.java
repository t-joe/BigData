package homework.w1d2;

import java.util.ArrayList;
import java.util.List;

public class GroupByPair {
	private String key;
	private List<Integer> values;
	public GroupByPair(String key, int val)
	{
		values = new ArrayList<Integer>();
		this.key = key;
		values.add(val);
	}
	public void add(int val)
	{
		this.values.add(val);
	}
	@Override
	public String toString()
	{
		return "(" + key + ", " + values.toString() + ")";
	}
	public String getKey()
	{
		return this.key;
	}
	public List<Integer> getValues()
	{
		return this.values;
	}
	@Override
	public boolean equals(Object ob)
	{
		if(ob == null) return false;
		if(!(ob instanceof GroupByPair)) return false;
		GroupByPair g = (GroupByPair)ob;
		return g.key.equalsIgnoreCase(this.key);
	}
}
