package function;

public class StuInfoVector {
	public int num;
	public String name;
	public int midScore;
	public int finScore;
	public int homeScore;
	public int total;
	public double average;
	public String grade;
	
	public StuInfoVector(int num,String name,int midScore,int finScore,int homeScore, int total,double average,String grade) {
		this.num = num;
		this.name = name;
		this.midScore = midScore;
		this.finScore = finScore;
		this.homeScore = homeScore;
		this.total = total;
		this.average = average;
		this.grade = grade;
	}
	public int GetNum() {
		return num;
	}
	public String GetName() {
		return name;
	}
	public double GetAverage() {
		return average;
	}
	
	public String nu1;
	public String nu2;
	public double midAver;
	public double finAver;
	public double homeAver;
	public double totalAver;
	public double aver;
	public String nu3;
	
	public StuInfoVector(String nu1, String nu2, double midAver, double finAver, double homeAver, double totalAver,
			double aver, String nu3) {
		this.nu1 = nu1;
		this.nu2 = nu2;
		this.midAver = midAver;
		this.finAver = finAver;
		this.homeAver = homeAver;
		this.totalAver = totalAver;
		this.aver = aver;
		this.nu3 = nu3;
	}
}
