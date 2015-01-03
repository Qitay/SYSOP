package processmanagement;
//import java.util.ArrayList;
public class proc {

	int uid;
	int pid;
	int ppid;
	String stan;
	int nice;
	int licznikRozkazow; //adres następnego rozkazu mającego być wykanany czy int??
	int addr; //adres w bloku pamięci
	//ArrayList<Integer> pidyPotomkow = new ArrayList<Integer>();
	int registerReturnValue; //0 jeśli funkcja jest stworzona przez proces macierzusty lub nr pid potomka jeśli ta funkcja go stworzyła
}

