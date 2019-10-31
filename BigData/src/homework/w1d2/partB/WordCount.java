package homework.w1d2.partB;

import java.util.ArrayList;
import java.util.List;
import homework.w1d2.partA.Mapper;
import homework.w1d1.Pair;
import homework.w1d2.partA.Reducer;

public class WordCount {
	List<Mapper> mList = new ArrayList<Mapper>();
	List<Reducer> rList = new ArrayList<Reducer>();
	List<List<Pair>> mapperToReducerPairList = new ArrayList<List<Pair>>();
	List<List<List<Pair>>> mapperToReducerOutput = new ArrayList<List<List<Pair>>>();

	public WordCount() {
		mList.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\Mapper0"));
		mList.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\Mapper1"));
		mList.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\Mapper2"));

		for (int i = 0; i < 4; i++) {
			rList.add(new Reducer());
			mapperToReducerPairList.add(new ArrayList<Pair>());
		}
	}

	public static void main(String[] args) {
		int i = 0, j = 0;
		WordCount wc = new WordCount();
		List<Pair> tmpList;
		int idx;
		for (Mapper m : wc.mList) {
			j = 0;
			System.out.println("Mapper" + i + " output");
			tmpList = m.map();
			for (Pair p : tmpList) {
				System.out.println(p);
				idx = wc.getPartition(p.getKey());
				wc.rList.get(idx).reduce(p);
				wc.mapperToReducerPairList.get(idx).add(p);
			}
			wc.mapperToReducerOutput.add(wc.mapperToReducerPairList);
			i++;
			wc.mapperToReducerPairList = new ArrayList<List<Pair>>();
			for (int l = 0; l < 4; l++) {
				wc.mapperToReducerPairList.add(new ArrayList<Pair>());
			}
		}
		i = 0;
		for (List<List<Pair>> mppp : wc.mapperToReducerOutput) {
			j = 0;
			for (List<Pair> lp : mppp) {
				System.out.println("Pairs sent from Mapper" + i + " to Reducer " + j++);
				for (Pair p1 : lp)
					System.out.println(p1);
			}
			i++;
		}

		i = 0;
		for (Reducer r : wc.rList) {
			System.out.println("Reducer" + i++ + " input");
			r.printRInput();
		}
		i = 0;
		for (Reducer r : wc.rList) {
			System.out.println("Reducer" + i++ + " output");
			r.print();
		}
	}

	public int getPartition(String key) {
		return Math.abs((int) key.hashCode() % rList.size());
	}

}
