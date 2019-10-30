package homework.w1d2.partA;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import homework.w1d1.Pair;

public class Mapper {
	private List<Pair> list = new ArrayList<Pair>();
	private String url = "";
	public Mapper(String url)
	{
		this.url = url;
	}
	public List<Pair> map()
	{
		File file = new File(url);
		String[] strArray;
		String tmpStr = "";
		Pair p;
		try(Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name()))
		{
			while (sc.hasNextLine()){
				tmpStr = sc.nextLine();
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
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list = list.stream().sorted().collect(Collectors.toList());
		return list;
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
