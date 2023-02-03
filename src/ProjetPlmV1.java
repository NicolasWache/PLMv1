
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class ProjetPlmV1 {
	
	

	public static void main(String[] args) {
		final String[][] dataAirplane = {
                {"A320", "PLM_AIRBUS_IN_SERVICE", "Passenger"},
                {"A400M", "PLM_AIRBUS_DEFINITION", "Cargo"},
                {"A350", "PLM_AIRBUS_FEASABILITY", "Passenger"},
                {"A380", "PLM_AIRBUS_STOPPED", "Passenger"}
				};	
		final String[][] dataDetailledAirplane = {
                {"Roue"},
                {},
                {},
                {"Moteur"}
				};	
		final String[][] pieceCatalogue = {
                {"Aile", "Carrosserie", "500€" },
                {"Moteur", "Piece de Moteur", "1200€"},
                {"Roue", "Equipement", "200€"},
                {"Siege", "Equipement", "120€"}
				};
		
		HashMap<Integer, String[]> airPlanesData = addDatabase(dataAirplane);
		HashMap<Integer, String[]> airPlanesDetailledData = addDatabase(dataDetailledAirplane);
		HashMap<Integer, String[]> airPlanePieceCatalogue = addDatabase(pieceCatalogue);
		HashMap<Integer, ArrayList<String[]>> airPlanesDetailled = new HashMap<>();
		ArrayList<String[]> airPlaneBuyPiece = new ArrayList<>();
		
		System.out.println("Bienvenue dans l'application de gestion du cycle de vie d'avions AIRBUS \n");
		
		
		Boolean StayInWhile = true;
		while (StayInWhile) {
			
			System.out.println("Faites votre choix dans le menu, saisissez le chiffre correspondant: \n\n"
					+ "1: Afficher tous les avions\n"
					+ "2: Afficher tous les avions contenant un mot clé dans le programme\n"
					+ "3: Ajouter ou Supprimer une pièce pour un avion donné \n"
					+ "4: Afficher un avion avec les infos detaillées de chaque pieces \n"
					+ "5: Quitter l'application");
			
			Scanner scanner = new Scanner(System.in);
			int numberChoice = scanner.nextInt();
			switch(numberChoice) {
			case 1:
				System.out.println(displayAirplaneData(airPlanesData));
				System.out.println("Souhaitez vous effectuer une nouvelle recherche? [o]Oui [n]non");
				StayInWhile = verifyResponse(StayInWhile, scanner);
				break;
			case 2: 
				System.out.println("Saisissez votre mot clé ");
				String searchedWord = scanner.next();
				System.out.println(searchedByWord(airPlanesData, searchedWord));
				System.out.println("Souhaitez vous effectuer une nouvelle recherche? [o]Oui [n]non");
				StayInWhile = verifyResponse(StayInWhile, scanner);
				break;
			case 3:
				System.out.println("Selectionnez l'avion sur lequel vous souhaitez effectuer une modification");
				System.out.println(displayAirplaneData(airPlanesData));
				int airplaneChoice = scanner.nextInt();
				System.out.println("Que souhaitez vous faire ? \n"
						+ "[1] Ajouter une Pièce  [2] Supprimer une Pièce ");
				int choice = scanner.nextInt();
				if (choice == 1) {
					System.out.println("Selectionner la pièce que vous souhaitez ajouter? ");
					System.out.println(displayAirplaneData(airPlanePieceCatalogue));
					int airplancePieceChoice = scanner.nextInt();
					addPiece(airPlanesDetailled, airPlanePieceCatalogue, airplaneChoice, airplancePieceChoice);
					System.out.println(displayAirplaneData2(airPlaneBuyPiece));
					
					} else {
						System.out.println("Selectionner la pièce que vous souhaitez supprimer? ");
						System.out.println(displayAirplaneData2(airPlaneBuyPiece));
						int airplancePieceChoice = scanner.nextInt();
						addPiece(airPlanesDetailled, airPlanePieceCatalogue, airplaneChoice, airplancePieceChoice);
						System.out.println(displayAirplaneData2(airPlaneBuyPiece));
				
				}
				System.out.println("Souhaitez vous effectuer une nouvelle recherche? [o]Oui [n]non");
				StayInWhile = verifyResponse(StayInWhile, scanner);
				break;
			case 5: 
			StayInWhile = false;
			break;
			}
		}
		
		
		
		

	}
	public static boolean verifyResponse (Boolean stayIn, Scanner scanner) {
		String response = scanner.next().toLowerCase();
		if (!response.equals("o"))
			stayIn=false;
		return stayIn;
	}
	
	public static HashMap<Integer, String[]> addDatabase (String[][] data) {
		HashMap<Integer, String[]> airPlanes = new HashMap<>();
		for (int i=0; i<data.length; i++) {
			airPlanes.put(i+1, data[i]);
		}
		return airPlanes;
	}
	public static String displayAirplaneData (HashMap<Integer, String[]> data) {
		String result = "";
		for (Integer key : data.keySet())
		result+= (key+"="+Arrays.toString(data.get(key))+"\n");
		return result;
	}
	public static String displayAirplaneData2 (HashMap<Integer, ArrayList<String[]>> data) {
		String result = "";
		for (Integer key : data.keySet())
		result+= (key+"="+Arrays.toString(data.get(key))+"\n");
		return result;
	}
	
	
	public static String displayPieceAirplaneData (HashMap<Integer, ArrayList<String[]>> data, int key) {
		String result= (key+"="+data.get(key)+"\n");
		return result;
	}
	public static void addPiece (HashMap<Integer,ArrayList<String[]>> dataPiece, HashMap<Integer, String[]> catalogue,int planeKey, int pieceKey) {
		
		String[] piecetoAdd = catalogue.get(pieceKey);
		
		dataPiece.put(planeKey-1, new ArrayList<String[]>());
		
		dataPiece.get(planeKey-1).add(piecetoAdd);
		
	}
	public static String searchedByWord (HashMap<Integer, String[]> data, String word) {
		String result = "";
		for (Integer key : data.keySet())
			if (data.get(key)[0].toLowerCase().contains(word.toLowerCase())) {
				result+= (key+"="+Arrays.toString(data.get(key))+"\n");
			}	
		return result;
	}

}
