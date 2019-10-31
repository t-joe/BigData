package homework.w1d2.partB;

import java.util.ArrayList;
import java.util.List;
import homework.w1d1.Mapper;
import homework.w1d1.Pair;
import homework.w1d2.partA.Reducer;

public class WordCount {
	List<Mapper> mList = new ArrayList<Mapper>();
	List<Reducer> rList = new ArrayList<Reducer>();

	public WordCount()
	{
		mList.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\Mapper0"));
		mList.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\Mapper1"));
		mList.add(new Mapper(System.getProperty("user.dir") + "\\src\\homework\\resources\\Mapper2"));
		
		for (int i = 0; i < 5; i++) {
			rList.add(new Reducer());
		}
	}
	public static void main(String[] args) {
		int i = 0, j = 0;
		WordCount wc = new WordCount();
		for(Mapper m: wc.mList)
		{
			j = 0;
			System.out.println("Mapper"+ i + " output");
			for(Pair p: m.getList())
			{
				System.out.println(p);
				wc.rList.get(wc.getPartition(p.getKey())).reduce(p);
			}
			for(Reducer r: wc.rList)
			{
				System.out.println("Pairs sent from Mapper"+i+ " to Reducer " + j);
				r.printParams();
				j++;
			}
			i++;
		}
		i=0;
		for(Reducer r: wc.rList)
		{
			System.out.println("Reducer"+ i++ + " input");
			r.printRInput();
		}
		i = 0;
		for(Reducer r: wc.rList)
		{
			System.out.println("Reducer"+ i++ + " output");
			r.print();
		}
	}

	public int getPartition(String key) {
		return Math.abs((int) key.hashCode() % rList.size());
	}

}
