package processmanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



//import java.util.ArrayList;
public class Klasa {

	ProcessMaganement PsM;
	
	//public static ArrayList<proc> ListaProcesow = new ArrayList<proc>();
	ArrayList<proc> ListaProcesow = new ArrayList<proc>();
	public void init()
	{
		System.out.println("Stworzono proces init");
		proc nowy = new proc();
		nowy.s = stan.NOWY;
		nowy.pid = Global.mpid;
		nowy.ppid = 0;
		nowy.uid = 0;//superu˝ytkownik root ma UID 0,
		nowy.nice = 0; //zale˝ne od u˝ytkownika
		//Domy≈õlnie wartoÊç nice ustawiana jest na 20 dla u˝ytkownika domyÊlnego?
		nowy.cpu =0; //miara wykorzystaina proc
		nowy.jestInitem = true;
		nowy.cpu_stan =new Object[6];
		nowy.pidyPotomkow=new ArrayList<Integer>();
		//nowy.tablicaDeskryptor√≥w = null;
		nowy.ExitStatus=0;
		nowy.addr=PsM.MM.pam.ZAPIS(" ");
		nowy.ChildExitStatus=0;
		nowy.registerReturnValue=0;//nie dotyczy po prostu domy≈õlna warto≈õƒá ktora nic nie znaczy
		nowy.licznikRozkazow=0;//nie dotyczy init nie ma kodu programu
		
		
		nowy.s=stan.GOTOWY;
		ListaProcesow.add(nowy);
	}
	
	public void fork(proc parent)
	{
		System.out.print("Wywo∏ano funkcj´ fork.");	
		
		proc nowy = new proc();
	
		nowy.s=stan.NOWY;
		/*
		if (Global.mpid<0)
		{
			Global.mpid = 0;
	
		}
		*/
		Global.mpid++;
		nowy.pid=Global.mpid;
		nowy.ppid=parent.pid;
		//nowy.tablicaDeskryptor√≥w=parent.tablicaDeskryptor√≥w;
		parent.pidyPotomkow.add(nowy.pid);
		nowy.pidyPotomkow=new ArrayList<Integer>();
		
		nowy.cpu=parent.cpu;
		
		//proces potomny wykonuje siƒô w przestrzeni adresowej bƒôdƒÖcej kopiƒÖ przestrzeni procesu rodzicielskiego
		
		nowy.addr=PsM.MM.pam.KLONUJ(parent.addr); //w arg parent.addr 
	 
		
		//Funkcja fork tworzy deskryptor nowego procesu oraz kopiƒô segmentu danych i stosu procesu macierzystego. 
		
		nowy.registerReturnValue=0;
		parent.registerReturnValue=Global.mpid;
		nowy.jestInitem = false;
		nowy.licznikRozkazow=parent.licznikRozkazow;
		nowy.s=stan.GOTOWY;
		
		nowy.cpu_stan =new Object[6];
		nowy.ChildExitStatus=0;
		nowy.ExitStatus=0;
		ListaProcesow.add(nowy);
		
		//dopisaƒá do listy proces√≥w gotowych
		//ArrayList<proc> Ready_list = new ArrayList<proc>();
		//Ready_list.add(nowy);
		System.out.println(" Stworzono proces o nr PID "+nowy.pid);	
	}
	public void fork()
	{
	//MUSZƒò MIEƒÜ DOSTƒòP DO ZMIENNEJ TYPU proc AKTUALNEGO PROCESU KT√ìRƒÑ STWORZY OSOBA ZAJMUJƒÑCA SIƒò PROCESOREM
	//jest to zmienna "aktualny" w klasie "procesor"
		//this.fork(PsM.PrM.procesor.aktualny);
		
	}

	public void exec(proc procesZm,String path)
	{
		System.out.println("Wywo∏ano funkcj´ exec");	
		try {
			
			
			//  "/Users/Czerniawska/Desktop/rozkazy.txt"
			
			String tmp = "";
			String tmp2 = "";
			
			FileReader fr = new FileReader(path); 
			BufferedReader br = new BufferedReader(fr);
			/*
			while (true){
			String a = br.readLine();
			if(a!=null)
				break;
				tmp+=a;
			}
			br.close();
			*/
			
			
			while ((tmp=br.readLine()) != null) {
				tmp2=tmp2+tmp+"|";
			}
			br.close();
				
			
			System.out.println("Wczytano z pliku do pami´ci nast´pujàce rozkazy:");
	        System.out.println(tmp2); //sprawdzanie czy wszystko wczyta≈Ço

			
			
			
			//przekazanie stringa do pam otrzymanie adresu gdzie zosta≈Ç zapisany ten string:

			
	        int adr=  PsM.MM.pam.NADPISZ(tmp2,procesZm.addr);
			
			procesZm.addr=adr; //zmienia addr w oryginale bo przekazywane przez referencje
			procesZm.licznikRozkazow=0;
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void wait2(proc p)
	{
		System.out.println("Wywo∏ano funkcj´ wait");	
		wait1(p);
	}
	
	public void wait1(proc p)
	{
		//System.out.println("Wywo∏ano funkcj´ wait");	
		
		p.s=stan.OCZEKUJACY;
		//trafia na kolejk´ oczekujàcych czy co??
		
		//JeÊli proces nie mia∏ dzieci to od razu wraca do stanu ready i w registerReturnValue -1
		if(p.pidyPotomkow.isEmpty())
		{
			p.s=stan.GOTOWY;
			p.registerReturnValue=-1;
		}
		else
		{
		//sprawdza stany dzieci i szuka jakiegoÊ terminated
		//JeÊli znajdzie to wpisuje sobie jego kod exitu i w registerReturnValue pid tego dziecka 
		//a status zmienia znowu na READY
		//usuwa terminated dziecko z listy procesów
		//usuwa dziecko z pidyPotomkow
			petla:
				for(int i=0;i<p.pidyPotomkow.size();i++)
				{
					for(int j=0;j<ListaProcesow.size();j++)
					{
						if(ListaProcesow.get(j).pid==p.pidyPotomkow.get(i))
						{
							if(ListaProcesow.get(j).s==stan.ZAKONCZONY) //equals??
							{
								p.ChildExitStatus=ListaProcesow.get(j).ExitStatus;
								p.registerReturnValue=ListaProcesow.get(j).pid;
								ListaProcesow.remove(j);
								p.pidyPotomkow.remove(i);
								p.s=stan.GOTOWY;
								break petla;
							}
				
						}
					}
				}
		}		
	}
	public void exit(proc p)
	{
		System.out.println("Wywo∏ano funkcj´ exit");	
		
		if(p.pid==1)
		{
		System.out.println("Nie zabito procesu init. Proces init b´dzie zabity przy zakoƒczeniu shella.");	
		}
		else
		{
		//zapisuje (do pcb?)argument exita
		p.ExitStatus=1;
		//zamkni´cie deskryptorów plików danego procesu
	
		//zwolnienie pami´ci
		PsM.MM.pam.USUN(p.addr);
		
		
		//na liÊcie gotowych te˝ sprawdziç jeÊli uda si´ po∏àczyç projekt!
		//zmienienie ppid potomków zabijanego procesu na 1
		for(int i =0;i<p.pidyPotomkow.size();i++)
		{
			for(int j=0;j<ListaProcesow.size();j++)
			{
				if(ListaProcesow.get(j).pid==p.pidyPotomkow.get(i)) //equals() ??
				{
					ListaProcesow.get(j).ppid=1;
				}
			}
		}
		//dodanie nowych adoptowanych potomków do listy potomków procesu init
		for(int i =0;i<p.pidyPotomkow.size();i++)
		{
			
		ListaProcesow.get(0).pidyPotomkow.add(p.pidyPotomkow.get(i));
		}
		
		p.pidyPotomkow.clear();
		
		p.s=stan.ZAKONCZONY;	
		
		
		//sprawdza czy rodzic ju˝ czeka∏( czy status rodzica by∏ waiting) 
		//i jeÊli tak to wywo∏uje jeszcze raz funkcj´ wait na rodzicu
		
		for(int j=0;j<ListaProcesow.size();j++)
		{
			if(ListaProcesow.get(j).pid==p.ppid)
			{
				if(ListaProcesow.get(j).s==stan.OCZEKUJACY)
				{
				wait1(ListaProcesow.get(j));
				}
			}
			
			
		}
		//the process's parent is sent a SIGCHLD signal. przez pipe???
		
		//The value status is returned to the parent process as the process's exit status,
		//and can be collected using one of the wait family of calls. 
	}
	}
	
}
