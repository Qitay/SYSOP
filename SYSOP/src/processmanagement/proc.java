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
	int priorytet;
	int bajt;
	int licznikRozkazow; // wskazuje adres następnego rozkazu do wykonania  
	int addr; //adres w bloku pamięci
	int ExitStatus; //1 oznacza poprawne zako�czenie procesu
	int ChildExitStatus;//exit status potomka
	//ArrayList<Integer> tablicaDeskryptor�w = new ArrayList<Integer>();
	ArrayList<Integer> pidyPotomkow;
	//ArrayList<Integer> pidyPotomkowAdoptowanych;
	int registerReturnValue; //FORK: 0 je�li funkcja jest stworzona przez proces macierzusty lub nr pid potomka je�li ta funkcja go stworzy�a
							 //WAIT: -1 je�li proces nie mia� dzieci, je�li mia� i jakie� zako�czy�o to jego pid
	boolean jestInitem;
	
	Object [] cpu_stan; 
	
	
}

