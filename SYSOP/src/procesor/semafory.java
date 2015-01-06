package procesor;

import java.util.ArrayList;

import cpumanagement.proc;

public class semafory 
{
	int wartosc;
	public ArrayList<proc> semafory = new ArrayList<proc>();
	
	semafory()
	{
		wartosc = 0;
	}
	
	semafory(int w)
	{
		wartosc = w;
	}
	
	public void XP(semafory S, int i)
	{
		S.wartosc=S.wartosc+i;
	}
	
	public void XV(semafory S, int i)
	{
		while (S.wartosc>=i)
		{
			S.wartosc=S.wartosc-i;
		}
	}
}
