package processmanagement;

import java.util.Scanner;
public class Projekt {
	
public static void main(String[] args) {

		ProcessMaganement processM = new ProcessMaganement();
		ProcesorManagement procesorM = new ProcesorManagement();
		MemoryManagement MemoryM = new MemoryManagement();//----
		
		processM.PrM=procesorM;
		processM.MM=MemoryM;
		
		procesorM.PsM=processM;
		procesorM.MM=MemoryM;
		
		MemoryM.PrM=procesorM;
		MemoryM.PsM=processM;//-----
		
		
		
		
        System.out.println("Witamy w systemie");
		
        processM.klasa.init();
       
		while(true)
		{	
        String odczytano;
        Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od u≈ºytkownika
        odczytano = odczyt.nextLine();
        
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
        	System.out.println("Wszystkie istniej?ce procesy:");
        	for(int k=0;k<processM.klasa.ListaProcesow.size();k++)
        	{
        		
        		System.out.println("PID: "+ processM.klasa.ListaProcesow.get(k).pid);
				System.out.println("PPID: "+ processM.klasa.ListaProcesow.get(k).ppid);
				System.out.println("UID: "+ processM.klasa.ListaProcesow.get(k).uid);
				System.out.println("NICE: "+ processM.klasa.ListaProcesow.get(k).nice);
				System.out.println("STAN PROCESU: "+ processM.klasa.ListaProcesow.get(k).s);
				System.out.println("ADRES W PAMI¢CI: "+ processM.klasa.ListaProcesow.get(k).addr);
				System.out.println("LICZNIK ROZKAZÓW: "+ processM.klasa.ListaProcesow.get(k).licznikRozkazow);
				System.out.println("WARTOÂå ZWRACANA PRZEZ FORK: "+ processM.klasa.ListaProcesow.get(k).registerReturnValue);
				System.out.println("Numery PID potomków "+ processM.klasa.ListaProcesow.get(k).pidyPotomkow.toString());
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
        				System.out.println("WARTOÂå ZWRACANA PRZEZ FORK: "+ p.registerReturnValue);
        				System.out.println("Numery PID potomków "+ p.pidyPotomkow.toString());
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
        else if(odczytano.equals("EXECVE"))
        {
        	System.out.println("Podaj PID procesu któremu chcesz zmieniç wykonywany program:");
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
        				
        				
        				processM.klasa.exec(p,"/Users/Czerniawska/Desktop/rozkazy.txt");
        				jestTakiProces=true;
        				
        			}
        				
        		}	
        			
        		
        		if(jestTakiProces==false)
        		{
    				
        			System.out.println("Nie ma procesu o takim PID!");
        				
        		}
        	
        	
        }
        
        
        
        }
        
        
        
        }
        
        
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


}
