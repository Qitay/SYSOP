package processmanagement;



public class ProcesorManagement {
	
	procesor.interpreter_rozkazow1  i_r;
	ProcessMaganement PsM;
	MemoryManagement MM;
	
	ProcesorManagement(){
		i_r = new procesor.interpreter_rozkazow1();

		i_r.PrM=this;
		
		
	}
	
	
/*
	Procesor procesor;
	ProcessMaganement PsM;
	ProcesorManagement(){
		procesor = new Procesor();
		//procesor.PrM = this;
		
		
	}
	*/
}
