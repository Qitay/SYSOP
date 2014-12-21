package filesystem;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;



	
class Iwezel{
	int typ_pliku;
	int i_block;
	boolean[] prawa;
	
	public Iwezel(int typ,int i_block){
		typ_pliku=typ;
		i_block=i_block;
	}
}

class Blok{
	char[] dane;
	int nr_nast_bloku;
	
	public Blok() {
		nr_nast_bloku=0;
		
	}
}
	

public class pliki 
{	

	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		
		ArrayList<Iwezel> wezly= new ArrayList<Iwezel>();
		Blok[] dysktwardy=new Blok[32];	
		for(int i=0;i<32;i++)dysktwardy[i]=new Blok();
		
		System.out.println("Podaj tresc pliku ");
		String zapis=scan.nextLine();
		dysktwardy[2].dane=zapis.toCharArray();
		System.out.println(dysktwardy[2].dane[0]);
		System.out.println(dysktwardy[2].dane[1]);
		System.out.println(dysktwardy[2].dane[2]);
	}
}
