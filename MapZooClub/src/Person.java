import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Person implements Comparable<Person>{
	
	private static int spacesid=0;
	private static int spacesName=0;
	private static int spacesLastName=0;
	private static int spacesAge=0;
	
	private static int lastid=0;
	private static Set<Integer> freeIndexes = new HashSet<Integer>();
	private static Iterator<Integer> iterator;
	
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private boolean free = false;
	
	
	
	private void index(){
		if (freeIndexes.isEmpty()){
			this.id=lastid;
			lastid++;
		}else{
			iterator = freeIndexes.iterator();
			this.id = iterator.next();
			iterator.remove();
		}
		spacesid = spaceChange(spacesid,((Integer)this.id).toString());
	}
	
	public Person() {
		super();
		index();
		this.free = false;
	}
	

	public Person(String firstName, String lastName, int age) {
		super();
		index();
		this.firstName = firstName;
		spacesName = spaceChange(spacesName,this.firstName);
		this.lastName = lastName;
		spacesLastName = spaceChange(spacesLastName,this.lastName);
		this.age = age;
		spacesAge = spaceChange(spacesAge,((Integer)this.age).toString());
		this.free = false;
	}
	
	private Person(int newid,String firstName, String lastName, int age) {
		super();
		this.id = newid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.free = true;
	}
	
	public static Person createFreePerson(int newid,String firstName, String lastName, int age){
		return new Person(newid,firstName, lastName, age);
	}
	
	

//	public void setId(int id) {
//		this.id = id;
//	}
	
		
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		spaceChange(spacesName,this.firstName);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static int getLastId(){
		return lastid;
	}
	
	public static Set<Integer> getFreeIndexes(){
		return freeIndexes;
	}
	
	private int spaceChange(int i, String str){
		return i<=str.length() ?  str.length() : i;
	}
	
	private String spaceAdd(String str, int l){
		String sp = "";
		for (int j = 0; j < l-str.length(); j++) {
			sp+=" ";
		}
		return sp;
	}
	
	static int inputInt(){
		int i=0;
		Scanner s = new Scanner(System.in);
		try {
			i=s.nextInt();				
		} catch (Exception e) {
			System.out.println("\nYou entered wrong value! Enter integer value!");
			System.out.print("Input again:");
			s.nextLine();	
			i = inputInt();
		}
		s.close();
		return i;
	}
	
	@Override
	public String toString() {
		return "Person | ("+"id = "+ spaceAdd(((Integer)id).toString(),spacesid) + id + ") | Name = " + firstName + spaceAdd(firstName,spacesName) + " |"
				+ " lName = " + lastName + spaceAdd(lastName,spacesLastName) + " |"
						+ " age = " + spaceAdd(((Integer)age).toString(),spacesAge) + age ;
			
	}

	@Override
	public int compareTo(Person o) {
		if(this.getFirstName().compareToIgnoreCase(o.getFirstName())!=0){
			return this.getFirstName().compareToIgnoreCase(o.getFirstName());
		}
		if(this.getLastName().compareToIgnoreCase(this.getLastName())!=0){
			return this.getLastName().compareToIgnoreCase(this.getLastName());
		}		
		return this.getAge()-o.getAge(); 
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	protected void finalize() throws Throwable {
		if(!this.free){
			freeIndexes.add((Integer)this.id);
			System.out.println(freeIndexes);	
		}
		
		super.finalize();
	}
	
	
	
}
