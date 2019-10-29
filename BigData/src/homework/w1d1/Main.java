package homework.w1d1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
	public static void main(String[] str)
	{
		File file = new File("C:\\Users\\Tuguldur\\Documents\\Big Data\\testData.txt");
		String[] strArray;
		String tmpStr = "";
		List<Pair> list = new ArrayList<Pair>();
		Pair p;
		try(Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name()))
		{
			while (sc.hasNextLine()){
				tmpStr = sc.nextLine();
				strArray = tmpStr.split(" |-");
				for(String s: strArray)
				{
					if(s.matches(".*\\d.*")) continue;
					if(!s.endsWith(".") && s.contains(".")) continue;
					if(s.contains("_")) continue;
					if(!s.endsWith("?") && s.contains("?")) continue;
					if(!s.endsWith("!") && s.contains("!")) continue;
					if(s.isEmpty()) continue;
					p = new Pair(trim(s.trim()), 1);
					list.add(p);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main m = new Main();
		list.stream().sorted(m.func).forEach(System.out::println);
	}
	public static String trim(String str)
	{
		if(str.endsWith("\"") || str.endsWith("?") || str.endsWith("!") || str.endsWith("."))
			str = str.substring(0, str.length() - 1);
		if(str.startsWith("\""))
			str = str.substring(1);
		return str;
	}
	
	Comparator<? super Pair> func = (ob1, ob2) -> ob1.compareTo(ob2);
}
