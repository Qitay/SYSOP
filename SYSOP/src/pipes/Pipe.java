package pipes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import processmanagement.*;

public class Pipe {
	
	List<Pipe_Inode> InodeList; //
	File FileTab[]; 			// odpowiada tablicy plików 
								// przechowuje informacje czy mo¿na czytaæ, czy pisaæ i odnoœnik do Inode na InodeList
	
	int FileTabcount; 			// zmienna pomocnicza iloœæ istniej¹cych deskryptorów	
	int InodeListcount;			// zmienna pomocnicza iloœæ istniej¹cych pipes
	
	public Pipe()
	{
		InodeList = new LinkedList<Pipe_Inode>();
		FileTab = new File[32];
		for (int i=0; i<31; i++)
		{
			FileTab[i] = null;
		}
		FileTabcount = 0;
		InodeListcount = 0;
	}
	
	public int[] DoPipe()
	{
		int fd[]= new int[2];
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
		
      	fd[0] = FileTabcount;
      	fd[1] = FileTabcount + 1;
      	FileTabcount = FileTabcount +2;
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
		fd0.readwrite = false;
		File fd1 = new File();
		fd1.loc = InodeListcount;
		fd1.readwrite = true;
		InodeListcount++;
		for (int i=0; i<31; i++)
		{
			if (FileTab[i] == null && FileTab[i+1] == null )
			{
				FileTab[i] = fd0;
				FileTab[i+1] = fd1;
				break;
			}
		}
	}
	
	public int write(int fd, String input)
	{
		if(FileTab[fd].readwrite == true)
		{
			
			Pipe_Inode tmp = InodeList.get(FileTab[fd-1].loc);
			tmp.buff = input;
			InodeList.set(FileTab[fd].loc, tmp);
			
			return 1;
		}
		else
		{
			System.out.println("B³¹d zapisu: pole read-only");
			return 0;
		}
	}
		
		
	public String read(int fd)
	{
		if(FileTab[fd].readwrite == false)
		{
			String output = InodeList.get(FileTab[fd].loc).buff;
			return output;	
		}
		else
		{
			System.out.println("B³¹d odczytu: pole write-only");
			return null;
		}
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
		fd = pipe.DoPipe();
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
	}
}
