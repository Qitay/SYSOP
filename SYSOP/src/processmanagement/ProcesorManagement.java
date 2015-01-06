package processmanagement;



public class ProcesorManagement {
	
	interpreter_rozkazow  i_r;
	ProcessMaganement PsM;
	MemoryManagement MM;
	
	ProcesorManagement(){
		i_r = new interpreter_rozkazow();

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
