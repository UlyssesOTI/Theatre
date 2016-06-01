import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Animal implements Comparable<Animal> {
	
	private static int spacesid=0;
	private static int spacesName=0;
	private static int spacesAnimaSpicies=0;
	private static int newWar;
	
	private static int lastid=0;
	private static Set<Integer> freeIndexes = new HashSet<Integer>();
	private static Iterator<Integer> iterator;
	
	private int id;
	private String name;
	private AnimalSpicies spicies;
	
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
	
	public Animal() {
		super();
		index();
	}
	
	public Animal(String name, AnimalSpicies spicies) {
		super();
		index();
		this.name = name;
		spacesName = spaceChange(spacesName, this.name);
		this.spicies = spicies;
		spacesAnimaSpicies = spaceChange(spacesAnimaSpicies, this.spicies.toString());
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AnimalSpicies getSpicies() {
		return spicies;
	}

	public void setSpicies(AnimalSpicies spicies) {
		this.spicies = spicies;
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

	@Override
	public String toString() {
		return "Animal | ("+"id = "+ spaceAdd(((Integer)id).toString(),spacesid) + id + ")| name = " + name + spaceAdd(name,spacesName) + "| spicies = " + spicies + spaceAdd(spicies.toString(),spacesAnimaSpicies);
	}


	@Override
	public int compareTo(Animal o) {
		if(this.getName().compareToIgnoreCase(o.getName()) != 0){
			return this.getName().compareToIgnoreCase(o.getName());
		}
		return this.getSpicies().ordinal()-o.getSpicies().ordinal();	
	}
	
	//@Override
	protected void finalize() throws Throwable {
		freeIndexes.add((Integer)this.id);
		//System.out.println(freeIndexes);
		super.finalize();
	}
	
}
