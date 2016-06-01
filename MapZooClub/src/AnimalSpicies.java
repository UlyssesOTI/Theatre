import java.util.Random;

public enum AnimalSpicies {
	DOG,
	CAT,
	HUMSTER,
	FISH,
	PARROT;
	
	public static AnimalSpicies generateSpicies(){
		
		Random r = new Random();
	
		switch(	r.nextInt(5)){
			case 0: 
				return DOG;
			case 1: 
				return CAT;
			case 2: 
				return HUMSTER;
			case 3: 
				return FISH;
			case 4: 
				return PARROT;
			default:
				return DOG;
		}
		
	}
}
