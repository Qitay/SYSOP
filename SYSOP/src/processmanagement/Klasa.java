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
		
		nowy.addr=PsM.MM.pam.ZAPIS(" ");
		
		nowy.registerReturnValue=0;//nie dotyczy po prostu domyślna wartość ktora nic nie znaczy
		nowy.licznikRozkazow=0;//nie dotyczy init nie ma kodu programu
		
		
		nowy.s=stan.GOTOWY;
		ListaProcesow.add(nowy);
	}
	
	public void fork(proc parent)
	{
		System.out.print("Wywołano funkcję fork.");	
		
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
		
	
		
		
		
		//proces potomny wykonuje się w przestrzeni adresowej będącej kopią przestrzeni procesu rodzicielskiego
		
		nowy.addr=PsM.MM.pam.KLONUJ(parent.addr); //w arg parent.addr 
		
		
		//Funkcja fork tworzy deskryptor nowego procesu oraz kopię segmentu danych i stosu procesu macierzystego. 
		
		nowy.registerReturnValue=0;
		parent.registerReturnValue=Global.mpid;
		nowy.jestInitem = false;
		nowy.licznikRozkazow=parent.licznikRozkazow;
		nowy.s=stan.GOTOWY;
		
		ListaProcesow.add(nowy);
		
		//dopisać do listy procesów gotowych
		//ArrayList<proc> Ready_list = new ArrayList<proc>();
		//Ready_list.add(nowy);
		System.out.println(" Stworzono proces o nr PID "+nowy.pid);	
	}
	public void fork()
	{
	//MUSZ�? MIEĆ DOST�?P DO ZMIENNEJ TYPU proc AKTUALNEGO PROCESU KTÓRĄ STWORZY OSOBA ZAJMUJĄCA SI�? PROCESOREM
	//jest to zmienna "aktualny" w klasie "procesor"
		this.fork(PsM.PrM.procesor.aktualny);
		
	}

	public void exec(proc procesZm,String path)
	{
		System.out.println("Wywołano funkcję exec");	
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
					
			
			System.out.println("Wczytano z pliku następujące rozkazy:");
	        System.out.println(tmp); //sprawdzanie czy wszystko wczytało

			
			
			
			//przekazanie stringa do pam otrzymanie adresu gdzie został zapisany ten string:

			int adr =  PsM.MM.pam.NADPISZ(tmp, procesZm.addr);//czy w nowym miejscu czy na miejscu starego kodu programu??
	        
			
			procesZm.addr=adr; //zmienia addr w oryginale bo przekazywane przez referencje
			procesZm.licznikRozkazow=0;
			
			System.out.println("joł!");
			
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
		/*
		 int wait ( int *status );
		w zmiennej "status" jest zwracana informacja o sposobie zakończenia działania potomka;
		wartością zwracaną przez wait() jest PID procesu potomnego, który się zakończył
	    niech: status == HHLL (4 cyfry hex)

	    potomek zakończył się przez wywołanie "exit(y)"; wtedy HH=y, LL=0
	    potomek zakończył się z powodu sygnału; wtedy HH=0, 7-my bit LL zawiera 1 jeśli wygenerowano plik "core", bity 6-0 LL zawierają nr sygnału
		*/
	}
	public void exit()
	{
		//zamknięcie deskryptorów plików danego procesu
		
		//procesy potomne tego procesu są adoptowane przez init
		//void exit(int kod_zakończenia)
		//funkcja "exit()" - powoduje zakończenie procesu potomnego, przekazany parametr może być odczytany przez proces macierzysty.
		//proces musi zasygnalizować rodzicowi że zginął??? przez pipe???
		
	}
	
	
}
