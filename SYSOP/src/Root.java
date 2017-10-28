
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

			//	UZYTKOWNIK MOZE NALEZEC TYLKO DO JEDNEJ GRUPY!

public class Root 
{
	UserManagement UM;
	
	String userName = "root";
	int uid = 0;
	int gid = 0;
	
	
	//----------------------------FUNKCJE UZYTWKONIK--------------------------------
	
	void useradd (String name, String passwd) throws IOException		//OK dodawanie uzytkownika
	{
		if (checkUser(name) != true)
		{
			int nUID = linesNum("passwd.txt");
			int nGID = 1;	//grupa domyœlna
			
			String userData = name + ":" + passwd + ":" + nUID + ":" + nGID;
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("passwd.txt", true));
			bw.write("\r\n");
			bw.write(userData);
			bw.close();
			
			groupAddUser("default", name);
		}
	}
	
	void usermod_group (String name, int gid) throws IOException		//(?) zamiana grupy (wyjatki) + zmiana rzeczywista w group.txt
	{
		String[][] arr = readUsers();
		
		for (int i=0 ; i < arr.length ; i++)
		{
			if(arr[i][0].equals(name))
			{
				arr[i][3] = String.valueOf(gid);
			}
		}
		
		updateUsers(arr);
	}
	
	void usermod_passwd (String name, String passwd) throws IOException	//OK zmiana hasla
	{
		if(checkUser(name) == true)
		{
		String[][] arr = readUsers();
		
		for (int i=0 ; i < arr.length ; i++)
		{
			if(arr[i][0].equals(name))
			{
				arr[i][1] = passwd;
			}
		}
		
		updateUsers(arr);
		System.out.println("Zmieniono haslo.");
		
		}
		else
		{
			System.out.println("Nie ma takiego uzytkownika.");
		}
	}
	
	void userdel (String name) throws IOException						//(?) usun uzytkownika (usuwaj z grupy)
	{
		if (!name.equals("root") && checkUser(name)==true)
		{
			String[][] arr = readUsers();
			String[][] outArr = new String [(linesNum("passwd.txt"))-1][4];
			int n = 0;
			
			for (int i=0 ; i<arr.length ; i++)
			{
				if (arr[i][0].equals(name))
					n = i;
			}
			
			for (int i=0 ; i<arr.length ; i++)
			{
				for (int j=0 ; j<arr[0].length ; j++)
				{
					if (i<n)
						outArr[i][j] = arr[i][j];
					
					if (i>n)
					{
						outArr[i-1][j] = arr[i][j];
						outArr[i-1][2] = String.valueOf(i-1);
					}
				}
			}
			updateUsers(outArr);
			System.out.println("Usunieto uzytkownika!");
		}
		else
		{
			if(name.equals("root"))
				System.out.println("Nie mozna usunac uzytkownika root.");
			else
				System.out.println("Uzytkownik nie istnieje.");
		}
	}
	
	//------------------------------FUNKCJE GRUPY-----------------------------------
	
	void groupadd (String name) throws IOException						//OK dodaj grupe
	{
		if (checkGroup(name) != true)
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("group.txt", true));
			
			int gid = linesNum("group.txt");
			String groupData = name + ":" + gid + ":";
			
			bw.write("\r\n");
			bw.write(groupData);
			bw.close();
			
			System.out.println("Dodano grupe.");
		}
		else
		{
			System.out.println("Istnieje grupa o takiej nazwie.");
		}
	}
	
	void groupAddUser (String groupName, String userName) throws IOException	//OK dodaj uzytkownika do grupy
	{
		if (checkGroup(groupName) == true && checkUserInGroup(groupName,userName) == false && checkUser(userName) == true)
		{
			String[][] arr = readGroup();
			
			for (int i=0 ; i<arr.length ; i++)
			{
				if(arr[i][0].equals(groupName))
				{
					int n = (arr[i].length)+1;
					String[] newArr = new String[n];
					System.arraycopy(arr[i], 0, newArr, 0, arr[i].length);
					
					arr[i] = newArr;
					arr[i][n-1] = userName;
				}
			}
			
			updateGroup(arr);
			System.out.println("Dodano uzytkownika do grupy.");
		}
		else
		{
			if(checkGroup(groupName) == false)
				System.out.println("Nie ma takiej grupy.");
			else if(checkGroup(groupName) == true && checkUserInGroup(groupName, userName) == true)
				System.out.println("Istnieje uzytkownik o takiej nazwie w tej grupie.");
			
		}
	}
	
	void groupDelUser (String groupName, String userName) throws IOException
	{
		String[][] arr = readGroup();
		
		
	}
	
	void groupdel(String groupName) throws IOException					//(?) usun grupe (wyjatek)
	{
		if (groupName != "admin" || groupName != "default" )	//NullPointerException popraw!
		{
			String[][] arr = readGroup();
			int m = (linesNum("group.txt"))-1;
			String[][] outArr = new String [m][];
			
			int n=0;
			
			for (int i=0 ; i<arr.length ; i++)
			{
				if (arr[i][0].equals(groupName))
					n = i;
			}
			
			for (int i=0 ; i<arr.length ; i++)
			{
				for (int j=0 ; j<arr[i].length ; j++)
				{
					if (i<n)
					{
						outArr[i] = new String[arr[i].length];
						
						outArr[i][j] = arr[i][j];
					}
					
					if (i>n)
					{
						outArr[i-1] = new String[arr[i].length];
						
						outArr[i-1][j] = arr[i][j];
						outArr[i-1][1] = String.valueOf(i-1);
					}
				}
			}
			
			updateGroup(outArr);
		}
	}
	
	//-------------------------------INNE FUNKCJE-----------------------------------
	
	boolean checkUser(String name) throws IOException					//OK sprawdza czy istnieje uzytkownik
	{
		String[][] arr = readUsers();
		
		for (int i=0 ; i<arr.length ; i++)
		{
			if (arr[i][0].equals(name))
				return (true);
		}
		
		return (false);
	}
	
	boolean checkGroup(String name) throws IOException					//OK sprawdza czy istnieje grupa
	{
		String[][] arr = readGroup();
		
		for (int i=0 ; i<arr.length ; i++)
		{
			if (arr[i][0].equals(name))
				return (true);
		}
		
		return (false);
	}
	
	boolean checkUserInGroup (String groupName, String userName) throws IOException	//OK sprawdza czy uzytkownik jest w grupie
	{
		String[][] arr = readGroup();
		
		for (int i=0 ; i<arr.length ; i++)
		{
			if (arr[i][0].equals(groupName))
			{
				for (int j=2 ; j<arr[i].length ; j++)
				{
					if (arr[i][j].contains(userName))
						return (true);
				}
			}
		}
		return (false);
	}
	
	int linesNum(String file) throws IOException						//OK liczba linii pliku tekstwego
	{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		int n=0;
		
		while ((line = br.readLine()) != null)
		{
			n++;
		}
		
		br.close();
		return n;
	}

	
	String[] userData (String name) throws IOException					//OK znajduje uzytkownika i zwraca tablice [nazwa, haslo, UID, GID]
	{
		BufferedReader br = new BufferedReader(new FileReader("passwd.txt"));
		String text;
		String[] arr = null;
		String[] data = null;
		String user, password, uid, gid;
		
		while ((text = br.readLine())!=null)
		{
			arr = text.split(":");
			user = arr[0];
			password = arr[1];
			uid = arr[2];
			gid = arr[3];
			
			if(text.equals(name))
				data = arr;
		}
		
		br.close();
		return data;
	}
	
	String[][] readUsers() throws IOException							//OK zwraca wszystkich uzytkownikow w postaci tablicy 2D
	{
		BufferedReader br = new BufferedReader(new FileReader("passwd.txt"));
		int n = linesNum("passwd.txt");
		String[][] users = new String [n][4];
		String[] arr = null;
		String text;
		int i=0;
		
		while ((text = br.readLine())!=null)
		{
			arr = text.split(":");
			for (int j=0 ; j<4 ; j++)
				users[i][j] = arr[j];
			i++;
		}
		
		return users;
	}

	void updateUsers(String[][] arr) throws IOException					//OK wpisuje na nowo uzytkownikow do pliku
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("passwd.txt", false));
		int n = arr.length;
		int i=0;
		
		while(i < arr.length)
		{
			for (int j=0 ; j < arr[0].length ; j++)
			{
				bw.write(arr[i][j]);
				if(j<3)
					bw.write(":");
			}
			if(i<n-1)
				bw.write("\r\n");
			i++;
		}
		
		bw.close();
	}

	
	
	String[][] readGroup() throws IOException							//OK zwraca wszystkie grupy w postaci tablicy 2D
	{
		BufferedReader br = new BufferedReader(new FileReader("group.txt"));
		
		int n = linesNum("group.txt");
		int m;
		String[][] groups = new String [n][];
		String[] arr = null;
		String text;
		
		int i=0;
		while ((text = br.readLine())!=null)
		{
			text = text.replaceAll(",",":");
			arr = text.split(":");
			m = arr.length;
			groups[i] = new String[m];
			for (int j=0 ; j<m ; j++)
				groups[i][j] = arr[j];
			i++;
		}
		
		br.close();
		return groups;
	}

	void updateGroup(String[][] arr) throws IOException					//OK wpisuje na nowo grupy do pliku
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("group.txt", false));
		
		for (int i=0 ; i<arr.length ; i++)
		{
			for (int j=0 ; j<arr[i].length ; j++)
			{
				bw.write(arr[i][j]);
				
				if(j<2)
					bw.write(":");
				
				else if(j<(arr[i].length)-1)
					bw.write(",");
			}
			if(i<(arr.length)-1)
				bw.write("\r\n");
		}
		
		bw.close();
	}
}





