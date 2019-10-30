package homework.w1d2.partB;

import java.util.ArrayList;
import java.util.List;

import homework.w1d1.Pair;

public class Reducer {
	List<GroupByPair> list = new ArrayList<GroupByPair>();
	List<Pair> reducedList = new ArrayList<Pair>();

	public Reducer() {
	}

	public void reduce(Pair p) {
		GroupByPair gbp;
		// List<GroupByPair> plist = iList.stream().collect(Collectors.groupingBy(x ->
		// new GroupByPair(x.getKey(), x.getValue())));
		gbp = new GroupByPair(p.getKey(), p.getValue());
		if (!list.contains(gbp))
			list.add(gbp);
		else {
			list.get(list.indexOf(gbp)).add(p.getValue());
		}
	}

	public void printRInput() {
		for (GroupByPair g : list)
			System.out.println(g);
	}

	public void print() {
		for (GroupByPair g : list) {
			reducedList.add(new Pair(g.getKey(), g.getValues().size()));
		}
		for (Pair p : reducedList)
			System.out.println(p);
	}

	public List<Pair> getList() {
		return this.reducedList;
	}
}
