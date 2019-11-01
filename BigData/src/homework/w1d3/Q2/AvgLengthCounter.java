package homework.w1d3.Q2;

import java.util.ArrayList;
import java.util.List;

public class AvgLengthCounter {

	List<Mapper> mappers;
	List<Reducer> reducers;
	List<List<Pair>> mapperToReducerPairList = new ArrayList<List<Pair>>();
	List<List<List<Pair>>> mapperToReducerOutput = new ArrayList<List<List<Pair>>>();
	public AvgLengthCounter()
	{
		mappers = new ArrayList<Mapper>();
		reducers = new ArrayList<Reducer>();
		
		mappers.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\w1d3Mapper0"));
		mappers.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\w1d3Mapper1"));
		mappers.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\w1d3Mapper2"));
		mappers.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\w1d3Mapper3"));

		for (int i = 0; i < 3; i++) {
			reducers.add(new Reducer());
			mapperToReducerPairList.add(new ArrayList<Pair>());
		}
	}
	public static void main(String[] args) {
		AvgLengthCounter alc = new AvgLengthCounter();
		int i = 0, j = 0;
		int idx;
		for(Mapper m: alc.mappers)
		{
			System.out.println("Mapper"+ i + " output");
			for(Pair p: m.map())
			{
				System.out.println(p);
				idx = alc.getPartition(p.getKey());
				alc.reducers.get(idx).reduce(p);
				alc.mapperToReducerPairList.get(idx).add(p);
			}
			alc.mapperToReducerOutput.add(alc.mapperToReducerPairList);
			i++;
			alc.mapperToReducerPairList = new ArrayList<List<Pair>>();
			for (int l = 0; l < 3; l++) {
				alc.mapperToReducerPairList.add(new ArrayList<Pair>());
			}
		}
		i = 0;
		for (List<List<Pair>> mppp : alc.mapperToReducerOutput) {
			j = 0;
			for (List<Pair> lp : mppp) {
				System.out.println("Pairs sent from Mapper" + i + " to Reducer " + j++);
				for (Pair p1 : lp)
					System.out.println(p1);
			}
			i++;
		}

		i = 0;
		for (Reducer r : alc.reducers) {
			System.out.println("Reducer" + i++ + " input");
			r.printRInput();
		}
		i = 0;
		for (Reducer r : alc.reducers) {
			System.out.println("Reducer" + i++ + " output");
			r.print();
		}
	}
	
	public int getPartition(String key) {
		return Math.abs((int) key.hashCode()) % reducers.size();
	}

}
