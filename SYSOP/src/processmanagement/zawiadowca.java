package processmanagement;
//package procesor;


import java.util.ArrayList;

import memory.Pamiec;




public class zawiadowca 
{
	ProcesorManagement PrM;
	
	Boolean whichqs[] = new Boolean[32];
	
	ArrayList<ArrayList<proc>> qs = new ArrayList<ArrayList<proc>>(7);
	ArrayList<proc> jeden = new ArrayList<proc>(3); //czemu ta lista ma d∏ugoÊç 4? a jak b´dzie wi´cej procesów?
	ArrayList<proc> dwa = new ArrayList<proc>(3);
	ArrayList<proc> trzy = new ArrayList<proc>(3);
	ArrayList<proc> cztery = new ArrayList<proc>(3);
	ArrayList<proc> piec = new ArrayList<proc>(3);
	ArrayList<proc> szesc = new ArrayList<proc>(3);
	ArrayList<proc> siedem = new ArrayList<proc>(3);
	ArrayList<proc> osiem = new ArrayList<proc>(3);
	
	proc wybrany;
	Pamiec pam;
	
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
	}
	
	void usuwanie_martwych()
	{
		for (ArrayList<proc> listy : qs)
		{
			for (proc procesy : listy)
			{
				if (procesy.s == stan.ZAKONCZONY)
				{
					System.out.println("Usunieto proces "+procesy.uid+" z listy i pamieci.");
					listy.remove(procesy);
					pam.USUN(procesy.addr);					
				}
			}
		}
	}
	
	void wybieranie_procesu()
	{
		wybrany = null;
		for (ArrayList<proc> listy : qs)
		{
			for (proc procesy : listy)
			{
				if (procesy != null){wybrany = procesy; procesy.s = stan.AKTYWNY; break;}
			}
		}
		if (wybrany == null) { System.out.println("Nie wybrano procesu. Lista procesow gotowych jest pusta");}
		else przeliczanie_procesu(wybrany);
	}
	
	
	void przeliczanie_procesu(proc proces)
	{
		int lr=proces.licznikRozkazow;
		if (proces.s != stan.AKTYWNY) System.out.println("Nieaktywny proces nie moze byc przeliczany!");
		else
		{
			while(proces.s == stan.AKTYWNY)
			{
				System.out.println("Przekazanie procesu "+proces.uid+" do interpretera.");
				procesor.interpreter_rozkazow1.Run(proces.addr, proces.bajt);
				proces.licznikRozkazow++;
				proces.cpu++;
				if (proces.licznikRozkazow-lr<=4)
				{
					proces.s = stan.OCZEKUJACY;
					dodaj_proces(proces);
				}
			}
			for (ArrayList<proc> listy : qs)
			{
				for (proc procesy : listy)
				{
					procesy.cpu= procesy.cpu/2;
					dodaj_proces(procesy);
					qs.remove(procesy);			
				}
			}
		}
		wybieranie_procesu();
	}
	
	
	void dodaj_proces(proc proces)
	{
		if (proces.s != stan.GOTOWY || proces.s != stan.OCZEKUJACY) System.out.println("Niegotowy proces nie moze byc dodany do listy procesow gotowych!");
		else
		{
			int a = proces.priorytet/4; 
			int i;
			if (a <=4) 
			{
				jeden.add(proces);
				for ( i=0; i<=3; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej jeden na miejsce"+i);
			}
			else if (a <=8) 
			{
				dwa.add(proces);
				for ( i=4; i<=7; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej dwa na miejsce"+i);
			}
			else if (a <=12) 
			{
				trzy.add(proces);
				for ( i=8; i<=11; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej trzy na miejsce"+i);
			}
			else if (a <=16) 
			{
				cztery.add(proces);
				for ( i=12; i<=15; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej cztery na miejsce"+i);
			}
			else if (a <=20) 
			{
				piec.add(proces);
				for ( i=16; i<=19; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej piec na miejsce"+i);
			}
			else if (a <=24) 
			{
				szesc.add(proces);
				for ( i=20; i<=23; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej szesc na miejsce"+i);
			}
			else if (a <=28) 
			{
				siedem.add(proces);
				for ( i=24; i<=27; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej siedem na miejsce"+i);
			}
			else if (a <=32) 
			{
				osiem.add(proces);
				for ( i=28; i<=31; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
				System.out.println("Proces "+proces.uid + " z priorytetem "+a+" zostal dodany do grupy priorytetowej osiem na miejsce"+i);
			}
			else
			{
				System.out.println("Bledny priorytet. Dodano do ostatniej grupy priorytetowej.");
				osiem.add(proces);
				for ( i=28; i<=31; i++)
				{
					if (whichqs[i] = false) {whichqs[i] = true; proces.miejsce_whichqs = i;}
				}
			}
		}
	}
}
