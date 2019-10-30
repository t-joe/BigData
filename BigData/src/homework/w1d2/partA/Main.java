package homework.w1d2.partA;

import java.util.List;

import homework.w1d1.Pair;

public class Main {

	public static void main(String[] args) {
		Mapper mapper = new Mapper();
		Reducer reducer =new Reducer();
		List<Pair> mList = mapper.map(System.getProperty("user.dir") + "\\src\\homework\\resources\\testData");
		System.out.println("Mapper output\n");
		for(Pair p: mList)
		{
			System.out.println(p);
			reducer.reduce(p);
		}
		System.out.println("\nReducer input\n");
		reducer.printRInput();
		System.out.println("\nReducer output\n");
		reducer.print();
	}

}
