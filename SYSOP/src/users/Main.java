package users;
import java.io.IOException;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		Log login = new Log();
		
		login.setRoot();
		
		// Menu
		int activeUid = 0; 	
		String x;
		
		
		do
		{
			do
			{
				if (activeUid == 0)
				{
					Root root = new Root();
					System.out.println("\r\nAktywny uzytkownik: root");
					System.out.print("CMD: "); x = sc.nextLine();
					
					switch (x)
					{
					case ("useradd"):
						System.out.print("Nazwa uzytkownika: "); String u = sc.nextLine();
						System.out.print("Haslo: "); String p = sc.nextLine();
						root.useradd(u, p);
						System.out.println("Dodano uzytkownika.");
						break;
						
					case ("usermodpa"):
						System.out.print("Nazwa uzytkownika: "); String us = sc.nextLine();
						System.out.print("Haslo: "); String pa = sc.nextLine();
						root.usermod_passwd(us, pa);
						System.out.println("Zmieniono haslo.");
						break;
						
					case ("userdel"):
						System.out.print("Nazwa uzytkownika: "); String usr = sc.nextLine();
						root.userdel(usr);
						break;
						
					case ("help"):
						Interfaces in = new Interfaces(); 
						in.printRootHelp();
						break;
						
					case ("cuser"):
						x = "cuser";
						break;
						
					case ("exit"):
						x = "exit";
						break;
					
					default:
						System.out.println("Zla komenda.");
						break;
					}
				}
				
				else
				{
					User user = new User(activeUid);
					System.out.println("\r\nAktywny uzytkownik: User");
					System.out.print("CMD: "); x = sc.nextLine();
					
					switch (x)
					{
					case ("userpassword"):
						System.out.print("Haslo: "); String p = sc.nextLine();
						user.usermod_passwd(p);
						System.out.println("Zmieniono haslo.");
						break;
					
					case ("help"):
						Interfaces in = new Interfaces(); 
						in.printRootHelp(); //zmienic na User
						break;
						
					case ("cuser"):
						x = "cuser";
						break;
						
					case ("exit"):
						x = "exit";
						break;
					
					default:
						System.out.println("Zla komenda");
						break;
						
					}
				}
			} while (!x.equals("cuser") && !x.equals("exit"));
			
			if (x!="exit")
			{
				String u;
				String p;
				boolean b;
				
				do
				{
					System.out.println("\r\n*LOGOWANIE");
					System.out.print("Nazwa uzytkownika: "); u = sc.nextLine();
					System.out.print("Haslo: "); p = sc.nextLine();
					b = login.verifyLogin(u, p);
				} while (b!=true);
				
				activeUid = login.giveUid(u);
			}
			
		} while (!x.contains("exit"));
		
		System.out.println("\r\nZakonczono dzialanie systemu.");
		sc.close();
	}

}
