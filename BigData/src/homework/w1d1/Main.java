package homework.w1d1;


public class Main {
	public static void main(String[] str)
	{
		Mapper map = new Mapper("C:\\Users\\Tuguldur\\Documents\\Big Data\\testData.txt");
		map.getList().forEach(System.out::println);
	}
}
