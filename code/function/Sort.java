package function;

import java.util.Comparator;

public class Sort {
	
	public static class averageSort implements Comparator<StuInfoVector>{
		@Override
		public int compare(StuInfoVector average1, StuInfoVector average2) {
			return  average1.GetAverage() > average2.GetAverage()? -1 : average1.GetAverage() < average2.GetAverage()? 1:0;
		}
		
	}
	public static class numberSort implements Comparator<StuInfoVector>{
		@Override
		public int compare(StuInfoVector num1, StuInfoVector num2) {
			return num1.GetNum() < num2.GetNum()? -1 : num1.GetNum() > num2.GetNum()? 1:0;
		}
	}
	
	public static class nameSort implements Comparator<StuInfoVector>{
		@Override
		public int compare(StuInfoVector name1, StuInfoVector name2) {
			return name1.GetName().compareTo(name2.GetName());
		}
	}
	

}
