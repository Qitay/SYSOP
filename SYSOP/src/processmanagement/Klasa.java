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
		nowy.uid = 0;
		nowy.nice = 0;
		nowy.jestInitem = true;
		
		nowy.registerReturnValue=0;//nie dotyczy po prostu domyslna wartosc ktora nic nie znaczy
		nowy.licznikRozkazow=0;//nie dotyczy init nie ma kodu programu
		
		
		nowy.s=stan.GOTOWY;
		ListaProcesow.add(nowy);
	}
	
	public void fork(proc parent)
	{
		System.out.print("Wywolano funkcje fork.");	
		
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
		
		
		//proces potomny wykonuje sie w przestrzeni adresowej bedacej kopia przestrzeni procesu rodzicielskiego
		//dodac alokowanie pamieci !!!
		//Funkcja fork tworzy deskryptor nowego procesu oraz kopie segmentu danych i stosu procesu macierzystego. 
		
		nowy.registerReturnValue=0;
		parent.registerReturnValue=Global.mpid;
		nowy.jestInitem = false;
		nowy.licznikRozkazow=parent.licznikRozkazow;
		nowy.s=stan.GOTOWY;
		
		ListaProcesow.add(nowy);
		
		//dopisac do listy procesow gotowych
		//ArrayList<proc> Ready_list = new ArrayList<proc>();
		//Ready_list.add(nowy);
		System.out.println(" Stworzono proces o nr PID "+nowy.pid);	
	}
	public void fork()
	{
	//MUSZA MIEC DOSTEP DO ZMIENNEJ TYPU proc AKTUALNEGO PROCESU KTORA STWORZY OSOBA ZAJMUJACA SIE PROCESOREM
	//jest to zmienna "aktualny" w klasie "procesor"
		this.fork(PsM.PrM.procesor.aktualny);
		
	}

	public void exec(proc procesZm,String path)
	{
		System.out.println("Wywolano funkcje exec");	
		try {
			
			
			//  "/Users/Czerniawska/Desktop/rozkazy.txt"
			
			String tmp = null;
			
			BufferedReader br = new BufferedReader(new FileReader(path));
			while ((br.readLine()) != null) {
				tmp = tmp + br.readLine();
			}
			br.close();
			
			//przekazanie stringa do pam otrzymanie adresu gdzie zostal‚ zapisany ten string:
			//int adr = obiekt.ZAPIS(tmp); <- zrobic modul‚ zarzPam i na obiekcie wywolac bo ta funkcja nie jest statyczna!!
			
			System.out.println("Wczytano z pliku nastepujace rozkazy:");
	        System.out.println(tmp); //sprawdzanie czy wszystko wczytalo

			
			
			int adr = 893; //proteza pamieci zwraca jakis numer oznaczajacy adres
			
			
			
			procesZm.addr=adr; //zmienia addr w oryginale bo przekazywane przez referencje
			procesZm.licznikRozkazow=0;
			
			System.out.println("joÅ‚!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public void wait1()
	{
		
		
		
		
	}
	public void exit()
	{
		
		
		
	}
	
	
}
