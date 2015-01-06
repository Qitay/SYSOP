package users;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;


public class Root 
{
	String userName = "root";
	int uid = 0;
	int gid = 0;
	
	
	//----------------------------FUNKCJE UZYTWKONIK--------------------------------
	
	void useradd (String name, String passwd) throws IOException		//OK dodawanie uzytkownika
	{
		int nUID = linesNum("passwd.txt");
		int nGID = 1;	//grupa domyœlna
		
		String userData = name + ":" + passwd + ":" + nUID + ":" + nGID;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("passwd.txt", true));
		bw.write("\r\n");
		bw.write(userData);
		bw.close();
	}
	
	void usermod_group (String name, int gid) throws IOException		//OK zamiana grupy (wyjatki)
	{
		String[][] arr = readUsers();
		
		for (int i=0 ; i < arr.length ; i++)
		{
			if(arr[i][0].contains(name))
			{
				arr[i][3] = String.valueOf(gid);
			}
		}
		
		updateUsers(arr);
	}
	
	void usermod_passwd (String name, String passwd) throws IOException	//OK zmiana hasla
	{
		String[][] arr = readUsers();
		
		for (int i=0 ; i < arr.length ; i++)
		{
			if(arr[i][0].contains(name))
			{
				arr[i][1] = passwd;
			}
		}
		
		updateUsers(arr);
	}
	
	void userdel (String name) throws IOException						//OK usun uzytkownika
	{
		if (name != "root" && checkUser(name)==true)
		{
			String[][] arr = readUsers();
			String[][] outArr = new String [(linesNum("passwd.txt"))-1][4];
			int n = 0;
			
			for (int i=0 ; i<arr.length ; i++)
			{
				if (arr[i][0].contains(name))
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
			if(name == "root")
				System.out.println("Nie mozna usunac uzytkownika root.");
			else
				System.out.println("Uzytkownik nie istenieje.");
		}
	}
	
	//------------------------------FUNKCJE GRUPY-----------------------------------
	
	void groupadd (String name) throws IOException						//OK dodaj grupe
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter("group.txt", true));
		
		int gid = linesNum("group.txt");
		String groupData = name + ":" + gid + ":";
		
		bw.write("\r\n");
		bw.write(groupData);
		bw.close();
	}
	
	void groupAddUser (String groupName, String userName) throws IOException	//OK dodaj uzytkownika do grupy (wyjatek: czy istnieje grupa)
	{
		String[][] arr = readGroup();
		
		for (int i=0 ; i<arr.length ; i++)
		{
			if(arr[i][0].contains(groupName))
			{
				int n = (arr[i].length)+1;
				String[] newArr = new String[n];
				System.arraycopy(arr[i], 0, newArr, 0, arr[i].length);
				
				arr[i] = newArr;
				arr[i][n-1] = userName;
			}
		}
		
		updateGroup(arr);
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
				if (arr[i][0].contains(groupName))
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
			if (arr[i][0].contains(name))
				return (true);
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
			
			if(text.contains(name))
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





