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
	//MUSZƒ? MIEƒÜ DOSTƒ?P DO ZMIENNEJ TYPU proc AKTUALNEGO PROCESU KT√ìRƒÑ STWORZY OSOBA ZAJMUJƒÑCA SIƒ? PROCESOREM
	//jest to zmienna "aktualny" w klasie "procesor"
		//this.fork(PsM.PrM.procesor.aktualny);
		
	}

	public void exec(proc procesZm,String path)
	{
		System.out.println("Wywo∏ano funkcj´ exec");	
		try {
			
			
			//  "/Users/Czerniawska/Desktop/rozkazy.txt"
			
			String tmp = null;
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			/*
			while (true){
			String a = br.readLine();
			if(a!=null)
				break;
				tmp+=a;
			}
			br.close();
			*/
			
			
			while ((br.readLine()) != null) {
				tmp = tmp + br.readLine();
			}
			br.close();
					
			
			System.out.println("Wczytano z pliku nast´puj?ce rozkazy:");
	        System.out.println(tmp); //sprawdzanie czy wszystko wczyta≈Ço

			
			
			
			//przekazanie stringa do pam otrzymanie adresu gdzie zosta≈Ç zapisany ten string:

			int adr =  PsM.MM.pam.NADPISZ(tmp, procesZm.addr);//czy w nowym miejscu czy na miejscu starego kodu programu??
	        
			
			procesZm.addr=adr; //zmienia addr w oryginale bo przekazywane przez referencje
			procesZm.licznikRozkazow=0;
			
			System.out.println("jo≈Ç!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void wait1(proc p)
	{
		p.s=stan.OCZEKUJACY;
		
		
		/*
		 int wait ( int *status );
		w zmiennej "status" jest zwracana informacja o sposobie zako≈Ñczenia dzia≈Çania potomka;
		warto≈õciƒÖ zwracanƒÖ przez wait() jest PID procesu potomnego, kt√≥ry siƒô zako≈Ñczy≈Ç
	    niech: status == HHLL (4 cyfry hex)

	    potomek zako≈Ñczy≈Ç siƒô przez wywo≈Çanie "exit(y)"; wtedy HH=y, LL=0
	    potomek zako≈Ñczy≈Ç siƒô z powodu sygna≈Çu; wtedy HH=0, 7-my bit LL zawiera 1 je≈õli 
	    wygenerowano plik "core", bity 6-0 LL zawierajƒÖ nr sygna≈Çu
		
		*Waiting for any some child to terminate: wait()
		*Blocks until some child terminates
		*Returns the process ID of the child process
		*Or returns -1  no children exist(i.e., already exited)
		*
		*
		*
		*
		*The wait function suspends execution of the current process until a child has exited,
		* or until a signal is delivered whose action is to terminate the current process or to call a 
		* signal handling function. If a child has already exited by the time of the call 
		* (a so-called "zombie" process), 
		*the function returns immediately. Any system resources used by the child are freed. 
		*
		*/
	}
	public void exit(proc p)
	{
		System.out.println("Wywo∏ano funkcj´ exit");	
		//zapisuje (do pcb?)argument exita
		p.ExitStatus=1;
		//zamkni´cie deskryptorów plików danego procesu
	
		//zwolnienie pami´ci
		PsM.MM.pam.USUN(p.addr);
		
		
		//na liÊcie gotowych te˝ sprawdziç jeÊli uda si´ po∏?czyç projekt!
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
			
		//the process's parent is sent a SIGCHLD signal. przez pipe???
		
		//The value status is returned to the parent process as the process's exit status,
		//and can be collected using one of the wait family of calls. 
	}
	
	
}
