package homework.w1d3.Q2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Reducer {
	Map<String, List<NumberPair>> list;
	List<homework.w1d1.Pair> reducedList;

	public Reducer() {
		list = new HashMap<String, List<NumberPair>>();
		reducedList = new ArrayList<homework.w1d1.Pair>();
	}

	public void reduce(Pair p) {
		List<NumberPair> tmp;
		if (list.get(p.getKey()) == null) {
			tmp = new ArrayList<NumberPair>();
			tmp.add(p.getValue());
			list.put(p.getKey(), tmp);
		} else {
			tmp = list.get(p.getKey());
			tmp.add(p.getValue());
			list.put(p.getKey(), tmp);
		}
		int totalLenght;
		int totalCount;
		homework.w1d1.Pair tmpPair;
		for (Entry<String, List<NumberPair>> e : list.entrySet()) {
			totalLenght = 0;
			totalCount = 0;
			for (NumberPair n : e.getValue()) {
				totalLenght += n.getKey();
				totalCount += n.getVal();
			}
			tmpPair = new homework.w1d1.Pair(e.getKey(), totalLenght / totalCount);
			if (reducedList.contains(tmpPair))
				reducedList.get(reducedList.indexOf(tmpPair)).setValue(totalLenght / totalCount);
			else
				reducedList.add(tmpPair);
		}
	}

	public void printRInput() {
		for (Entry<String, List<NumberPair>> e : list.entrySet()) {
			System.out.print("(" + e.getKey() + "[");
			for (int i = 0; i < e.getValue().size() - 2; i++)
				System.out.print(e.getValue().get(i) + ", ");
			System.out.print(e.getValue().get(e.getValue().size() - 1));
			System.out.println("])");
		}
	}

	public void print() {
		for (homework.w1d1.Pair p : reducedList)
			System.out.println(p);
	}

	public List<homework.w1d1.Pair> getList() {
		return this.reducedList;
	}
}
