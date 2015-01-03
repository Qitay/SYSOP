package filesystem;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;



///////////////////////////////////////	
class Iwezel{
	int typ_pliku;
	int i_block;
	boolean[] prawa;
	String nazwa;
	
	//CREATE////////////////////////////////////////////////////
	public Iwezel(Blok[] dysk){
		System.out.println("Nazwa pliku:");
		Scanner scan=new Scanner(System.in);
		String zapis=scan.nextLine();
		nazwa=zapis;
		typ_pliku=1;
		for(int i=0;i<32;i++)
			if(dysk[i].zajety==false){
				i_block=i;
				dysk[i].zajety=true;
				break;
			}
	}

	//WRITE/////////////////////////////////////////////////////
	public void write(Blok[] dysk,ArrayList<Iwezel> wezly){
		System.out.println("Do ktorego pliku chcesz napisac");
		Scanner scan=new Scanner(System.in);
		String zapis=scan.nextLine();
		int nr = Integer.parseInt(zapis);
		System.out.println("Co chcesz zapisac");
		zapis=scan.nextLine();
		write_2(dysk,wezly,zapis,wezly.get(nr).i_block);
	}
	
	public void write_2(Blok[] dysk,ArrayList<Iwezel> wezly,String zapis,int nr){
		if(zapis.length()<8)dysk[nr].dane=zapis.toCharArray();
			else{
				dysk[nr].dane=zapis.substring(0, 8).toCharArray();
				zapis=zapis.substring(8);
				for(int i=0;i<32;i++)
					if(dysk[i].zajety==false){
						dysk[i].zajety=true;
						dysk[nr].nr_nast_bloku=i;
						break;
					}
				write_2(dysk,wezly,zapis,dysk[nr].nr_nast_bloku);
			}
	}
	
	//READ//////////////////////////////////////////////////////
	public void read(Blok[] dysk,ArrayList<Iwezel> wezly){
		System.out.println("Ktory plik chcesz odczytac");
		Scanner scan=new Scanner(System.in);
		String zapis=scan.nextLine();
		int nr = Integer.parseInt(zapis);
		read_2(dysk,wezly,wezly.get(nr).i_block);
		System.out.println();
	}
	
	public void read_2(Blok[] dysk,ArrayList<Iwezel> wezly,int nr){
		if(dysk[nr].nr_nast_bloku==0)
		System.out.print(dysk[nr].dane);
		else{
			System.out.print(dysk[nr].dane);
			nr=dysk[nr].nr_nast_bloku;
			read_2(dysk,wezly,nr);
		}
	}
	////To samo co 2 poprzednie tylko zwraca string zamiast wypisywaæ na konsoli
	public String read_s(Blok[] dysk,ArrayList<Iwezel> wezly){
		String napis ="";
		System.out.println("Ktory plik chcesz odczytac");
		Scanner scan=new Scanner(System.in);
		String zapis=scan.nextLine();
		int nr = Integer.parseInt(zapis);
		napis=read_2_s(dysk,wezly,wezly.get(nr).i_block,napis);
		
		return napis;
	}
	public String read_2_s(Blok[] dysk,ArrayList<Iwezel> wezly,int nr,String napis){
		if(dysk[nr].nr_nast_bloku==0){
			napis+=new String(dysk[nr].dane);
			return napis;
		}
		return new String(dysk[nr].dane)+read_2_s(dysk,wezly,dysk[nr].nr_nast_bloku,napis);		
	}
	
	//ZAWARTOSCDYSKU////////////////////////////////////////////
	public void zawdysk(Blok[] dysk,ArrayList<Iwezel> wezly){
		for(int i=0;i<32;i++){
			System.out.print("Blok "+i+":");
			System.out.println(dysk[i].dane);
		}
	}
	
	//PLIKI/////////////////////////////////////////////////////
	public void wyswpliki(Blok[] dysk,ArrayList<Iwezel> wezly){
		for(int i=0;i<wezly.size();i++){
			System.out.print("Plik "+i+":");
			System.out.println(wezly.get(i).nazwa);
		}
	}
}
//////////////////////////////////////////////////////////////////////////////////////////////////
class Blok{
	char[] dane;
	int nr_nast_bloku;
	boolean zajety;
	public Blok() {
		dane=" ".toCharArray();
		nr_nast_bloku=0;
		zajety=false;
	}
}	
/////////////////////////////////////////////////////////////////////////////////////////////////////////
public class Pliki 
{	

	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		int x = 0;
		
		ArrayList<Iwezel> wezly= new ArrayList<Iwezel>();
		Blok[] dysktwardy=new Blok[32];	
		for(int i=0;i<32;i++)dysktwardy[i]=new Blok();

		while(x!=6){
			System.out.println("1-stworz plik");
			System.out.println("2-wyswietl plik");
			System.out.println("3-zapisz do pliku");
			System.out.println("4-wyswietl zawartosc dysku");
			System.out.println("5-wyswietl nazwy plikow");
			System.out.println("6-wyjdz");
			System.out.println("7-wyswietl plik_s");
			System.out.println("Co chcesz zrobic:");
			String zapis=scan.nextLine();
			x = Integer.parseInt(zapis);
			if(x==1){
				wezly.add(new Iwezel(dysktwardy));
			}
			if(x==2){
				wezly.get(0).read(dysktwardy, wezly);
			}
			if(x==3){
				wezly.get(0).write(dysktwardy, wezly);
			}
			if(x==4){
				wezly.get(0).zawdysk(dysktwardy, wezly);
			}	
			if(x==5){
				wezly.get(0).wyswpliki(dysktwardy, wezly);
			}
			if(x==7){
				System.out.println(wezly.get(0).read_s(dysktwardy, wezly));
			}
		}
	}
}
