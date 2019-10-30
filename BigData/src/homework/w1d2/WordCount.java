package homework.w1d2;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import homework.w1d1.Pair;

public class WordCount {
	static List<Mapper> mList = new ArrayList<Mapper>();
	static List<Reducer> rList = new ArrayList<Reducer>();

	public static void main(String[] args) {
		// System.out.println(System.getProperty("user.dir"));

		for (int i = 0; i < 5; i++) {
			rList.add(new Reducer());
		}
		Mapper mapper = null;
		File file = new File(System.getProperty("user.dir") + "\\src\\homework\\resources\\testData");
		int i = 0;
		String key;
		try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8.name())) {
			while (sc.hasNextLine()) {
				mapper = new Mapper(sc.nextLine());
				System.out.println("Mapper " + (i++) + " output");
				for (Pair p : mapper.getList()) {
					System.out.println(p);
					key = p.getKey();
					rList.get(getPartition(key)).reduce(p);
				}
				mList.add(mapper);
			}
			i = 0;
			for (Reducer r : rList) {
				System.out.println("Reducer " + (i++) + " input");
				r.printRInput();
				System.out.println("Reducer " + (i++) + " output");
				r.print();
			}
		} catch (FileNotFoundException e) {

		}
	}

	public static int getPartition(String key) {
		return Math.abs((int) key.hashCode() % rList.size());
	}

}
