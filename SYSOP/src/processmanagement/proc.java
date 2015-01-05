package processmanagement;
import java.util.ArrayList;
public class proc {

	int uid;
	int pid;
	int ppid;
	//String stan;
	stan s;
	int nice;
	int licznikRozkazow; // wskazuje adres następnego rozkazu do wykonania  
	int addr; //adres w bloku pamięci
	
	ArrayList<Integer> tablicaDeskryptorów = new ArrayList<Integer>();
	
	//ArrayList<Integer> pidyPotomkow = new ArrayList<Integer>();
	int registerReturnValue; //0 jeśli funkcja jest stworzona przez proces macierzusty lub nr pid potomka jeśli ta funkcja go stworzyła
	boolean jestInitem;
}

