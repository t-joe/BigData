package homework.w1d2.partA;

import java.util.ArrayList;
import java.util.List;
import homework.w1d1.Pair;

public class Reducer {
	//List<PairByPair> iList;
	List<GroupByPair> list;
	List<Pair> reducedList;

	public Reducer() {
		//iList = new ArrayList<Pair>();
		list = new ArrayList<GroupByPair>();
		reducedList = new ArrayList<Pair>();
	}

	public void reduce(Pair p) {
		GroupByPair gbp;
		Pair tmpP;
		int sum = 0;
		//iList.add(new PairByPair(p, mapperName));
		gbp = new GroupByPair(p.getKey(), p.getValue());
		if (!list.contains(gbp))
			list.add(gbp);
		else {
			list.get(list.indexOf(gbp)).add(p.getValue());
		}
		for (GroupByPair g : list) {
			sum = 0;
			for (int i : g.getValues())
				sum += i;
			tmpP = new Pair(g.getKey(), sum);
			if (reducedList.contains(tmpP))
				reducedList.get(reducedList.indexOf(tmpP)).setValue(sum);
			else
				reducedList.add(tmpP);
		}
	}

//	public void printParams(String mapperName) {
//		for (PairByPair p : iList)
//			if (mapperName != null) {
//				if (p.getMapperName().equalsIgnoreCase(mapperName))
//					System.out.println(p.getPair());
//			} else
//				System.out.println(p.getPair());
//	}

	public void printRInput() {
		for (GroupByPair g : list)
			System.out.println(g);
	}

	public void print() {
		for (Pair p : reducedList)
			System.out.println(p);
	}

	public List<Pair> getList() {
		return this.reducedList;
	}
}
