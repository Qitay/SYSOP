package pipes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import processmanagement.*;

public class Pipe {
	
	List<Pipe_Inode> InodeList; //
	File FileTab[]; 			// odpowiada tablicy plików 
								// przechowuje informacje czy mo¿na czytaæ, czy pisaæ i odnoœnik do Inode na InodeList
	
	int licznik; 				// zmienna pomocnicza iloœæ istniej¹cych pipes
	
	public Pipe()
	{
		InodeList = new LinkedList<Pipe_Inode>();
		FileTab = new File[32];
		for (int i=0; i<31; i++)
		{
			FileTab[i] = null;
		}
		licznik = 0;
	}
	
	public int DoPipe(int fd[])
	{
		get_pipe_inode();
		//	przydziel nowy i-wezel z urzadzenia do przechowywania laczy
		// 	(za pomoca funkcji get_pipe_inode);
		
		get_filetable_positions();
		//	znajdz dwie wolne pozycje w tablicy plikow i przydziel pierwsza do
		//	czytania a druga do pisania 
		// 	zainicjuj znalezione pozycje w tablicy plikow, by wskazywaly na nowy i-wezel
		
      	
		//	znajdz dwie wolne pozycje w tablicy deskryptorow procesu i zainicjuj
		// 	je, tak by wskazywaly na odpowiednie pozycje w tablicy plikow, 
		// 	podstawiajac od razu odpowiednie wartosci w argumencie fd
		//	(fd[0]= deskryptor do czytania, fd[1]= deskryptor do pisania);
		
      	fd[0] = licznik;
      	fd[1] = licznik + 1;
		
		return 0;
	};
	
	public void get_pipe_inode()
	{
		Pipe_Inode inode = new Pipe_Inode();
		InodeList.add( inode );
	}
	public void get_filetable_positions()
	{
		File fd0 = new File();
		fd0.loc = licznik;
		fd0.readwrite = false;
		File fd1 = new File();
		fd1.loc = licznik;
		fd1.readwrite = true;
		for (int i=0; i<31; i++)
		{
			if (FileTab[i] == null)
			{
				FileTab[i] = fd0;
				FileTab[i+1] = fd1;
				break;
			}
		}
		licznik = 0;	
	}
	
	public int write(int fd, String input)
	{
		if(FileTab[fd].readwrite == true)
		{
			int tmp;
			tmp = FileTab[fd].loc;
			
			return 0;
		}
		else
		{
			System.out.println("B³¹d zapisu: pole read-only");
			return 1;
		}
	}
		
		
	public int read(int fd, String output)
	{
		
		
		return 0;
	}
	
	public int close(int fd[])
	{
		
		return 0;
	}
	
	
	// MAIN MAIN MAIN MAIN MAIN MAIN //
	
	public static void main(String[] args)
	{

		int fd[] = new int [2];
		
		Pipe pipe = new Pipe();
		pipe.DoPipe(fd);
		
		//////////////// WRITE ///////////////////////////////////////
		Scanner reading = new Scanner(System.in);
		String txt;
		System.out.println("Funkcja WRITE - Podaj dane do wczytania:");
		txt = reading.nextLine();
		pipe.write(fd[0], txt);
		//////////////////////////////////////////////////////////////
		
		///////////////// READ ///////////////////////////////////////
		String output = null;
		pipe.read(fd[1], output);
		System.out.println("Funkcja READ - Odczytano dane:" + output);
		
		
		

		
	}
}
