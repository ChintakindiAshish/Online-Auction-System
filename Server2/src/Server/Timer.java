package Server;

public class Timer {

	public static long Total_Time=-1;
	public static boolean flag2=true;
	public static void StartTimer(int t)
	{
		Total_Time=System.currentTimeMillis()+t*1000;
		flag2=false;
	}
	public static boolean checktime()
	{
		if(System.currentTimeMillis()<Total_Time)
			return true;
		else
			return false;
	}
}
