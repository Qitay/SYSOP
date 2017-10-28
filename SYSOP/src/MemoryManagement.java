
public class MemoryManagement { //----

	//Bity_odniesienia bity_o;
	//Plik_wymiany plik_w;
	Pamiec pam;
	Pamiec1 pam1;
	ProcessMaganement PsM;
	//ProcesorManagement PrM;
	
	MemoryManagement(){
		//bity_o = new Bity_odniesienia();
		//plik_w = new Plik_wymiany();
		pam = new Pamiec();
		pam1 = new Pamiec1();
		//bity_o.MM=this;
		//plik_w.MM=this;
		pam.MM=this;
		pam1.MM=this;
		
	}
}
