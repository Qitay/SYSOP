
import java.io.IOException;
import java.util.Scanner;
public class Projekt {
	
public static void main(String[] args) throws IOException {
	
		ProcessMaganement processM = new ProcessMaganement();
		//ProcesorManagement procesorM = new ProcesorManagement();
		MemoryManagement MemoryM = new MemoryManagement();//----
		FilesManagement FilesM = new FilesManagement();
		UserManagement userM = new UserManagement();
		StreamsManagement streamsM = new StreamsManagement();
		//Pipe pipe = new Pipe();

		int fd[]=new int[2];
		//processM.PrM=procesorM;
		processM.MM=MemoryM;
		processM.FM=FilesM;
		processM.SM=streamsM;
		processM.UM=userM;
		//procesorM.PsM=processM;
		//procesorM.MM=MemoryM;
		
		//MemoryM.PrM=procesorM;
		MemoryM.PsM=processM;//-----
		
		FilesM.PsM=processM;
		
		userM.PsM=processM;
		
		streamsM.PsM=processM;


        System.out.println("Witamy w systemie");
		
        processM.klasa.init();
        userM.usr.login.setRoot();
        
        String odczytano;
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od u≈ºytkownika
       
		do{	
        
        
        do
		{
			if (userM.usr.activeUid == 0)
			{
				Root root = new Root();
				System.out.println("\r\nAktywny uzytkownik: root");
				odczytano = odczyt.nextLine();
		//////////////////////////////////// UZYTKOWNICY/////////////////////////////////////////		
				if(odczytano.equals("USERADD"))
		        {
					System.out.print("Nazwa uzytkownika: "); String u = odczyt.nextLine();
					System.out.print("Haslo: "); String p = odczyt.nextLine();
					userM.root.useradd(u, p);
					System.out.println("Dodano uzytkownika.");
					
		        }
				if(odczytano.equals("USERMODP"))
		        {
		        	
		        	System.out.print("Nazwa uzytkownika: "); String us = odczyt.nextLine();
					System.out.print("Haslo: "); String pa = odczyt.nextLine();
					userM.root.usermod_passwd(us, pa);
					
		        	
		        }
		        if(odczytano.equals("USERDEL"))
		        {
		        	
		        	System.out.print("Nazwa uzytkownika: "); String usr = odczyt.nextLine();
		        	userM.root.userdel(usr);
		        	
		        }
		        if(odczytano.equals("GROUPADD"))
		        {
		        	
		        	System.out.print("Nazwa grupy: "); String g = odczyt.nextLine();
		        	userM.root.groupadd(g);
		        }
		        if(odczytano.equals("GROUPADDUS"))
		        {
		        	
		        	System.out.print("Nazwa grupy: "); String gr = odczyt.nextLine();
					System.out.print("Nazwa uzytkownika: "); String ur = odczyt.nextLine();
					userM.root.groupAddUser(gr, ur);
		        } 
		        /*
		        if(odczytano.equals("CUSER"))
		        {
		        	
		        	odczytano = "cuser";
		        } 
		        */
		        
		 ///////////////////////////////// STRUMIENIE /////////////////////////////////////////////////
		        else if(odczytano.equals("PIPE"))
		        {
			    	
			    	System.out.println("Podaj PID procesu ktory utworzy lacze:");
			    	String pid = odczyt.nextLine();
			    	if(jestIntem(pid)==false)
			    	{
			    		System.out.println("Zly format PID!");
			    	}
					boolean jestTakiProces=false;
					proc p = new proc();
					
					for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
					{	
						p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
						int NRpid = Integer.parseInt(pid);
						if(p.pid==NRpid)
						{
							
							jestTakiProces=true;
						}	
					}		
					if(jestTakiProces==false)
					{	
						System.out.println("Nie ma procesu o takim PID!");	
					}
					else
					{
						int NRpid = Integer.parseInt(pid);
						fd = streamsM.pipe.DoPipe(NRpid);
					}
			    }
		        else if(odczytano.equals("READ"))
		        {
			    	System.out.println("Podaj PID procesu ktory b´dzie czytaç z ∏àcza:");
			    	String pid = odczyt.nextLine();
			    	if(jestIntem(pid)==false)
			    	{
			    		System.out.println("Zly format PID!");
			    	}
					boolean jestTakiProces=false;
					proc p = new proc();
					for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
					{	
						p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
						int NRpid = Integer.parseInt(pid);
						if(p.pid==NRpid)
						{
							jestTakiProces=true;
						}	
					}		
					if(jestTakiProces==false)
					{	
						System.out.println("Nie ma procesu o takim PID!");	
					}
					else
					{
						///////////////// READ ///////////////////////////////////////
						Scanner reading = new Scanner(System.in);
						int NRpid = Integer.parseInt(pid);
						String output = null;
						
						System.out.println("Funkcja READ - Podaj ktory deskryptor chcesz uzyc:");
						int fd0 = reading.nextInt();
						output = streamsM.pipe.read(fd0, NRpid);
						System.out.println("Funkcja READ - Odczytano dane:"+ output);
						
					}	
			    }
		        else if(odczytano.equals("WRITE"))
		        {
			    	System.out.println("Podaj PID procesu ktory bedzie pisac do ∏acza:");
			    	String pid = odczyt.nextLine();
			    	if(jestIntem(pid)==false)
			    	{
			    		System.out.println("Zly format PID!");
			    	}
					boolean jestTakiProces=false;
					proc p = new proc();
					for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
					{	
						p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
						int NRpid = Integer.parseInt(pid);
						if(p.pid==NRpid)
						{
							jestTakiProces=true;
						}	
					}		
					if(jestTakiProces==false)
					{	
						System.out.println("Nie ma procesu o takim PID!");	
					}
					else
					{
						//////////////// WRITE ///////////////////////////////////////
						int NRpid = Integer.parseInt(pid);
						Scanner reading = new Scanner(System.in);
						String txt;
						
						System.out.println("Funkcja WRITE - Podaj dane do wczytania:");
						txt = reading.nextLine();
						System.out.println("Funkcja WRITE - Podaj ktory deskryptor chcesz uzyc:");
						int fd1 = reading.nextInt();
						
						streamsM.pipe.write(fd1, txt, NRpid);
					}
			    }
		        else if(odczytano.equals("CLSFD"))
		        {
		        	System.out.println("Podaj PID procesu:");
			    	String pid = odczyt.nextLine();
			    	if(jestIntem(pid)==false)
			    	{
			    		System.out.println("Zly format PID!");
			    	}
					boolean jestTakiProces=false;
					proc p = new proc();
					for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
					{	
						p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
						int NRpid = Integer.parseInt(pid);
						if(p.pid==NRpid)
						{
							jestTakiProces=true;
						}	
					}		
					if(jestTakiProces==false)
					{	
						System.out.println("Nie ma procesu o takim PID!");	
					}
					else
					{
						//////////////////CLOSE/////////////////////////////////////
						int NRpid = Integer.parseInt(pid);
			        	Scanner reading = new Scanner(System.in);
			        	System.out.println("Funkcja CLOSE - Podaj ktory deskryptor chcesz zamknac:");
						int txt = reading.nextInt();
						streamsM.pipe.close(txt, NRpid);
					}	
		        }	
		////////////////////////////////////////// PROCESY ///////////////////////////////////////
        if(odczytano.equals("FORK"))
        {
        	System.out.println("Podaj PID procesu na którym wywo∏aç funkcj´ FORK:");
        	String numerP = odczyt.nextLine();
        	if(jestIntem(numerP)==false)
        	{
        		System.out.println("B∏´dny format PID!");
        	}
        	else
        	{
        		int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
        		proc p = new proc();
        		boolean jestTakiProces=false;
        		for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
        		{	
        			p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
        			if(p.pid==numerPID)
        			{
        				processM.klasa.fork(p);
        				jestTakiProces=true;
        			}	
        		}
        		if(jestTakiProces==false)
        		{
        			System.out.println("Nie ma procesu o takim PID!");
        		}
        	}
        }
        else if(odczytano.equals("PCBALL"))
        {
        	System.out.println("Wszystkie istniejàce procesy:");
        	for(int k=0;k<processM.klasa.ListaProcesow.size();k++)
        	{
        		System.out.println("PID: "+ processM.klasa.ListaProcesow.get(k).pid);
				System.out.println("PPID: "+ processM.klasa.ListaProcesow.get(k).ppid);
				System.out.println("UID: "+ processM.klasa.ListaProcesow.get(k).uid);
				System.out.println("NICE: "+ processM.klasa.ListaProcesow.get(k).nice);
				System.out.println("STAN PROCESU: "+ processM.klasa.ListaProcesow.get(k).s);
				System.out.println("ADRES W PAMI¢CI: "+ processM.klasa.ListaProcesow.get(k).addr);
				System.out.println("LICZNIK ROZKAZÓW: "+ processM.klasa.ListaProcesow.get(k).licznikRozkazow);
				System.out.println("WARTOÂå ZWRACANA: "+ processM.klasa.ListaProcesow.get(k).registerReturnValue);
				System.out.println("Numery PID potomków "+ processM.klasa.ListaProcesow.get(k).pidyPotomkow.toString());
				System.out.println("ZAWARTOSC fdtab: "+ processM.klasa.ListaProcesow.get(k).fdtab.toString());

				if(processM.klasa.ListaProcesow.get(k).jestInitem==true)
				{	
				System.out.println("CZY PROCES JEST INITEM: TAK");
				}
				else
				{
				System.out.println("CZY PROCES JEST INITEM: NIE");
				}
				System.out.println("------------------------------------------------------------");
        	}	
        }
        else if(odczytano.equals("PCB"))
        {
        	System.out.println("Podaj PID procesu którego PCB chcesz wyÊwietliç:");
        	String numerP = odczyt.nextLine();
        	if(jestIntem(numerP)==false)
        	{
        		System.out.println("B∏´dny format PID!");
        	}
        	else
        	{
        		int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
        		proc p = new proc();
        		boolean jestTakiProces=false;
        		for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
        		{	
        			p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
        			if(p.pid==numerPID)
        			{
        				System.out.println("PID: "+ p.pid);
        				System.out.println("PPID: "+ p.ppid);
        				System.out.println("UID: "+ p.uid);
        				System.out.println("NICE: "+ p.nice);
        				System.out.println("STAN PROCESU: "+ p.s);
        				System.out.println("ADRES W PAMI¢CI: "+ p.addr);
        				System.out.println("LICZNIK ROZKAZÓW: "+ p.licznikRozkazow);
        				System.out.println("WARTOÂå ZWRACANA: "+ p.registerReturnValue);
        				System.out.println("Numery PID potomków "+ p.pidyPotomkow.toString());
        				System.out.println("ZAWARTOSC fdtab: "+ p.fdtab.toString());
        				if(p.jestInitem==true)
        				{	
        				System.out.println("CZY PROCES JEST INITEM: TAK");
        				}
        				else
        				{
        				System.out.println("CZY PROCES JEST INITEM: NIE");
        				}
        				jestTakiProces=true;
        			}
        		}	
        		if(jestTakiProces==false)
        		{
        			System.out.println("Nie ma procesu o takim PID!");
        		}
        }
        }
        else if(odczytano.equals("EXIT"))
        {
        	System.out.println("Podaj PID procesu na którym wywo∏aç funkcj´ EXIT:");
        	String numerP = odczyt.nextLine();
        	if(jestIntem(numerP)==false)
        	{
        		System.out.println("B∏´dny format PID!");
        	}
        	else
        	{
        		int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
        		proc p = new proc();
        		boolean jestTakiProces=false;
        		for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
        		{	
        			p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
        			if(p.pid==numerPID)
        			{
        		
        				processM.klasa.exit(p);
        				jestTakiProces=true;
        			}	
        		}
        		if(jestTakiProces==false)
        		{
        			System.out.println("Nie ma procesu o takim PID!");
        		}
        	}
        }
        else if(odczytano.equals("WAIT"))
        {
        	System.out.println("Podaj PID procesu na którym wywo∏aç funkcj´ WAIT:");
        	String numerP = odczyt.nextLine();
        	if(jestIntem(numerP)==false)
        	{
        		System.out.println("B∏´dny format PID!");
        	}
        	else
        	{
        		int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
        		proc p = new proc();
        		boolean jestTakiProces=false;
        		for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
        		{	
        			p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
        			if(p.pid==numerPID)
        			{
        				processM.klasa.wait2(p);
        				jestTakiProces=true;
        			}
        		}
        		if(jestTakiProces==false)
        		{
        			System.out.println("Nie ma procesu o takim PID!");
        		}
        	}
        }
        else if(odczytano.equals("EXECVE"))
        {
        	if(processM.klasa.ListaProcesow.size()==1)
        	{
        		
        		System.out.println("Stworz najpierw jakis proces!");
        		
        	}
        	else
        	{
        	System.out.println("Podaj PID procesu któremu chcesz zmieniç wykonywany program:");
        	String numerP = odczyt.nextLine();
        	if(jestIntem(numerP)==false)
        	{
        		System.out.println("B∏´dny format PID!");
        	}
        	else if(Integer.parseInt(numerP)==1)
        	{
        		System.out.println("Nie mozna zmienic kodu initowi!");
        		
        	}
        	else
        	{
        		int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
        		proc p = new proc();
        		boolean jestTakiProces=false;
        		for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
        		{	
        			p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
        			if(p.pid==numerPID)
        			{
        				processM.klasa.exec(p);
        				jestTakiProces=true;
        				break;
        			}	
        		}
        		if(jestTakiProces==false)
        		{
        			System.out.println("Nie ma procesu o takim PID!");
        		}
        }
        }}
        
        ////////////////////////////////////////////// PLIKI ////////////////////////////////////////////////////
        else if(odczytano.equals("NEWFILE"))
        {
        	//tworzenie pliku
        	FilesM.p.wezly.add(new Iwezel(FilesM.p.dysktwardy));
        	
        }
        else if(odczytano.equals("READFILE"))
        {
        //wyswietl plik
        	FilesM.p.wezly.get(0).read(FilesM.p.dysktwardy, FilesM.p.wezly);    		
        }
        else if(odczytano.equals("WRITEFILE"))
        {
        //zapisz do pliku
        	FilesM.p.wezly.get(0).write(FilesM.p.dysktwardy, FilesM.p.wezly);
    		
        }
        else if(odczytano.equals("DISC"))
        {
        //wyswietl zawartosc dysku
        	FilesM.p.wezly.get(0).zawdysk(FilesM.p.dysktwardy, FilesM.p.wezly);
        }
        else if(odczytano.equals("FILENAMES"))
        {
        	//wyswietl nazwy plikow
        	FilesM.p.wezly.get(0).wyswpliki(FilesM.p.dysktwardy, FilesM.p.wezly);

        }
        /*
        else if(odczytano.equals("SHOWFILE"))
        {
        	
        	//wyswietl plik_s
			System.out.println(FilesM.p.wezly.get(0).read_s(FilesM.p.dysktwardy, FilesM.p.wezly));

        }
        */
		/////////////////////////////////////// PAMIEC /////////////////////////////////////////////
        else if(odczytano.equals("WRITEMEM"))
        {
        	//Zapis
        	System.out.println("Podaj dane do zapisu: ");
			MemoryM.pam1.napis = odczyt.nextLine();
			MemoryM.pam1.a.ZAPIS(MemoryM.pam1.napis);
			System.out.println("Dane zapisano");
			
        }
        else if(odczytano.equals("READMEM"))
        {
        	//Odczyt
        	System.out.println("Podaj indeks procesu: ");
        	MemoryM.pam1.indeks_procesu = odczyt.nextInt();
			System.out.println("Podaj adres: ");
			MemoryM.pam1.adres = odczyt.nextInt();
			System.out.println(MemoryM.pam1.indeks_procesu+","+MemoryM.pam1.adres);
			
			MemoryM.pam1.kupa = MemoryM.pam1.a.ODCZYT( MemoryM.pam1.indeks_procesu, MemoryM.pam1.adres );
			if( MemoryM.pam1.kupa == -1 ) System.out.println("Podano zly indeks lub adres!!");
			else System.out.println( MemoryM.pam1.kupa );
        }
        else if(odczytano.equals("DELPROC"))
        {
        	//Usun proces
        	System.out.println("Podaj indeks procesu do usuniecia: ");
        	MemoryM.pam1.indeks_procesu = odczyt.nextInt();
        	MemoryM.pam1.kupa = MemoryM.pam1.a.USUN(MemoryM.pam1.indeks_procesu);
			if( MemoryM.pam1.kupa == -1) System.out.println("Podano zly indeks!!");
			else System.out.println("Usunieto proces");
        }
        else if(odczytano.equals("MEM"))
        {
        	//Wyswietl pamiec
        	for( MemoryM.pam1.w = 0 ; MemoryM.pam1.w < 256 ; MemoryM.pam1.w++ )
			{
				if( MemoryM.pam1.w%16 == 0 )
				{
					System.out.print( "| " );
				}
				System.out.print( MemoryM.pam1.a.pamiec_op[ MemoryM.pam1.w ] + " " );
			}
			System.out.println();
        }
        else if(odczytano.equals("PROCTABLE"))
        {
        	//Wyswietl zawartosc tablicy stron procesu
        	System.out.println("Podaj numer procesu: ");
        	MemoryM.pam1.indeks_procesu = odczyt.nextInt();
			try{
				for( MemoryM.pam1.w = 0 ; MemoryM.pam1.w < MemoryM.pam1.a.tablica_proc[ MemoryM.pam1.indeks_procesu ].wielkosc ; MemoryM.pam1.w++ )
				{
					System.out.print( MemoryM.pam1.a.tablica_proc[ MemoryM.pam1.indeks_procesu ].tablica_stron[ MemoryM.pam1.w ] + " ");
				}
				System.out.println();
			}
			catch( Exception e )
			{
				System.out.println("Podano nieprawidlowy numer procesu");
			}
        }
        else if(odczytano.equals("VECTOR"))
        {
        	//Wyswietl wektor zajetosci
        	for( MemoryM.pam1.w = 0 ; MemoryM.pam1.w < 16 ; MemoryM.pam1.w++ )
			{
				System.out.print(MemoryM.pam1.a.wektor_zajetosci[ MemoryM.pam1.w ] + " ");
			}
			System.out.println();
        }
        else if(odczytano.equals("CLONE"))
        {
        	//Sklonuj kod
        	System.out.println("Podaj indeks procesu: ");
        	MemoryM.pam1.indeks_procesu = odczyt.nextInt();
        	MemoryM.pam1.adres = MemoryM.pam1.a.KLONUJ( MemoryM.pam1.indeks_procesu );
			System.out.println("Klon zapisano pod adresem " + MemoryM.pam1.adres );
        }
        else if(odczytano.equals("OVERWRITE"))
        {
        	//Nadpisz kod
        	System.out.println("Podaj dane do nadpisania: ");
        	MemoryM.pam1.napis = odczyt.nextLine();
			System.out.println("Podaj indeks procesu: ");
			MemoryM.pam1.indeks_procesu = odczyt.nextInt();
			System.out.println("Dane nadpisano pod adresem"	+ MemoryM.pam1.a.NADPISZ( MemoryM.pam1.napis , MemoryM.pam1.indeks_procesu ));
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////
        else if(odczytano.equals("HELP")||odczytano.equals("help"))
        {
        		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        		System.out.println("Pamiec");
        		System.out.println("");
        		System.out.println("WRITEMEM  - zapisuje do pamieci");
        		System.out.println("READMEM   - odczytuje z pamieci");
        		System.out.println("DELPROC   - usun proces");
        		System.out.println("MEM       - wyswietla pamiec");
        		System.out.println("PROCTABLE - wyswietla zawartosc tablicy stron procesu");
        		System.out.println("VECTOR    - wyswietla wektor zajetosci");
        		System.out.println("CLONE     - klonuje kod");
        		System.out.println("OVERWRITE - nadpisuje kod");
        		System.out.println("");
        		System.out.println("Procesy");
        		System.out.println("");
        		System.out.println("Proces init ma PID=1");
        		System.out.println("");
        		System.out.println("FORK      - tworzy nowy proces");
        		System.out.println("EXECVE    - laduje do pamieci nowy kod programu dla procesu");
        		System.out.println("EXIT      - konczy dzia∏anie procesu");
        		System.out.println("WAIT      - wymusza bezczynnosc do momentu zakonczenia dzia∏ania dowolnego procesu potomnego");
        		System.out.println("PCB       - wyswietla blok kontrolny procesu");
        		System.out.println("PCBALL    - wyswietla bloki kontrolne wszystkich istniejàcych procesów");
        		System.out.println("");
        		System.out.println("Pliki");
        		System.out.println("");
        		System.out.println("NEWFILE   - tworzy nowy plik");
        		System.out.println("READFILE  - wyswietla plik");
        		System.out.println("WRITEFILE - zapisuje do pliku");
        		System.out.println("DISC      - wyswietla zawartosc dysku");
        		System.out.println("FILENAMES - wyswietla nazwy plikow");
        		System.out.println("");
        		System.out.println("Uzytkownicy");
        		System.out.println("");
        		System.out.println("CUSER     - zmienia uzytkownika");
        		System.out.println("USERADD   - dodaje uzytkownika");
        		System.out.println("USERMODPA - zmienia haslo");
        		System.out.println("USERDEL   - usuwa uzytkownika");
        		System.out.println("GROUPADD  - dodaje grupe");
        		System.out.println("GROUPADDUS- dodanie uzytkownika do grupy");
        		System.out.println("");
        		System.out.println("Strumienie (lacza nienazwane)");
        		System.out.println("");
        		System.out.println("PIPE      - tworzy pipe przez wybrany proces");
        		System.out.println("CLSFD     - zamyka wybrany deskryptor przez wybrany proces");
        		System.out.println("WRITE     - wywoluje funkcje write dla wybranego procesu");
        		System.out.println("READ      - wywoluje funkcje read dla wybranego procesu");
        		System.out.println("");
        		
        		System.out.println("SHUTDOWN  - zamyka system operacyjny");
        		System.out.println("HELP      - otwiera pomoc");
        		System.out.println("help      - otwiera pomoc");
        		
        		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        else if(odczytano.equals("PCBHELP")||odczytano.equals("pcbhelp"))
        {
        	System.out.println("WARTOSC ZWRACANA:");
        	System.out.println("FORK: 0 jeÊli funkcja jest potomkiem");
        	System.out.println("    : PID potomka jeÊli jest rodzicem ");
        	System.out.println("WAIT: -1 jeÊli proces nie mia∏ dzieci");
        	System.out.println("    : PID zakonczonego dziecka ");
        	System.out.println("ADRES W PAMI¢CI:");
        	System.out.println("wartosc -1 oznacza brak adresu w pamieci");
        	
        }
        
	}
			else
			{
				
				
///////////////////awedjilwajdiwledijlweadjk/////// sdjhkeajhd				
				
				
				
				
				
				
				
				User user = new User(userM.usr.activeUid);
				System.out.println("\r\nAktywny uzytkownik: User");
				odczytano = odczyt.nextLine();
				
////////////////////////////////////UZYTKOWNICY/////////////////////////////////////////		
if(odczytano.equals("USERPASS"))
{
	System.out.print("Haslo: "); String p = odczyt.nextLine();
	user.usermod_passwd(p);
	System.out.println("Zmieniono haslo.");

}
/*
if(odczytano.equals("CUSER"))
{

odczytano = "cuser";
} 
*/

///////////////////////////////// STRUMIENIE /////////////////////////////////////////////

else if(odczytano.equals("PIPE"))
{
	
	System.out.println("Podaj PID procesu ktory utworzy lacze:");
	String pid = odczyt.nextLine();
	if(jestIntem(pid)==false)
	{
		System.out.println("Zly format PID!");
	}
	boolean jestTakiProces=false;
	proc p = new proc();
	
	for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
	{	
		p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
		int NRpid = Integer.parseInt(pid);
		if(p.pid==NRpid)
		{
			
			jestTakiProces=true;
		}	
	}		
	if(jestTakiProces==false)
	{	
		System.out.println("Nie ma procesu o takim PID!");	
	}
	else
	{
		int NRpid = Integer.parseInt(pid);
		fd = streamsM.pipe.DoPipe(NRpid);
	}
}
else if(odczytano.equals("READ"))
{
	System.out.println("Podaj PID procesu ktory b´dzie czytaç z ∏àcza:");
	String pid = odczyt.nextLine();
	if(jestIntem(pid)==false)
	{
		System.out.println("Zly format PID!");
	}
	boolean jestTakiProces=false;
	proc p = new proc();
	for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
	{	
		p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
		int NRpid = Integer.parseInt(pid);
		if(p.pid==NRpid)
		{
			jestTakiProces=true;
		}	
	}		
	if(jestTakiProces==false)
	{	
		System.out.println("Nie ma procesu o takim PID!");	
	}
	else
	{
		///////////////// READ ///////////////////////////////////////
		Scanner reading = new Scanner(System.in);
		int NRpid = Integer.parseInt(pid);
		String output = null;
		
		System.out.println("Funkcja READ - Podaj ktory deskryptor chcesz uzyc:");
		int fd0 = reading.nextInt();
		output = streamsM.pipe.read(fd0, NRpid);
		System.out.println("Funkcja READ - Odczytano dane:"+ output);
		
	}	
}
else if(odczytano.equals("WRITE"))
{
	System.out.println("Podaj PID procesu ktory bedzie pisac do ∏acza:");
	String pid = odczyt.nextLine();
	if(jestIntem(pid)==false)
	{
		System.out.println("Zly format PID!");
	}
	boolean jestTakiProces=false;
	proc p = new proc();
	for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
	{	
		p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
		int NRpid = Integer.parseInt(pid);
		if(p.pid==NRpid)
		{
			jestTakiProces=true;
		}	
	}		
	if(jestTakiProces==false)
	{	
		System.out.println("Nie ma procesu o takim PID!");	
	}
	else
	{
		//////////////// WRITE ///////////////////////////////////////
		int NRpid = Integer.parseInt(pid);
		Scanner reading = new Scanner(System.in);
		String txt;
		
		System.out.println("Funkcja WRITE - Podaj dane do wczytania:");
		txt = reading.nextLine();
		System.out.println("Funkcja WRITE - Podaj ktory deskryptor chcesz uzyc:");
		int fd1 = reading.nextInt();
		
		streamsM.pipe.write(fd1, txt, NRpid);
	}
}
else if(odczytano.equals("CLSFD"))
{
	System.out.println("Podaj PID procesu:");
	String pid = odczyt.nextLine();
	if(jestIntem(pid)==false)
	{
		System.out.println("Zly format PID!");
	}
	boolean jestTakiProces=false;
	proc p = new proc();
	for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
	{	
		p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
		int NRpid = Integer.parseInt(pid);
		if(p.pid==NRpid)
		{
			jestTakiProces=true;
		}	
	}		
	if(jestTakiProces==false)
	{	
		System.out.println("Nie ma procesu o takim PID!");	
	}
	else
	{
		//////////////////CLOSE/////////////////////////////////////
		int NRpid = Integer.parseInt(pid);
    	Scanner reading = new Scanner(System.in);
    	System.out.println("Funkcja CLOSE - Podaj ktory deskryptor chcesz zamknac:");
		int txt = reading.nextInt();
		streamsM.pipe.close(txt, NRpid);
	}	
}
////////////////////////////////////////// PROCESY ///////////////////////////////////////
if(odczytano.equals("FORK"))
{
System.out.println("Podaj PID procesu na którym wywo∏aç funkcj´ FORK:");
String numerP = odczyt.nextLine();
if(jestIntem(numerP)==false)
{
System.out.println("B∏´dny format PID!");
}
else
{
int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
proc p = new proc();
boolean jestTakiProces=false;
for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
{	
p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
if(p.pid==numerPID)
{
	processM.klasa.fork(p);
	jestTakiProces=true;
}	
}
if(jestTakiProces==false)
{
System.out.println("Nie ma procesu o takim PID!");
}
}
}
else if(odczytano.equals("PCBALL"))
{
System.out.println("Wszystkie istniejàce procesy:");
for(int k=0;k<processM.klasa.ListaProcesow.size();k++)
{
System.out.println("PID: "+ processM.klasa.ListaProcesow.get(k).pid);
System.out.println("PPID: "+ processM.klasa.ListaProcesow.get(k).ppid);
System.out.println("UID: "+ processM.klasa.ListaProcesow.get(k).uid);
System.out.println("NICE: "+ processM.klasa.ListaProcesow.get(k).nice);
System.out.println("STAN PROCESU: "+ processM.klasa.ListaProcesow.get(k).s);
System.out.println("ADRES W PAMI¢CI: "+ processM.klasa.ListaProcesow.get(k).addr);
System.out.println("LICZNIK ROZKAZÓW: "+ processM.klasa.ListaProcesow.get(k).licznikRozkazow);
System.out.println("WARTOÂå ZWRACANA: "+ processM.klasa.ListaProcesow.get(k).registerReturnValue);
System.out.println("Numery PID potomków "+ processM.klasa.ListaProcesow.get(k).pidyPotomkow.toString());
System.out.println("ZAWARTOSC fdtab: "+ processM.klasa.ListaProcesow.get(k).fdtab.toString());

if(processM.klasa.ListaProcesow.get(k).jestInitem==true)
{	
System.out.println("CZY PROCES JEST INITEM: TAK");
}
else
{
System.out.println("CZY PROCES JEST INITEM: NIE");
}
System.out.println("------------------------------------------------------------");
}	
}
else if(odczytano.equals("PCB"))
{
System.out.println("Podaj PID procesu którego PCB chcesz wyÊwietliç:");
String numerP = odczyt.nextLine();
if(jestIntem(numerP)==false)
{
System.out.println("B∏´dny format PID!");
}
else
{
int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
proc p = new proc();
boolean jestTakiProces=false;
for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
{	
p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
if(p.pid==numerPID)
{
	System.out.println("PID: "+ p.pid);
	System.out.println("PPID: "+ p.ppid);
	System.out.println("UID: "+ p.uid);
	System.out.println("NICE: "+ p.nice);
	System.out.println("STAN PROCESU: "+ p.s);
	System.out.println("ADRES W PAMI¢CI: "+ p.addr);
	System.out.println("LICZNIK ROZKAZÓW: "+ p.licznikRozkazow);
	System.out.println("WARTOÂå ZWRACANA: "+ p.registerReturnValue);
	System.out.println("Numery PID potomków "+ p.pidyPotomkow.toString());
	System.out.println("ZAWARTOSC fdtab: "+ p.fdtab.toString());
	if(p.jestInitem==true)
	{	
	System.out.println("CZY PROCES JEST INITEM: TAK");
	}
	else
	{
	System.out.println("CZY PROCES JEST INITEM: NIE");
	}
	jestTakiProces=true;
}
}	
if(jestTakiProces==false)
{
System.out.println("Nie ma procesu o takim PID!");
}
}
}
else if(odczytano.equals("EXIT"))
{
System.out.println("Podaj PID procesu na którym wywo∏aç funkcj´ EXIT:");
String numerP = odczyt.nextLine();
if(jestIntem(numerP)==false)
{
System.out.println("B∏´dny format PID!");
}
else
{
int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
proc p = new proc();
boolean jestTakiProces=false;
for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
{	
p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
if(p.pid==numerPID)
{
	processM.klasa.exit(p);
	jestTakiProces=true;
}	
}
if(jestTakiProces==false)
{
System.out.println("Nie ma procesu o takim PID!");
}
}
}
else if(odczytano.equals("WAIT"))
{
System.out.println("Podaj PID procesu na którym wywo∏aç funkcj´ WAIT:");
String numerP = odczyt.nextLine();
if(jestIntem(numerP)==false)
{
System.out.println("B∏´dny format PID!");
}
else
{
int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
proc p = new proc();
boolean jestTakiProces=false;
for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
{	
p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
if(p.pid==numerPID)
{
	processM.klasa.wait2(p);
	jestTakiProces=true;
}
}
if(jestTakiProces==false)
{
System.out.println("Nie ma procesu o takim PID!");
}
}
}
else if(odczytano.equals("EXECVE"))
{
if(processM.klasa.ListaProcesow.size()==1)
{

System.out.println("Stworz najpierw jakis proces!");

}
else
{
System.out.println("Podaj PID procesu któremu chcesz zmieniç wykonywany program:");
String numerP = odczyt.nextLine();
if(jestIntem(numerP)==false)
{
System.out.println("B∏´dny format PID!");
}
else if(Integer.parseInt(numerP)==1)
{
System.out.println("Nie mozna zmienic kodu initowi!");

}
else
{
int numerPID = Integer.parseInt(numerP); //zamiana wczytanego numer z string na int
proc p = new proc();
boolean jestTakiProces=false;
for(int i =0;i<processM.klasa.ListaProcesow.size();i++)
{	
p = processM.klasa.ListaProcesow.get(i); //get zwraca element o pozycji i
if(p.pid==numerPID)
{
	processM.klasa.exec(p);
	jestTakiProces=true;
	break;
}	
}
if(jestTakiProces==false)
{
System.out.println("Nie ma procesu o takim PID!");
}
}
}}

////////////////////////////////////////////// PLIKI ////////////////////////////////////////////////////
else if(odczytano.equals("NEWFILE"))
{
//tworzenie pliku
FilesM.p.wezly.add(new Iwezel(FilesM.p.dysktwardy));

}
else if(odczytano.equals("READFILE"))
{
//wyswietl plik
FilesM.p.wezly.get(0).read(FilesM.p.dysktwardy, FilesM.p.wezly);    		
}
else if(odczytano.equals("WRITEFILE"))
{
//zapisz do pliku
FilesM.p.wezly.get(0).write(FilesM.p.dysktwardy, FilesM.p.wezly);

}
else if(odczytano.equals("DISC"))
{
//wyswietl zawartosc dysku
FilesM.p.wezly.get(0).zawdysk(FilesM.p.dysktwardy, FilesM.p.wezly);
}
else if(odczytano.equals("FILENAMES"))
{
//wyswietl nazwy plikow
FilesM.p.wezly.get(0).wyswpliki(FilesM.p.dysktwardy, FilesM.p.wezly);

}
/*
else if(odczytano.equals("SHOWFILE"))
{

//wyswietl plik_s
System.out.println(FilesM.p.wezly.get(0).read_s(FilesM.p.dysktwardy, FilesM.p.wezly));

}
*/
/////////////////////////////////////// PAMIEC /////////////////////////////////////////////
else if(odczytano.equals("WRITEMEM"))
{
//Zapis
System.out.println("Podaj dane do zapisu: ");
MemoryM.pam1.napis = odczyt.nextLine();
MemoryM.pam1.a.ZAPIS(MemoryM.pam1.napis);
System.out.println("Dane zapisano");

}
else if(odczytano.equals("READMEM"))
{
//Odczyt
System.out.println("Podaj indeks procesu: ");
MemoryM.pam1.indeks_procesu = odczyt.nextInt();
System.out.println("Podaj adres: ");
MemoryM.pam1.adres = odczyt.nextInt();
System.out.println(MemoryM.pam1.indeks_procesu+","+MemoryM.pam1.adres);

MemoryM.pam1.kupa = MemoryM.pam1.a.ODCZYT( MemoryM.pam1.indeks_procesu, MemoryM.pam1.adres );
if( MemoryM.pam1.kupa == -1 ) System.out.println("Podano zly indeks lub adres!!");
else System.out.println( MemoryM.pam1.kupa );
}
else if(odczytano.equals("DELPROC"))
{
//Usun proces
System.out.println("Podaj indeks procesu do usuniecia: ");
MemoryM.pam1.indeks_procesu = odczyt.nextInt();
MemoryM.pam1.kupa = MemoryM.pam1.a.USUN(MemoryM.pam1.indeks_procesu);
if( MemoryM.pam1.kupa == -1) System.out.println("Podano zly indeks!!");
else System.out.println("Usunieto proces");
}
else if(odczytano.equals("MEM"))
{
//Wyswietl pamiec
for( MemoryM.pam1.w = 0 ; MemoryM.pam1.w < 256 ; MemoryM.pam1.w++ )
{
if( MemoryM.pam1.w%16 == 0 )
{
System.out.print( "| " );
}
System.out.print( MemoryM.pam1.a.pamiec_op[ MemoryM.pam1.w ] + " " );
}
System.out.println();
}
else if(odczytano.equals("PROCTABLE"))
{
//Wyswietl zawartosc tablicy stron procesu
System.out.println("Podaj numer procesu: ");
MemoryM.pam1.indeks_procesu = odczyt.nextInt();
try{
for( MemoryM.pam1.w = 0 ; MemoryM.pam1.w < MemoryM.pam1.a.tablica_proc[ MemoryM.pam1.indeks_procesu ].wielkosc ; MemoryM.pam1.w++ )
{
System.out.print( MemoryM.pam1.a.tablica_proc[ MemoryM.pam1.indeks_procesu ].tablica_stron[ MemoryM.pam1.w ] + " ");
}
System.out.println();
}
catch( Exception e )
{
System.out.println("Podano nieprawidlowy numer procesu");
}
}
else if(odczytano.equals("VECTOR"))
{
//Wyswietl wektor zajetosci
for( MemoryM.pam1.w = 0 ; MemoryM.pam1.w < 16 ; MemoryM.pam1.w++ )
{
System.out.print(MemoryM.pam1.a.wektor_zajetosci[ MemoryM.pam1.w ] + " ");
}
System.out.println();
}
else if(odczytano.equals("CLONE"))
{
//Sklonuj kod
System.out.println("Podaj indeks procesu: ");
MemoryM.pam1.indeks_procesu = odczyt.nextInt();
MemoryM.pam1.adres = MemoryM.pam1.a.KLONUJ( MemoryM.pam1.indeks_procesu );
System.out.println("Klon zapisano pod adresem " + MemoryM.pam1.adres );
}
else if(odczytano.equals("OVERWRITE"))
{
//Nadpisz kod
System.out.println("Podaj dane do nadpisania: ");
MemoryM.pam1.napis = odczyt.nextLine();
System.out.println("Podaj indeks procesu: ");
MemoryM.pam1.indeks_procesu = odczyt.nextInt();
System.out.println("Dane nadpisano pod adresem"	+ MemoryM.pam1.a.NADPISZ( MemoryM.pam1.napis , MemoryM.pam1.indeks_procesu ));
}
///////////////////////////////////////////////////////////////////////////////////////////////
else if(odczytano.equals("HELP")||odczytano.equals("help"))
{
System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
System.out.println("Pamiec");
System.out.println("");
System.out.println("WRITEMEM  - zapisuje do pamieci");
System.out.println("READMEM   - odczytuje z pamieci");
System.out.println("DELPROC   - usun proces");
System.out.println("MEM       - wyswietla pamiec");
System.out.println("PROCTABLE - wyswietla zawartosc tablicy stron procesu");
System.out.println("VECTOR    - wyswietla wektor zajetosci");
System.out.println("CLONE     - klonuje kod");
System.out.println("OVERWRITE - nadpisuje kod");
System.out.println("");
System.out.println("Procesy");
System.out.println("");
System.out.println("Proces init ma PID=1");
System.out.println("");
System.out.println("FORK      - tworzy nowy proces");
System.out.println("EXECVE    - laduje do pamieci nowy kod programu dla procesu");
System.out.println("EXIT      - konczy dzia∏anie procesu");
System.out.println("WAIT      - wymusza bezczynnosc do momentu zakonczenia dzia∏ania dowolnego procesu potomnego");
System.out.println("PCB       - wyswietla blok kontrolny procesu");
System.out.println("PCBALL    - wyswietla bloki kontrolne wszystkich istniejàcych procesów");
System.out.println("");
System.out.println("Pliki");
System.out.println("");
System.out.println("NEWFILE   - tworzy nowy plik");
System.out.println("READFILE  - wyswietla plik");
System.out.println("WRITEFILE - zapisuje do pliku");
System.out.println("DISC      - wyswietla zawartosc dysku");
System.out.println("FILENAMES - wyswietla nazwy plikow");
System.out.println("");
System.out.println("");
System.out.println("Uzytkownicy");
System.out.println("CUSER     - zmienia uzytkownika");
System.out.println("USERPASS  - zmienia haslo uzytkownika");
System.out.println("");
System.out.println("Strumienie (lacza nienazwane)");
System.out.println("");
System.out.println("PIPE      - tworzy pipe przez wybrany proces");
System.out.println("CLSFD     - zamyka wybrany deskryptor przez wybrany proces");
System.out.println("WRITE     - wywoluje funkcje write dla wybranego procesu");
System.out.println("READ      - wywoluje funkcje read dla wybranego procesu");

System.out.println("");
System.out.println("SHUTDOWN  - zamyka system operacyjny");
System.out.println("HELP      - otwiera pomoc");
System.out.println("help      - otwiera pomoc");

System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
}
else if(odczytano.equals("PCBHELP")||odczytano.equals("pcbhelp"))
{
System.out.println("WARTOSC ZWRACANA:");
System.out.println("FORK: 0 jeÊli funkcja jest potomkiem");
System.out.println("    : PID potomka jeÊli jest rodzicem ");
System.out.println("WAIT: -1 jeÊli proces nie mia∏ dzieci");
System.out.println("    : PID zakonczonego dziecka ");
System.out.println("ADRES W PAMI¢CI:");
System.out.println("wartosc -1 oznacza brak adresu w pamieci");
}
				
				
				
				
				
				
				
				
				
				
			}
        /////////////////////////////////////////////////////////////////////////////
        }
        while(!odczytano.equals("CUSER") && !odczytano.equals("SHUTDOWN"));
			
		
        if (!odczytano.equals("SHUTDOWN"))
		{
			String u;
			String p;
			boolean b;
			
			do
			{
				System.out.println("\r\n*LOGOWANIE");
				System.out.print("Nazwa uzytkownika: "); u = odczyt.nextLine();
				System.out.print("Haslo: "); p = odczyt.nextLine();
				b = userM.usr.login.verifyLogin(u, p);
			} while (b!=true);
			
			userM.usr.activeUid = userM.usr.login.giveUid(u);
		}
        else
        {
        	break;
        	
        }	
        
        
		}while(true/*!odczytano.contains("SHUTDOWN")*/);
		
        }
public static boolean jestIntem(String s) {
    try 
    { 
        Integer.parseInt(s); 
    } 
    
    catch(NumberFormatException e) 
    { 
        return false; 
    }
    
    return true;
}

//ZROBIC ZAMYKANIE SYSTEMU
}
