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
		nowy.uid = 0;//superu�ytkownik root ma UID 0,
		nowy.nice = 0; //zale�ne od u�ytkownika
		//Domyślnie warto� nice ustawiana jest na 20 dla u�ytkownika domy�lnego?
		nowy.cpu =0; //miara wykorzystaina proc
		nowy.jestInitem = true;
		nowy.cpu_stan =new Object[6];
		nowy.pidyPotomkow=new ArrayList<Integer>();
		//nowy.tablicaDeskryptorów = null;
		nowy.ExitStatus=0;
		nowy.addr=PsM.MM.pam.ZAPIS(" ");
		nowy.ChildExitStatus=0;
		nowy.registerReturnValue=0;//nie dotyczy po prostu domyślna wartość ktora nic nie znaczy
		nowy.licznikRozkazow=0;//nie dotyczy init nie ma kodu programu
		nowy.fdtab = new ArrayList<Integer>();

		
		nowy.s=stan.GOTOWY;
		ListaProcesow.add(nowy);
	}
	
	public void fork(proc parent)
	{
		if(parent.s==stan.ZAKONCZONY)
		{
			
		System.out.print("Nie mozna wywolac funkcji fork na zakonczonym procesie!");	
		
		}
		else
		{
		System.out.print("Wywo�ano funkcj� fork.");	
		
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
		nowy.uid=PsM.UM.usr.activeUid;
		
		
		if(nowy.uid==0)
		{
			
			nowy.nice=0;
		}
		else
		{
			nowy.nice=10;
			
			
		}
		parent.pidyPotomkow.add(nowy.pid);
		nowy.pidyPotomkow=new ArrayList<Integer>();
		
		
		
		nowy.cpu=parent.cpu;
		
		//proces potomny wykonuje się w przestrzeni adresowej będącej kopią przestrzeni procesu rodzicielskiego
		
		nowy.addr=PsM.MM.pam.KLONUJ(parent.addr); //w arg parent.addr 
	 
		
		//Funkcja fork tworzy deskryptor nowego procesu oraz kopię segmentu danych i stosu procesu macierzystego. 
		
		nowy.registerReturnValue=0;
		parent.registerReturnValue=Global.mpid;
		nowy.jestInitem = false;
		nowy.licznikRozkazow=parent.licznikRozkazow;
		nowy.s=stan.GOTOWY;
		
		nowy.cpu_stan =new Object[6];
		nowy.ChildExitStatus=0;
		nowy.ExitStatus=0;
		ListaProcesow.add(nowy);
		nowy.fdtab= new ArrayList<Integer>();
		nowy.fdtab.addAll(parent.fdtab);
		
		//dopisać do listy procesów gotowych
		//ArrayList<proc> Ready_list = new ArrayList<proc>();
		//Ready_list.add(nowy);
		System.out.println(" Stworzono proces o nr PID "+nowy.pid);	
		}
		
		
	}
	public void fork()
	{
	//MUSZĘ MIEĆ DOSTĘP DO ZMIENNEJ TYPU proc AKTUALNEGO PROCESU KTÓRĄ STWORZY OSOBA ZAJMUJĄCA SIĘ PROCESOREM
	//jest to zmienna "aktualny" w klasie "procesor"
		//this.fork(PsM.PrM.procesor.aktualny);
		
	}

	public void exec(proc procesZm)
	{
		if(procesZm.s==stan.ZAKONCZONY)
		{
			
		System.out.print("Nie mozna wywolac funkcji execve na zakonczonym procesie!");	
		
		}
		else
		{	
		System.out.println("Wywo�ano funkcj� exec");
		
		//sprawdzanie czy istnieje plik
		if(PsM.FM.p.wezly.isEmpty())
		{
			
			System.out.println("Nie stworzono jeszcze pliku ktory mozna wczytac!");	
			
			
		}
		else
		{
		String tmp = PsM.FM.p.wezly.get(0).read_s(PsM.FM.p.dysktwardy, PsM.FM.p.wezly);
		
		//przekazanie stringa do pam otrzymanie adresu gdzie został zapisany ten string:

		
        int adr=  PsM.MM.pam.NADPISZ(tmp,procesZm.addr);
		
		procesZm.addr=adr; //zmienia addr w oryginale bo przekazywane przez referencje
		procesZm.licznikRozkazow=0;
		 
		System.out.println("Wczytano z pliku do pami�ci nast�puj�ce rozkazy:");
		System.out.println(tmp);
		System.out.println(procesZm.addr);
		}
	/*
		
		try {
			
			
			//  "/Users/Czerniawska/Desktop/rozkazy.txt"
			
			String tmp = "";
			String tmp2 = "";
			
			FileReader fr = new FileReader(path); 
			BufferedReader br = new BufferedReader(fr);
			
			
			
			while ((tmp=br.readLine()) != null) {
				tmp2=tmp2+tmp+"|";
			}
			br.close();
				
			
			System.out.println("Wczytano z pliku do pami�ci nast�puj�ce rozkazy:");
	        System.out.println(tmp2); //sprawdzanie czy wszystko wczytało

			
			
			
			//przekazanie stringa do pam otrzymanie adresu gdzie został zapisany ten string:

			
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
		
		*/
		}
	}
	public void wait2(proc p)
	{
		System.out.println("Wywo�ano funkcj� wait");	
		wait1(p);
	}
	
	public void wait1(proc p)
	{
		if(p.s==stan.ZAKONCZONY)
		{
			
		System.out.print("Nie mozna wywolac funkcji wait na zakonczonym procesie!");	
		
		}
		else
		{
		//System.out.println("Wywo�ano funkcj� wait");	
		
		p.s=stan.OCZEKUJACY;
		//trafia na kolejk� oczekuj�cych czy co??
		
		//Je�li proces nie mia� dzieci to od razu wraca do stanu ready i w registerReturnValue -1
		if(p.pidyPotomkow.isEmpty())
		{
			p.s=stan.GOTOWY;
			p.registerReturnValue=-1;
		}
		else
		{
		//sprawdza stany dzieci i szuka jakiego� terminated
		//Je�li znajdzie to wpisuje sobie jego kod exitu i w registerReturnValue pid tego dziecka 
		//a status zmienia znowu na READY
		//usuwa terminated dziecko z listy proces�w
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
	}
	public void exit(proc p)
	{

		if(p.s==stan.ZAKONCZONY)
		{
			
		System.out.print("Nie mozna wywolac funkcji exit na ju� zakonczonym procesie!");	
		
		}
		else
		{
		
		System.out.println("Wywo�ano funkcj� exit");	
		
			if(p.pid==1)
			{
				System.out.println("Nie zabito procesu init. Proces init b�dzie zabity przy zako�czeniu shella.");	
			}
			else
			{
				//zapisuje (do pcb?)argument exita
				p.ExitStatus=1;
				//zamkni�cie deskryptor�w plik�w danego procesu
		
		
		
				PsM.SM.pipe.closeall(p.pid);
		
		
		
		//zwolnienie pami�ci
		PsM.MM.pam.USUN(p.addr);
		
		p.addr=-1;
		
		
		//na li�cie gotowych te� sprawdzi� je�li uda si� po��czy� projekt!
		//zmienienie ppid potomk�w zabijanego procesu na 1
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
		//dodanie nowych adoptowanych potomk�w do listy potomk�w procesu init
		for(int i =0;i<p.pidyPotomkow.size();i++)
		{
			
		ListaProcesow.get(0).pidyPotomkow.add(p.pidyPotomkow.get(i));
		}
		
		p.pidyPotomkow.clear();
		
		p.s=stan.ZAKONCZONY;	
		
		
		//sprawdza czy rodzic ju� czeka�( czy status rodzica by� waiting) 
		//i je�li tak to wywo�uje jeszcze raz funkcj� wait na rodzicu
		
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
}
