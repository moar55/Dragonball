package dragonball.model.player;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;

public class Player implements java.io.Serializable{
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;
	private transient PlayerListener game;
	
	public Player(String name) {
		this(name, new ArrayList<PlayableFighter>(), new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>(), 0,
				0, null, 0);
	}

	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans, int dragonBalls, PlayableFighter activeFighter,
			int exploredMaps) {
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
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
		ArrayList<PlayableFighter> fighters=this.fighters;
		
		int max = -1;
		
		for (int i = 0; i < fighters.size(); i++) {
			if(fighters.get(i).getLevel()>max)max=fighters.get(i).getLevel();
		}
		
		return max;
		
	}
	
	public void callDragon(){
		if(game!=null)
		game.onDragonCalled();
	}
	
	public void chooseWish(DragonWish wish){
		switch(wish.getType()){
		case SENZU_BEANS: setSenzuBeans(getSenzuBeans()+wish.getSenzuBeans());break;
		case ABILITY_POINTS: getActiveFighter().setAbilityPoints(getActiveFighter().getAbilityPoints()+wish.getAbilityPoints());break;
		case SUPER_ATTACK:  superAttacks.add(wish.getSuperAttack());break;
		case ULTIMATE_ATTACK: ultimateAttacks.add(wish.getUltimateAttack());break;
		}
		
		if(game!=null)
		game.onWishChosen(wish);

	}

	public PlayerListener getGame() {
		return game;
	}

	public void setGame(PlayerListener game) {
		this.game = game;
	}
	
	public void createFighter(char race, String name){
		switch(race){
		case 'E': fighters.add(new Earthling(name));break;
		case 'S': fighters.add(new Saiyan(name));break;
		case 'N': fighters.add(new Namekian(name));break;
		case 'F': fighters.add(new Frieza(name));break;
		case 'M': fighters.add(new Majin(name));break;
		}
		
		if(fighters.size()==1) activeFighter=fighters.get(0);
	}
	
	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute) throws NotEnoughAbilityPointsException{
		if(fighter.getAbilityPoints()>0){
		switch(fighterAttribute){
		case 'H': fighter.setMaxHealthPoints(fighter.getMaxHealthPoints()+50);break;
		case 'B': fighter.setBlastDamage(fighter.getBlastDamage()+50);break;
		case 'P': fighter.setPhysicalDamage(fighter.getPhysicalDamage()+50);break;
		case 'K': fighter.setMaxKi(fighter.getMaxKi()+1);break;
		case 'S': fighter.setMaxStamina(fighter.getMaxStamina()+1);break;
		}
		fighter.setAbilityPoints(fighter.getAbilityPoints()-1);
		}
		else
			throw new NotEnoughAbilityPointsException();
		
	}
	
	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack, SuperAttack oldAttack) throws DuplicateAttackException, MaximumAttacksLearnedException{
		ArrayList<SuperAttack> superAttacks= fighter.getSuperAttacks();
		
		for (int i = 0; i < superAttacks.size(); i++) {
			if(fighter.getSuperAttacks().get(i).getName().equals(newAttack.getName()))
				throw new DuplicateAttackException(newAttack);
		}
		
		if(oldAttack!=null){
			int j=0;
			for (int i = 0; i < superAttacks.size(); i++) 
				if(fighter.getSuperAttacks().get(i).getName().equals(oldAttack.getName()))
					{
						
						j=i;
						break;
					}
			
			superAttacks.set(j, newAttack);
				
		}
			
			else if(superAttacks.size()<4)
				superAttacks.add(newAttack);
		
			else throw new MaximumAttacksLearnedException();
		}
	
	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack, UltimateAttack
			oldAttack) throws DuplicateAttackException, NotASaiyanException, MaximumAttacksLearnedException{
		
		if(newAttack instanceof SuperSaiyan && !(fighter instanceof Saiyan))
			throw new NotASaiyanException();

		ArrayList<UltimateAttack> ultimateAttacks= fighter.getUltimateAttacks();

		for (int i = 0; i < ultimateAttacks.size(); i++) {
			if(fighter.getUltimateAttacks().get(i).getName().equals(newAttack.getName()))
				throw new DuplicateAttackException(newAttack);
		}
		
		
		if(oldAttack!=null){
			int j=0;
			for (int i = 0; i < ultimateAttacks.size(); i++) 
				if(ultimateAttacks.get(i).getName().equals(oldAttack.getName()))
					if(fighter.getUltimateAttacks().get(i).getName().equals(oldAttack.getName()))
					{
						j=i;
						break;
					}
			
			ultimateAttacks.set(j, newAttack);
				
		}
	
			
			else if(ultimateAttacks.size()<2)
				ultimateAttacks.add(newAttack);
		
			else throw new MaximumAttacksLearnedException();
	}
	
	
}
