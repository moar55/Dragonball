package dragonball.model.player;

import java.util.ArrayList;

import dragonball.model.attack.*;
import dragonball.model.character.fighter.*;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.game.GameListener;

public class Player {
	
	private String name; //name of the player
	private ArrayList<PlayableFighter> fighters; // fighters you have
	private ArrayList<SuperAttack> 	superAttacks; //unlocked super-attacks
	private ArrayList<UltimateAttack> ultimateAttacks; //unlocked ultimate-attacks
	private int senzuBeans;  
	private int dragonBalls; 
	private PlayableFighter activeFighter; //current fighter
	private int exploredMaps; //number of maps explored
	private  PlayerListener game;
	
	public Player(String name){
		this.name=name;
		this.fighters=new ArrayList<PlayableFighter>();
		superAttacks=new ArrayList<SuperAttack>();
		ultimateAttacks=new ArrayList<UltimateAttack>();
	}
	
	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack>
	superAttacks, ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans, int dragonBalls,
	PlayableFighter activeFighter, int exploredMaps){
		this.name=name;
		this.fighters=fighters;
		this.superAttacks=superAttacks;
		this.ultimateAttacks=ultimateAttacks;
		this.senzuBeans=senzuBeans;
		this.dragonBalls=dragonBalls;
		this.activeFighter=activeFighter;
		this.exploredMaps=exploredMaps;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}
	

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public int getMaxFighterLevel(){
		int max=-1;
		for(int i=0;i<fighters.size();i++)
			if(fighters.get(i).getLevel()>max)
				max=fighters.get(i).getLevel();
		
		return max;	
	}
	
	public void callDragon(){
		if(game!=null)
			game.onDragonCalled();
	}
	
	public void chooseWish(DragonWish wish){
		
		if(wish.getType()==DragonWishType.SENZU_BEANS)
			senzuBeans+=wish.getSenzuBeans();
		
		else if (wish.getType()==DragonWishType.ABILITY_POINTS)
				activeFighter.setAbilityPoints(activeFighter.getAbilityPoints()+wish.getAbilityPoints());
		
		else if (wish.getType()==DragonWishType.SUPER_ATTACK)
				superAttacks.add(wish.getSuperAttack());
		
		else
				ultimateAttacks.add(wish.getUltimateAttack());
		
		if(game!=null)
		game.onWishChosen(wish);
			
	}
	
	public void createFighter(char race,String name){
		
		switch(race){
		case 'E':fighters.add(new Earthling(name));break;
		case 'S': fighters.add(new Saiyan(name));break;
		case 'N': fighters.add(new Namekian(name));break;
		case 'F': fighters.add(new Frieza(name));break;
		case 'M': fighters.add(new Majin(name));break;
		}
		
		if(fighters.size()==1)
			activeFighter=fighters.get(0);
	}
	
	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute){
		
		switch(fighterAttribute){
		case 'H':fighter.setMaxHealthPoints(fighter.getMaxHealthPoints()+50);break;
		case 'B': fighter.setBlastDamage(fighter.getBlastDamage()+50);break;
		case 'P': fighter.setPhysicalDamage(fighter.getPhysicalDamage()+50);break;
		case 'K': fighter.setKi(fighter.getMaxKi()+1);break;
		case 'S' : fighter.setStamina(fighter.getMaxStamina()+1);break;
		}
	}
	
	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack, SuperAttack oldAttack){
		
		ArrayList<SuperAttack>superAttacks=fighter.getSuperAttacks();
		int j=0;
		
			while(!(oldAttack.getName().equals(superAttacks.get(j).getName())) && j<superAttacks.size())
				j++;
		
		if(oldAttack!=null)
			superAttacks.add(j, newAttack);
		
		else if(superAttacks.size()<4)
			superAttacks.add(newAttack);
		
		fighter.setSuperAttacks(superAttacks);
			
		}
	
	void assignAttack(PlayableFighter fighter, UltimateAttack newAttack, UltimateAttack oldAttack){
		
		ArrayList<UltimateAttack>ultimateAttacks=fighter.getUltimateAttacks();
		int j=0;
		
			while(!(oldAttack.getName().equals(ultimateAttacks.get(j).getName())) && j<ultimateAttacks.size())
				j++;
		
			
		if(oldAttack!=null)
			ultimateAttacks.add(j, newAttack);
		
		else if(ultimateAttacks.size()<2)
			ultimateAttacks.add(newAttack);
		
		fighter.setUltimateAttacks(ultimateAttacks);
	}
	

	public void setGame(PlayerListener game) {
		this.game = game;
	}
	
	
	
    


}
