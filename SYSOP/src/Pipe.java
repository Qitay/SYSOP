

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;



public class Pipe {
	
	StreamsManagement SM;		// klasa zbiorcza
	
	List<Pipe_Inode> InodeList; //
	File FileTab[]; 			// odpowiada tablicy plików 
								// przechowuje informacje czy można czytać, czy pisać i odnośnik do Inode na InodeList
	 
	int FileTabcount; 			// zmienna pomocnicza ilość istniejących deskryptorów	
	int InodeListcount;			// zmienna pomocnicza ilość istniejących pipes
	
	public Pipe()
	{
		InodeList = new LinkedList<Pipe_Inode>();
		FileTab = new File[32];
		for (int i=0; i<31; i++)
		{
			FileTab[i] = new File();
		}
		FileTabcount = 0;
		InodeListcount = 0;
	}
	
	public int[] DoPipe(int pid)
	{
		if(SM.PsM.klasa.ListaProcesow.get(pid-1).s==stan.ZAKONCZONY)
		{
			
			
		System.out.print("Nie mozna wywolac funkcji pipe na ju� zakonczonym procesie!");	
		return null;
		}
		
		int fd[]= new int[2];
		get_pipe_inode();
		//	przydziel nowy i-wezel z urzadzenia do przechowywania laczy
		// 	(za pomoca funkcji get_pipe_inode);
		
		get_filetable_positions();
		//	znajdz dwie wolne pozycje w tablicy plikow i przydziel pierwsza do
		//	czytania a druga do pisania 
		// 	zainicjuj znalezione pozycje w tablicy plikow, by wskazywaly na nowy i-wezel
      	fd[0] = FileTabcount;
      	fd[1] = FileTabcount + 1;
      	FileTabcount = FileTabcount +2;
      	System.out.println("Przypisane deskryptory: "+ fd[0] +" - read, "+ fd[1] + " - write.");
      	
      	
      	get_fdtab_positions(fd,pid);
      	
		//	znajdz dwie wolne pozycje w tablicy deskryptorow procesu i zainicjuj
		// 	je, tak by wskazywaly na odpowiednie pozycje w tablicy plikow, 
		// 	podstawiajac od razu odpowiednie wartosci w argumencie fd
		//	(fd[0]= deskryptor do czytania, fd[1]= deskryptor do pisania);
		return fd;
	};
	
	public void get_pipe_inode()
	{
		Pipe_Inode inode = new Pipe_Inode();
		InodeList.add( inode );
	}
	public void get_filetable_positions()
	{
		File fd0 = new File();
		fd0.loc = InodeListcount;
		fd0.write = false;
		File fd1 = new File();
		fd1.loc = InodeListcount;
		fd1.read = false;
		InodeListcount++;
		for (int i=0; i<31; i++)
		{
			if (FileTab[i].loc == -1 && FileTab[i+1].loc == -1 )
			{
				FileTab[i] = fd0;
				FileTab[i+1] = fd1;
				break;
			}
		}
	}
	
	public void get_fdtab_positions(int fd[], int pid)
	{
		SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.add(fd[0]);
		System.out.println("Dodano deskryptory do procesu o PID "+ pid +"");
		SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.add(fd[1]);
		System.out.println("");
	}
	
	
	/*public void get_fdtab_positions(int fd[])
	{
		SM.PsM.klasa.ListaProcesow.get(pid aktywny proces).fdtab.add(fd[0]);
		SM.PsM.klasa.ListaProcesow.get(pid aktywny proces).fdtab.add(fd[1]);
	}
	*/
	public int write(int fd, String input, int pid)
	{
		if(SM.PsM.klasa.ListaProcesow.get(pid-1).s==stan.ZAKONCZONY)
		{
			
			
		System.out.print("Nie mozna wywolac funkcji write na juz zakonczonym procesie!");	
		return 0;
		}
		
		for(int i=0;i<SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.size();i++)
		{
			if(SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.get(i)== fd) 
			{//sprawdza czy istnieje taki deskryptor w tablicy deskryptorow
				
				if(FileTab[fd].write == true)
				{//sprawdza czy do pola mozna pisac (true - mozna);
					
					Pipe_Inode tmp = InodeList.get(FileTab[fd-1].loc);
					tmp.buff = input;
					InodeList.set(FileTab[fd].loc, tmp);
					System.out.println("Zapisano:"+ input);
					return 1;
				}
				else
				{
					System.out.println("Blad zapisu: pole read-only");
					return 0;
				}
			}
		}
		System.out.println("Blad zapisu: deskryptor nie istnieje");
		return 0;
		
	}
		
	public String read(int fd, int pid)
	{
		if(SM.PsM.klasa.ListaProcesow.get(pid-1).s==stan.ZAKONCZONY)
		{
		System.out.print("Nie mozna wywolac funkcji read na ju� zakonczonym procesie!");	
		return null;
		}
		for(int i=0;i<SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.size();i++)
		{//sprawdza czy istnieji taki deskryptor w tablicy deskryptorow
			
			if(SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.get(i)== fd) 
			{//sprawdza czy istnieji taki deskryptor w tablicy deskryptorow
				
				if(FileTab[fd].read == true)
				{
					String output = InodeList.get(FileTab[fd].loc).buff;
					InodeList.get(FileTab[fd].loc).buff=null; ////!!!
					return output;	
				}
				else
				{
					System.out.println("Blad odczytu: pole write-only");
					return null;
				}
			}
		}
		System.out.println("Blad zapisu: deskryptor nie istnieje");
		return null;
	}
	
	public int close(int fd, int pid)
	{
		{
			for(int i=0;i<SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.size();i++)
			{
				if(SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.get(i).equals(fd) )
				{
					SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.remove(i);
					System.out.println("Zamknięto deskryptor "+ fd +" dla procesu o PID " +pid);
					Fclose(fd, pid);
					return 0;
				}
			}
		}
		System.out.println("Niepowodzenie przy zamykaniu deskryptora: deskryptor nie istnieje");
		return 1;
	}
	
	void closeall (int pid)
	{
		int fd = 0;
		for(int i=SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.size()-1;i>=0;i--)
		{
			fd = SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.get(i);
			SM.PsM.klasa.ListaProcesow.get(pid-1).fdtab.remove(i);
			System.out.println("Zamknieto deskryptor "+ fd +" dla procesu o PID " +pid);
			Fclose(fd, pid);	
		}
	}
	
	
	
	
	
	
	
	
	void Fclose(int fd, int pid)
	{
		// Szukanie czy sa jeszcze odpowiadajace mu deskryptory
		for(int j=0;j<SM.PsM.klasa.ListaProcesow.size();j++)
		{
			for(int i=0;i<SM.PsM.klasa.ListaProcesow.get(j).fdtab.size();i++)
			{
				if(SM.PsM.klasa.ListaProcesow.get(j).fdtab.get(i).equals(fd) )
				{
					System.out.println(" n");
					return;
				}
			}
		}
		
		// Jesli nie ma zwalnia odpowiednie pole w tablicy plików
		int loc = FileTab[fd].loc;
		FileTab[fd].loc = -1;
		FileTabcount--;
		
		// Sprawdza czy istnieja jeszcze istniejace odniesienia do lokacji inode
		for (int i=0; i<31; i++)
		{
			if(FileTab[i].loc == loc)
				return;
		}
		
		//usuwa inode
		InodeList.remove(loc);
		InodeListcount--;
		
		
	}
	// MAIN MAIN MAIN MAIN MAIN MAIN //
	
	/*public static void main(String[] args)
	{

		int fd[] = new int [2];
		int fd2[] = new int [2];

		
		Pipe pipe = new Pipe();
		fd = pipe.DoPipe();
		fd2 = pipe.DoPipe();
		
		//////////////// WRITE ///////////////////////////////////////
		Scanner reading = new Scanner(System.in);
		String txt;
		System.out.println("Funkcja WRITE - Podaj dane do wczytania:");
		txt = reading.nextLine();
		pipe.write(fd[1], txt);
		//////////////////////////////////////////////////////////////
		
		
		///////////////// READ ///////////////////////////////////////
		String output = "a";
		output = pipe.read(fd[0]);
		System.out.println("Funkcja READ - Odczytano dane:"+ output);
		//////////////////////////////////////////////////////////////
	}*/
}
