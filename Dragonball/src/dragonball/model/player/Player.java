package dragonball.model.player;

public class Player {
	
	private String name;
	private ArrayList<PlayableFighter> fighters; //number of fighters you have
	private ArrayList<SuperAttack> 	superAttacks; //unlocked super-attacks
	private ArryList<UltimateAttack> ultimateAttakcs; //unlocked ultimate-attacks
	private int senzuBeans;
	private int dragonbBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;
	
	public Player(String name){
		this.name=name;
	}
	
	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack>
	superAttacks, ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans, int dragonBalls,
	PlayableFighter activeFighter, int exploredMaps){
		this.name=name;
		this.fighters=fighters;
		this.superAttacks=superAttacks;
		this.ultimateAttakcs=ultimateAttacks;
		this.senzuBeans=senzuBeans;
		this.dragonbBalls=dragonBalls;
		this.activeFighter=activeFighter;
		this.exploredMaps=exploredMaps;
		
	}
	






}
