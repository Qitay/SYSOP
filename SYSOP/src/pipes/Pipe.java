package pipes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import processmanagement.*;

public class Pipe {
	
	List<Pipe_Inode> InodeList; //
	File FileTab[]; 			// odpowiada tablicy plik�w 
								// przechowuje informacje czy mo�na czyta�, czy pisa� i odno�nik do Inode na InodeList
	
	int licznik; 				// zmienna pomocnicza ilo�� istniej�cych pipes
	
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
		
	}
	
	
	
	public int write(int fd[], String input, int len)
	{
		
		return 0;
	}
	
	public int read(int fd[], String output, int len)
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
		Scanner reading = new Scanner(System.in);
		String txt;
		System.out.println("Podaj tekst:");
		txt = reading.nextLine();
		//////////////////////////////////////////////////////////////
		
		int fd[] = new int [2];
		Pipe pipe = new Pipe();
		//////////////////////////////////////////////////////////////
		
		pipe.DoPipe(fd);
		
		pipe.write(fd, "asdfghjkl", 9);
		

		
	}
}
