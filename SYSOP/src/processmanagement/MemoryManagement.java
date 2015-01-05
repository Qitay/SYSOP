package processmanagement;

import memory.Bity_odniesienia;
import memory.Pamiec;
import memory.Plik_wymiany;

public class MemoryManagement { //----

	Bity_odniesienia bity_o;
	Plik_wymiany plik_w;
	Pamiec pam;
	ProcessMaganement PsM;
	
	
	MemoryManagement(){
		pam = new Pamiec();	
	}
}
