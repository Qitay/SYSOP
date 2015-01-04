package pipes;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import processmanagement.*;

public class Pipe {
	
	Pipe_Inode inode;
	
	
	public Pipe()
	{
		
	}
	
	public int create(int fd[])
	{
		//  sprawdzenie poprawnosci parametru fd;
		
		inode = get_pipe_inode();
		//	przydziel nowy i-wezel z urzadzenia do przechowywania laczy
		// 	(za pomoca funkcji get_pipe_inode);
		
		
		//	znajdz dwie wolne pozycje w tablicy plikow i przydziel pierwsza do
		// 	czytania a druga do pisania ;
      	
		//	znajdz dwie wolne pozycje w tablicy deskryptorow procesu i zainicjuj
		// 	je, tak by wskazywaly na odpowiednie pozycje w tablicy plikow, 
		// 	podstawiajac od razu odpowiednie wartosci w argumencie fd
		//	(fd[0]= deskryptor do czytania, fd[1]= deskryptor do pisania);
      	
		//	zainicjuj znalezione pozycje w tablicy plikow, by wskazywaly na nowy i-wezel;
   		
		//	ustaw flagi i wskaznik do struktury z operacjami w znalezionych pozycjach
		//	w tablicy plikow odpowiednio do czytania i pisania;
		
		return 0;
	};
	
	public Pipe_Inode get_pipe_inode()
	{
		Pipe_Inode inode = new Pipe_Inode();
		
		return inode;
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
		List<Pipe_Inode> InodeList = new LinkedList<Pipe_Inode>();
		int fd[] = new int [2];
		Pipe pipe = new Pipe();
		//////////////////////////////////////////////////////////////
		
		
		pipe.write(fd, "asdfghjkl", 9);
		

		
	}
}
