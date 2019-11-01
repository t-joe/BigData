package homework.w1d3.Q2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Mapper {
	private List<Pair> list;
	Map<String, NumberPair> inMapCombiner;
	private String url = "";

	public Mapper(String url) {
		this.url = url;
		list = new ArrayList<Pair>();
		inMapCombiner = new HashMap<String, NumberPair>();
	}

	public List<Pair> map() {
		File file = new File(url);
		String[] strArray;
		String tmpStr = "";
		String firstChar = "";
		NumberPair tmpPair = null;
		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()) {
				tmpStr = sc.nextLine();
				strArray = tmpStr.split(" |-");
				for (String s : strArray) {
					if (s.matches("[\"|\']?[a-zA-Z]*[!|?|.|\"\']?")) {
						if (s.isEmpty())
							continue;
						s = trim(s);
						firstChar = String.valueOf(s.charAt(0)).toLowerCase();
						if (inMapCombiner.get(firstChar) == null)
							inMapCombiner.put(firstChar, new NumberPair(s.length(), 1));
						else
						{
							tmpPair = inMapCombiner.get(firstChar);
							inMapCombiner.put(firstChar, new NumberPair(tmpPair.getKey() + s.length(), tmpPair.getVal() + 1 ));
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return close();
	}

	private List<Pair> close() {
		for (Entry<String, NumberPair> e : inMapCombiner.entrySet()) {
			list.add(new Pair(e.getKey(), e.getValue()));
		}
		return list.stream().sorted().collect(Collectors.toList());
	}

	public static String trim(String str) {
		if (str.endsWith("\"") || str.endsWith("?") || str.endsWith("!") || str.endsWith(".") || str.endsWith("\'"))
			str = str.substring(0, str.length() - 1);
		if (str.startsWith("\"") || str.startsWith("\'"))
			str = str.substring(1);
		return str;
	}

	public List<Pair> getList() {
		return this.list;
	}
}
