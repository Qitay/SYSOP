package cpumanagement;
import java.util.ArrayList;
public class Klasa {

	ProcessMaganement PsM;
	
	public void fork(proc parent)
	{
		proc nowy = new proc();
		nowy.stan="nowy";
		
		if (Global.mpid<0)
		{
			Global.mpid = 0;
			
			
		}
		Global.mpid++;
		//zapytac sie bartoszka jak to ma byÄ‡ z tym pidem
		nowy.pid=Global.mpid;
		nowy.ppid=parent.pid;
		nowy.licznikRozkazow=parent.licznikRozkazow;
		
		
		//proces potomny wykonuje siÄ™ w przestrzeni adresowej bÄ™dÄ…cej kopiÄ… przestrzeni procesu rodzicielskiego
		//dodaÄ‡ alokowanie pamiÄ™ci !!!
		
		nowy.registerReturnValue=0;
		parent.registerReturnValue=Global.mpid;
		//tu jeszcze bÄ™dÄ… rzeczy!!
		
		nowy.stan="gotowy";
		
		//dopisaÄ‡ do listy procesĂłw gotowych
		ArrayList<proc> Ready_list = new ArrayList<proc>();
		Ready_list.add(nowy);
		
	}
	public void fork()
	{
	//MUSZÄ� MIEÄ† DOSTÄ�P DO ZMIENNEJ TYPU proc AKTUALNEGO PROCESU KTĂ“RÄ„ STWORZY OSOBA ZAJMUJÄ„CA SIÄ� PROCESOREM
	//jest to zmienna "aktualny" w klasie "procesor"
		this.fork(PsM.PrM.procesor.aktualny);
		
	}

	
	
	
	
}
