package homework.w1d3.Q1;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;

import homework.w1d1.Pair;

public class Mapper {
	private List<Pair> list;
	Map<String, Integer> inMapCombiner;
	private String url = "";

	public Mapper(String url) {
		this.url = url;
		list = new ArrayList<Pair>();
		inMapCombiner = new HashMap<String, Integer>();
	}

	public List<Pair> map() {
		File file = new File(url);
		String[] strArray;
		String tmpStr = "";
		String trimmed = "";
		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()) {
				tmpStr = sc.nextLine();
				strArray = tmpStr.split(" |-");
				for (String s : strArray) {
					if (s.matches("[\"|\']?[a-zA-Z]*[!|?|.|\"\']?")) {
						if (s.isEmpty())
							continue;
						s = trim(s);
						if (inMapCombiner.get(s) == null)
							inMapCombiner.put(s, 1);
						else
							inMapCombiner.put(s, inMapCombiner.get(s) + 1);
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
		for (Entry<String, Integer> e : inMapCombiner.entrySet()) {
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
