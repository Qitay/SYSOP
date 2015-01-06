package procesor;

import java.util.List;
import java.util.ArrayList;

import cpumanagement.proc;


public class zawiadowca 
{
	Boolean whichqs[] = new Boolean[32];
	
	ArrayList<ArrayList<proc>> qs = new ArrayList<ArrayList<proc>>(7);
	ArrayList<proc> jeden = new ArrayList<proc>(3);
	ArrayList<proc> dwa = new ArrayList<proc>(3);
	ArrayList<proc> trzy = new ArrayList<proc>(3);
	ArrayList<proc> cztery = new ArrayList<proc>(3);
	ArrayList<proc> piec = new ArrayList<proc>(3);
	ArrayList<proc> szesc = new ArrayList<proc>(3);
	ArrayList<proc> siedem = new ArrayList<proc>(3);
	ArrayList<proc> osiem = new ArrayList<proc>(3);
	
	proc wybrany;
	
	zawiadowca()
	{
		qs.add(0,jeden);
		qs.add(1,dwa);
		qs.add(2,trzy);
		qs.add(3,cztery);
		qs.add(4,piec);
		qs.add(5,szesc);
		qs.add(6,siedem);
		qs.add(7,osiem);
		
		proc wybrany;
	}
	
	void wybieranie_procesu()
	{
		//proc wybrany;
		int a = qs.indexOf(wybrany);
		przeliczanie_procesu(wybrany);
		qs.remove(a);
	}
	
	
	void przeliczanie_procesu(proc proces)
	{
		int lr=proces.licznikRozkazow;
		
		while(proces.stan = running)
		{
			interpreter_rozkazow.Run();
			proces.licznikRozkazow++;
			proces.cpu++;
			if (proces.licznikRozkazow-lr<=4)
			{
				proces.stan = waiting;
				dodaj_proces(proces);
			}
		}
		for (ArrayList<proc> listy : qs)
		{
			for (proc procesy : listy)
			{
				procesy.cpu= procesy.cpu/2;
				qs.remove(procesy);
				dodaj_proces(procesy);
			}
		}
		wybieranie_procesu();
	}
	
	
	void dodaj_proces(proc proces)
	{
		int a = proces.priorytet/4; 
		if (a <=4) 
		{
			jeden.add(proces);
			for (int i=0; i<=3; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=8) 
		{
			dwa.add(proces);
			for (int i=4; i<=7; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=12) 
		{
			trzy.add(proces);
			for (int i=8; i<=11; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=16) 
		{
			cztery.add(proces);
			for (int i=12; i<=15; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=20) 
		{
			piec.add(proces);
			for (int i=16; i<=19; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=24) 
		{
			szesc.add(proces);
			for (int i=20; i<=23; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=28) 
		{
			siedem.add(proces);
			for (int i=24; i<=27; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
		else if (proces.priorytet/4 <=32) 
		{
			osiem.add(proces);
			for (int i=28; i<=31; i++)
			{
				if (whichqs[a] = false) whichqs[a] = true;
				else a++;
			}
		}
	}
}
