package pipes;

public class Pipe {
	
	public int pipe(int fd)
	{
	/*	sprawdzenie poprawnosci parametru fd;
	  
   		przydziel nowy i-wezel z urzadzenia do przechowywania laczy
      	(za pomoca funkcji get_pipe_inode);
   		
   		znajdz dwie wolne pozycje w tablicy plikow i przydziel pierwsza do
      	czytania a druga do pisania fd[0] i fd[1];
      	
   		znajdz dwie wolne pozycje w tablicy deskryptorow procesu i zainicjuj
      	je, tak by wskazywaly na odpowiednie pozycje w tablicy plikow, 
      	podstawiajac od razu odpowiednie wartosci w argumencie fd
      	(fd[0]= deskryptor do czytania, fd[1]= deskryptor do pisania);
      	
   		zainicjuj znalezione pozycje w tablicy plikow, by wskazywaly na nowy i-wezel;
   		
   		ustaw flagi i wskaznik do struktury z operacjami w znalezionych pozycjach
      	w tablicy plikow odpowiednio do czytania i pisania;
    */
		return 0;
	};
	
	public int write(int fd)
	{
		return 0;
	}
	
	public int read(int fd)
	{
		return 0;
	}
	
	public int close(int fd)
	{
		return 0;
	}
	
	
	// MAIN MAIN MAIN MAIN MAIN MAIN //
	
	public static void main(String[] args)
	{
		System.out.println("");
		
	}
}
