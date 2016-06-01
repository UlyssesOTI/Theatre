import java.io.IOException;


public class Main {
	
	
	public static void main(String[] args) throws IOException {
	
		ZooClub zooClub = new ZooClub();
		
		int i=0;
		
		while(true){			
			System.out.println("\nOperations with a ZooClub:");
			System.out.println("1 - Add new Member");
			System.out.println("2 - Add new Animal to a Member");
			System.out.println("3 - Remove Animal from a Member");
			System.out.println("4 - Remove Member");
			System.out.println("5 - Remove Animals from all Members by Spicies");			
			System.out.println("6 - Save ZooClub to File");
			System.out.println("7 - Read ZooClub from File");			
			System.out.println("8 - Print ZooClub");
			System.out.println("9 - Add random ZooClub");
			System.out.println("0 - exit");
			System.out.print("Input Operation:");		
				
			do {
				i = ZooClub.inputInt();
				if(!(i>=0 && i<=9)){
					System.out.println("\nYou entered wrong digit!\n\t Try again!\n");
				}else{
					break;
				}
			} while (true);
				
			
			switch(i){
		
			case 1: zooClub.addNewMember();
				ZooClub.pressAnyKey();
				break;
			case 2: zooClub.addNewAnimalToList();
				ZooClub.pressAnyKey();
				break;
			case 3: zooClub.deleteAnimalFromPerson(); 
				ZooClub.pressAnyKey();
				break;
			case 4: zooClub.deletePersonFromZooClub();
				//System.gc();
				ZooClub.pressAnyKey();
				break;
			case 5: zooClub.deleteAnimalsBySpicies();
				ZooClub.pressAnyKey();
				break;
			case 6: zooClub.saveToAFile();
				ZooClub.pressAnyKey();
				break;
			case 7: zooClub.readFromAFile();
				ZooClub.pressAnyKey();
				break;
			case 8: System.out.println(zooClub.print());
				ZooClub.pressAnyKey();
				break;
			case 9: 
				System.out.print("\nInput qantity:");
				int q = ZooClub.inputInt(); 
				zooClub.generateZooClub(q);
				ZooClub.pressAnyKey();
				break;
			case 0:
				System.out.print("\nEND!");
				return;
			default:
				System.out.print("\nEND!");
				return;	
			}	
		}		
	}
	
	
	
}
