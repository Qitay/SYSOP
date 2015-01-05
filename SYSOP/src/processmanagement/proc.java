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
	int licznikRozkazow; // wskazuje adres nastÄ™pnego rozkazu do wykonania  
	int addr; //adres w bloku pamiÄ™ci
	int ExitStatus; //1 oznacza poprawne zakoÄczenie procesu
	int ChildExitStatus;//exit status potomka
	//ArrayList<Integer> tablicaDeskryptor—w = new ArrayList<Integer>();
	ArrayList<Integer> pidyPotomkow;
	//ArrayList<Integer> pidyPotomkowAdoptowanych;
	int registerReturnValue; //0 jeæli funkcja jest stworzona przez proces macierzusty lub nr pid potomka jeæli ta funkcja go stworzyÅ‚a
	boolean jestInitem;
	
	Object [] cpu_stan; 
	
	
}

