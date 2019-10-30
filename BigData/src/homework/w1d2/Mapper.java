package homework.w1d2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import homework.w1d1.Pair;

public class Mapper {
	private List<Pair> list = new ArrayList<Pair>();
	public Mapper(String lines)
	{
		String[] strArray;
		String tmpStr = "";
		Pair p;
		try
		{
			strArray = tmpStr.split(" |-");
				for(String s: strArray)
				{
					if(s.matches("[a-zA-Z]*[!|?|.|\"]?"))
					{
						if(s.isEmpty()) continue;
						p = new Pair(trim(s.trim()), 1);
						list.add(p);
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = list.stream().sorted().collect(Collectors.toList());//.forEach(System.out::println);
	}
	public static String trim(String str)
	{
		if(str.endsWith("\"") || str.endsWith("?") || str.endsWith("!") || str.endsWith("."))
			str = str.substring(0, str.length() - 1);
		if(str.startsWith("\""))
			str = str.substring(1);
		return str;
	}
	public List<Pair> getList()
	{
		return this.list;
	}
}
