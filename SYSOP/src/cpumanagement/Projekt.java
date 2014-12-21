package cpumanagement;


public class Projekt {
	
public static void main(String[] args) {

		ProcessMaganement processM = new ProcessMaganement();
		ProcesorManagement procesorM = new ProcesorManagement();
		processM.PrM=procesorM;
		procesorM.PsM=processM;
		
	}

}
