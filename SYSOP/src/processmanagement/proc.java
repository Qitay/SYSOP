package processmanagement;
import java.util.ArrayList;
public class proc {

	int uid;
	int pid;
	int ppid;
	//String stan;
	stan s;
	int nice;
	int cpu;
	int licznikRozkazow; // wskazuje adres następnego rozkazu do wykonania  
	int addr; //adres w bloku pamięci
	int ExitStatus; //1 oznacza poprawne zako�czenie procesu
	int ChildExitStatus;//exit status potomka
	//ArrayList<Integer> tablicaDeskryptor�w = new ArrayList<Integer>();
	ArrayList<Integer> pidyPotomkow;
	//ArrayList<Integer> pidyPotomkowAdoptowanych;
	int registerReturnValue; //0 je�li funkcja jest stworzona przez proces macierzusty lub nr pid potomka je�li ta funkcja go stworzyła
	boolean jestInitem;
	
	Object [] cpu_stan; 
	
	
}

