package users;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException 
	{
		Interfaces menu = new Interfaces();
		Scanner sc = new Scanner(System.in);
		
		// Nadanie hasla administratorowi
		System.out.print("Podaj haslo dla konta administratora: ");
		String p = sc.nextLine();
		Root admin = new Root();
		admin.usermod_passwd("root", p);
		
		
		// Menu
		int activeUid = 0;
		String x;
		
		// dodac switch'a jak nizej tyle ze z domyslnym adminem???
		
		do
		{
			if (activeUid == 0)
			{
				Root root = new Root();
				System.out.print("Cmd:\t"); x = sc.nextLine();
				
				switch (x)
				{
				case("help"):
					Interfaces in = new Interfaces(); in.printRootHelp();
				}
			}
			
			else
			{
				// dodac opcje logowania
				User user = new User();
			}
		} while (x!="exit");
	}

}
