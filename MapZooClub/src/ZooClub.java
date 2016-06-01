import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;


public class ZooClub {
	
	private static Scanner s = new Scanner(System.in);
	

	private Map<Person,List<Animal>> Members = new LinkedHashMap<Person, List<Animal>>();
	private Set<Entry<Person, List<Animal>>> entrySet;
	private Iterator<Entry<Person, List<Animal>>> iteratorMembers;
	
	
	public static String inputStr(){
		String str;
		try {
			str=s.next();				
		} catch (Exception e) {
			System.out.println("You entered wrong value! Enter String value!\n");
			System.out.println("Input again:");
			s.nextLine();	
			str = inputStr();
		}
		return str;
	}
	
	public static int inputInt(){
		int i;
		try {
			i=s.nextInt();				
		} catch (Exception e) {
			System.out.println("You entered wrong value! Enter integer value!\n");
			System.out.println("Input again:");
			s.nextLine();	
			i = inputInt();
		}
		return i;
	}
	
	public void generateZooClub(int k){
		String[] personNames = {"Peter","Gulian","Mark","Thomas","Ivan","Poul","Elizabeth"};
		String[] personLNames = {"Anderson","Virth","Peterson","Mullen","Hewson","Zalucky","Portman"};
		
		String[] animalNames = {"Petty","Fluffy","Catty","Thomas","Creature","Bethoven","Lilly"};
		
		Random r = new Random();
	
		for(int i = 0; i < k; i++) {	
			ArrayList<Animal> aList= new ArrayList<Animal>();
			Members.put((new Person(personNames[r.nextInt(personNames.length)], personLNames[r.nextInt(personLNames.length)], r.nextInt(20)+10)), aList);
			for (int j = 0; j < r.nextInt(3)+1; j++) {
				aList.add(new Animal(animalNames[r.nextInt(animalNames.length)], AnimalSpicies.generateSpicies()));
			
			}
		}
	}
	
	
	public boolean addNewMember(){
		System.out.println("\nInput new Person:");
		System.out.println("Name:");
		String pName = inputStr();
			
		System.out.println("LastName:");
		String lName = inputStr();
				
		System.out.println("Age:");
		int age = inputInt();			
		
		Integer id = findPerson(pName,lName,age);

		if(id==null){
			Person p = new Person(pName,lName,age);
			this.Members.put(p, new ArrayList<Animal>());
			System.out.println("Person "+pName+" "+lName+" "+age+" added with id = "+p.getId()+"!");
			return true; 
		}else {
			System.out.println("Person "+pName+" "+lName+" "+age+" already exists with id "+id+"!");
			System.out.println("1 - save anyway");
			System.out.println("else - dont save");
			int choise = inputInt();
			switch (choise) {
			case 1:
				Person p = new Person(pName,lName,age);
				this.Members.put(p, new ArrayList<>());		
				System.out.println("Person "+pName+" "+lName+" "+age+" added with id = "+p.getId()+"!");
				return true;					
			default:
				return false;
			}	
		}			
	}
	
	public Animal inputNewAnimal() {
		Animal a;
		String name;
		AnimalSpicies aSpicies;
		try {
			System.out.println("\nInput new Animal:");
			System.out.println("Name:");
			name = inputStr();
			System.out.println("Spicies:");
			aSpicies = AnimalSpicies.valueOf(inputStr());
			a= new Animal(name,aSpicies);
			
		} catch (Exception e) {
			System.out.println("Animal not created! Try again");
			a = inputNewAnimal();
		}
		return a;
	}
	
	public boolean addNewAnimalToList(){
		
		entrySet = Members.entrySet();
		System.out.println("\nEnter index of Person to find:");
		String name = inputStr();
		for (Entry<Person, List<Animal>> entry : entrySet) {
			if(entry.getKey().getFirstName().equals(name)){
				
				System.out.println("\nInput new Animal:");
				System.out.println("Name:");
				String nameAnimal = inputStr();
				System.out.println("Spicies:");
				String aSpicies = inputStr();
				Animal a = new Animal(nameAnimal,AnimalSpicies.valueOf(aSpicies));
				entry.getValue().add(a);
			}
		}
		
		
		return true;
	}
	
	public boolean deleteAnimalsBySpicies() {
		
		this.entrySet = Members.entrySet();
		Iterator<Animal> aIterator;
		try {
			System.out.println("Spicies:");
			AnimalSpicies aSpicies = AnimalSpicies.valueOf(inputStr());
			for ( Entry<Person, List<Animal>> entry : entrySet) {
				aIterator = entry.getValue().iterator();
				String namePerson = inputStr();
				if(entry.getKey().getFirstName().equals(namePerson)){
					while(aIterator.hasNext()){
						Animal a = aIterator.next(); 
						if(a.getSpicies() == aSpicies){
							System.out.println(a.toString()+" DELETED from  "+entry.getKey());
							aIterator.remove();
						}
					}
				}				
			}
			return true;
		} catch (Exception e) {
			System.out.println("You inputed wrong Spicies!\n\tTry again!\n");
			return deleteAnimalsBySpicies();
		}	
	}
	
	public boolean deleteAnimalFromPerson() {
		try {
			List<Animal> alist =getAnimalListOfPersonByPersonId();
			if(alist!=null){
				System.out.println("\nInput Animal id:");
				int idAnimal = inputInt();
				Iterator<Animal> iter = alist.iterator();
				while (iter.hasNext()) {
					Animal a = iter.next();
					if(a.getId()==idAnimal){
						iter.remove();
						return true;
					}
				}
				System.out.println("There is no Anima with id = "+idAnimal);
				return false;
			}else{
				return false;
			}
		} catch (Exception e) {
			System.out.println("Animal not deleted from Person list of Animals");
			return false;
		}	
	}
	
	public void deletePersonFromZooClub(){
		System.out.println("\nInput Person id:");
		int idPerson = inputInt();
		
		entrySet = Members.entrySet();
		iteratorMembers = entrySet.iterator();
		
		while (iteratorMembers.hasNext()) {
			Entry<Person, List<Animal>> e = iteratorMembers.next();
			if(e.getKey().getId() == idPerson){
				iteratorMembers.remove();
			}
		}		
	}
	
	
	
	public List<Animal> getAnimalListOfPersonByPersonId() {
		entrySet = Members.entrySet();
		System.out.println("\nEnter index of Person to find:");
		int i = inputInt();
		for (Entry<Person, List<Animal>> entry : entrySet) {
			if(entry.getKey().getId() == i){
				return entry.getValue();
			}
		}
		System.out.println("There no Person with id = "+i);
		return null;
	}
	
	
	
	public List<Animal> getAnimalListOfPersonByPersonId(int i) {
		entrySet = Members.entrySet();
		System.out.println("\nEnter index of Person to find:");
		for (Entry<Person, List<Animal>> entry : entrySet) {
			if(entry.getKey().getId() == i){
				return entry.getValue();
			}
		}
		return null;
	}
	
	
	
	
	public boolean addNewAnimalToList(int i, Animal animal){
		try {
			getAnimalListOfPersonByPersonId(i).add(animal);	
			return true;
		} catch (Exception e) {
			System.out.println("Animal not addet to Person list of Animals");
			return false;
		}
		
	}
	
	
	public boolean saveToAFile() throws IOException{
		System.out.println("Enter Name of a file:");
		String str = inputStr();
		PrintWriter pw = new PrintWriter(str);
		
		String[] line = this.toString().split("\n");
		for (String string : line) {
			pw.println(string);
		}		
		pw.close();
		return true;
	}
	
	private String readLine(BufferedReader br){
		 try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private Integer findPerson(String name, String lName, int age){
		entrySet = Members.entrySet();
		for ( Entry<Person, List<Animal>> entry : entrySet) {
			if(entry.getKey().getFirstName().compareTo(name)==0 && entry.getKey().getLastName().compareTo(lName)==0 && entry.getKey().getAge()==age){
				return entry.getKey().getId();
			}
			
		}
		return null;
	}
	
	public boolean readFromAFile(){
		System.out.println("Enter Name of a file:");
		String str = inputStr();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(str));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return readFromAFile();
		}
		
		String pName, lName, aName, animalSpicies, strLine;
		int age, personId;
		String[] res;
		List<Animal> aList = null;
		
		while((strLine = readLine(br)) != null) {
			strLine = strLine.replace("\t", "");
			strLine = strLine.replace(" ", "");
			strLine = strLine.replace("|", ",");
			res = strLine.split(",");
			if(res[0].compareTo("Person")==0){
				Person p;
				res = strLine.split(",");
				res[1] = res[1].replace("(", "");
				res[1] = res[1].replace(")", "");
				personId = (int)Integer.parseInt(res[1].split("=")[1]); 
				pName = res[2].split("=")[1];
				lName = res[3].split("=")[1];
				age = (int)Integer.parseInt(res[4].split("=")[1]);
				
				aList = null;
							
				if(!Members.keySet().contains(Person.createFreePerson(personId, pName, lName, age))){
					boolean save = true;
					Integer findedId = findPerson(pName,lName,age);
					if(findedId != null){
						System.out.println("Person "+pName+" "+lName+" "+age+" already exists with id "+findedId+"!");
						System.out.println("1 - save anyway");
						System.out.println("else - dont save");
						int choise = inputInt();
						switch (choise) {
						case 1:
							save = true;								
							break;						
						default:
							save = false;
							break;
						}
					}
										
					if (save){
						aList = new ArrayList<Animal>();
						p = new Person(pName, lName, age);
						Members.put(p, aList);
					}				
				}
				else{
					System.out.println("Person id="+personId+" "+pName+" "+lName+" "+age+" "+" allready exists!");
				}
			}
			else if(aList != null && res[0].compareTo("Animal")==0){
				Animal a;
				res = strLine.split(",");
				aName = res[2].split("=")[1];
				animalSpicies = res[3].split("=")[1];
				a = new Animal(aName,AnimalSpicies.valueOf(animalSpicies));
				aList.add(a);
			}	
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String print(){
		String str = "\n";
		entrySet = Members.entrySet();
		if (entrySet.size()==0){
			str+="Club is empty!";
		}else{
			str+="Club: \n\n";
		}
		return str+=toString();
	}
	
	static public void pressAnyKey() throws IOException{
		System.out.println("\n   DONE!\n Press any key to continue.");
		System.in.read();
	}

	@Override
	public String toString() {
		
		String str = "";
		
		for (Entry<Person, List<Animal>> entry : entrySet) {
			str+=entry.getKey().toString()+"\n\t";
			for (int i = 0; i < entry.getKey().toString().length()-8; i++) {
				str+="-";
			}
			str+="\n";
			for (Animal animal : entry.getValue()) {
				str+="\t "+animal.toString()+"\n";
			}		
			for (int i = 0; i < entry.getKey().toString().length(); i++) {
				str+="-";
			}
			str+="\n";
		}
		return str+"Free indexes: "+Person.getFreeIndexes();
	}
	
	
	

}
