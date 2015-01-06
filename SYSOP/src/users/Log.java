package users;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Log 
{
	// Login bedzie sprawdzal uzytkownika i haslo w pliku tekstowym, a nastepnie
	// tworzyl obiekt i dawal parametry konstruktorowi User (chyba ze tworzymy Root)
	
	boolean verifyLogin (String userName, String passwd) throws IOException		//zwraca bool
	{
		String[] arr = userData(userName);
		boolean b;
		
	
		if (arr[0].equals(userName) && arr[1].equals(passwd))
		{
			b = true;
		}
		else
		{
			b = false;
		}
		
		return (b);
	}
	
	int giveUid (String userName) throws IOException
	{
		int uid;
		String[] data;
		
		data = userData(userName);
		uid = Integer.parseInt(data[2]);
		
		return (uid);
	}
	
	void setRoot () throws IOException
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Ustaw haslo administratora: ");
		String p = sc.nextLine();
		Root admin = new Root();
		admin.usermod_passwd("root", p);
	}
	
	//-------------------------------INNE FUNKCJE-----------------------------------
	

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

	
}
