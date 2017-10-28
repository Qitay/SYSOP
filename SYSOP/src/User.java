
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class User 
{
	
	
	String userName;
	int uid;
	int gid;
	
	User (int uid) throws IOException									//OK konstruktor
	{
		String[][] arr = readUsers();
		
		for (int i=0 ; i<arr.length ; i++)
		{
			if (arr[i][2].contains(String.valueOf(uid)))
			{
				this.userName = arr[i][0];
				this.gid = Integer.parseInt(arr[i][3]);
			}
			
			this.uid = uid;
		}
		
	}
	
	//----------------------------FUNKCJE UZYTWKONIK--------------------------------
	
	void usermod_passwd (String passwd) throws IOException				//OK zmiana hasla
	{
		String[][] arr = readUsers();
		
		for (int i=0 ; i < arr.length ; i++)
		{
			if(arr[i][0].contains(userName))
			{
				arr[i][1] = passwd;
			}
		}
		
		updateUsers(arr);
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
