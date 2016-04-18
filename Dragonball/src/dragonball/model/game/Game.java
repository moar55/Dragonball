package dragonball.model.game;

import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dragonball.model.attack.*;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Cell;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.EmptyCell;
import dragonball.model.character.fighter.*;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;

public class Game implements WorldListener , PlayerListener,BattleListener  {
	private GameState state;
	private Player player;
	private World world;
	private ArrayList <NonPlayableFighter> weakFoes;
	private ArrayList <NonPlayableFighter> strongFoes;
	private ArrayList <Attack> attacks;
	private ArrayList <Dragon> dragons;
	private GameListener gui;

	
	public Game() throws IOException{
		this.loadAttacks("Database-Attacks_20309.csv");
		this.loadFoes("Database-Foes_20311.csv");
		this.loadDragons("Database-Dragons_20310.csv");
		player= new Player("random");
		player.setGame(this);
		state=GameState.WORLD;
		this.world=new World();
		world.setGame(this);
		world.generateMap(weakFoes, strongFoes);
		
	}
	
	
	
	
public static int numOfLines(String filePath) throws IOException{
		
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		
		int i=0;
		while(( br.readLine())!=null)
			i++;
		
		return i;
	}
	
	
	private  String [][] loadCSV(String filePath) throws IOException{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		int i=numOfLines(filePath);
		
		String result[][]=new String[i][];
		i=0;
		
		while((currentLine= br.readLine())!=null){
		// Parsing the currentLine String
		
		 result[i]=currentLine.split(",");
		 i++;
	}
		
		
		return result;
		}
	
	
	private void loadAttacks(String filePath) throws IOException{
		String [] [] result=loadCSV(filePath);
		this.attacks=new ArrayList<Attack>();
				
		for(int i=0;i<result.length;i++){
			if(result[i][0].equals("SA"))
				attacks.add(new SuperAttack(result[i][1],Integer.parseInt(result[i][2])));
			 
			else if(result[i][0].equals("UA"))
				attacks.add(new UltimateAttack(result[i][1],Integer.parseInt(result[i][2])));
			
			else if(result[i][0].equals("MC"))
				attacks.add(new MaximumCharge());
			
			else
				attacks.add(new SuperSaiyan());
			
		}
		
	}
	
	private void loadDragons(String filePath) throws IOException{
		String [] [] result=loadCSV(filePath);
		ArrayList <SuperAttack>superAttacks;
		ArrayList <UltimateAttack>ultimateAttacks;
		String [] first;
		this.dragons=new ArrayList<Dragon>();
		for(int i=0;i<result.length;){
			Dragon temp;
			superAttacks=new ArrayList<SuperAttack>();ultimateAttacks=new ArrayList <UltimateAttack>();
			first=null;
			for(int j=0;j<3;j++,i++){
				if(j==0)
					first=result[i];	//first line of input (non-attacks)
					
				if(j==1){
			//if superattack is a maximum charge		
					for(int k=0;k<result[i].length;k++){ 
						if(result[i][k].equals("Maximum Charge"))
							superAttacks.add(new MaximumCharge());
						
						else{ 
							//searching for the damage in the attacks arraylist
							int x=0;
							for(int counter=0;counter<attacks.size();counter++)
									if(result[i][k].equals(attacks.get(counter).getName()))
											{x=attacks.get(counter).getDamage();
											break;
											}
							
						superAttacks.add(new SuperAttack(result[i][k],x));
								
					}
				}
				}
				
				if(j==2){
					for(int k=0;k<result[i].length;k++ ){
						//Ultimate attack is a super saiyan
						if(result[i][k].equals("Super Saiyan"))
							ultimateAttacks.add(new SuperSaiyan());
						
						else{
							//Search for value of the damage of the ultimate attack in attacks arraylist
							int x=0;
							for(int counter=0;counter<attacks.size();counter++)
									if(result[i][k].equals(attacks.get(counter).getName()))
											{x=attacks.get(counter).getDamage();
											break;
											}
							
						ultimateAttacks.add(new UltimateAttack(result[i][k],x));
						}
					}
				}
				}
			//initialization of variables duuuh :D
			dragons.add(new Dragon(first[0],superAttacks,ultimateAttacks,Integer.parseInt(first[1]),Integer.parseInt(first[2])));		
		}
	}

	
	private void loadFoes(String filePath) throws IOException{
		String [] [] result=loadCSV(filePath);
		String [] first;
		ArrayList <SuperAttack>superAttacks;
		ArrayList <UltimateAttack> ultimateAttacks;
		this.weakFoes=new ArrayList<NonPlayableFighter>();
		this.strongFoes=new ArrayList<NonPlayableFighter>()	;
		for(int i=0;i<result.length;){
			NonPlayableFighter temp;
			superAttacks=new ArrayList<SuperAttack>();ultimateAttacks=new ArrayList<UltimateAttack>();
			first=null;
			for(int j=0;j<3;j++,i++){
				
				if(j==0)
					first=result[i];	
					
				if(j==1 && !(result[i][0].equals(""))){
					for(int k=0;k<result[i].length;k++){
						if(result[i][k].equals("Maximum Charge"))
							superAttacks.add(new MaximumCharge());
						
						else{
							int x=0;
							for(int counter=0;counter<attacks.size();counter++){
									if(result[i][k].equals(attacks.get(counter).getName()))
											{x=attacks.get(counter).getDamage();
											break;
											}
							}
							
						superAttacks.add(new SuperAttack(result[i][k],x));
								
					}
				}
				}
				
				if(j==2 && !(result[i][0].equals(""))){
					for(int k=0;k<result[i].length;k++ ){

						if(result[i][k].equals("Super Saiyan"))
							ultimateAttacks.add(new SuperSaiyan());
						
						else{
							int x=0;
							for(int counter=0;counter<attacks.size();counter++)
									if(result[i][k].equals(attacks.get(counter).getName()))
											{x=attacks.get(counter).getDamage();
											break;
											}
							
							ultimateAttacks.add(new UltimateAttack(result[i][k],x));
						}		
						
					}
				}
			}
						
			int level=Integer.parseInt(first[1]),maxhealth=Integer.parseInt(first[2]),
					blastdamage=Integer.parseInt(first[3]),physicaldamage=Integer.parseInt(first[4]),
					maxki=Integer.parseInt(first[5]),maxstamina=Integer.parseInt(first[6]);
			
			boolean strong=(first[7].equals("TRUE"))?true:false;
			temp=new NonPlayableFighter(first[0],level,maxhealth,blastdamage,physicaldamage,maxki,maxstamina,strong,
					superAttacks,ultimateAttacks);
			
			if(strong)
				strongFoes.add(temp);
			else
				weakFoes.add(temp);
			
			
		}
	}
	


	public Player getPlayer() {
		return player;
	}


	public World getWorld() {
		return world;
	}


	public ArrayList<NonPlayableFighter> getWeakFoes() {
		return weakFoes;
	}


	public ArrayList<NonPlayableFighter> getStrongFoes() {
		return strongFoes;
	}


	public ArrayList<Attack> getAttacks() {
		return attacks;
	}


	public ArrayList<Dragon> getDragons() {
		return dragons;
	}
	
	
	
	public GameState getState() {
		return state;
	}




	public static void main(String[] args) throws IOException {
		Game x=new Game();
		
	}




	@Override
	public void onBattleEvent(BattleEvent e) {
		
		
		if(e.getType()==BattleEventType.ENDED){
			if(!(((Fighter)e.getWinner()).getName().equals(((Fighter)e.getCurrentOpponent()).getName()))){
				player.getActiveFighter().setXp(player.getActiveFighter().getXp()+ (e.getCurrentOpponent().getLevel()*5));
				ArrayList<SuperAttack> temp =player.getSuperAttacks();
				temp.addAll(e.getCurrentOpponent().getSuperAttacks());
				player.setSuperAttacks(temp);
				
				ArrayList<UltimateAttack> temp2 =player.getUltimateAttacks();
				temp2.addAll(e.getCurrentOpponent().getUltimateAttacks());
				player.setUltimateAttacks(temp2);
			}
			
			if(((NonPlayableFighter)e.getCurrentOpponent()).isStrong())
			{
				player.setExploredMaps(player.getExploredMaps()+1);
				world=new World();
				world.generateMap(weakFoes, strongFoes);
				world.setGame(this);
			}
			state=GameState.WORLD;
		}
			
			if(gui!=null)
			gui.onBattleEvent(e);
		}
			
		
	



	@Override
	public void onDragonCalled() {

		int rand = (int)(Math.random()*dragons.size());
		Dragon chosen= dragons.get(rand);
		player.setDragonBalls(0);
		state=GameState.DRAGON;
		if(gui!=null)
		gui.onDragonCalled(chosen);
		
	}




	@Override
	public void onWishChosen(DragonWish wish) {
		state=GameState.WORLD;
		
	}




	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
			Battle now = new Battle(player.getActiveFighter(), foe);
			now.setGame(this);
			state=GameState.BATTLE;
			now.start();
			
	}




	@Override
	public void onCollectibleFound(Collectible collectible) {

		
		if(collectible==Collectible.SENZU_BEAN){
			
			player.setSenzuBeans(player.getSenzuBeans()+1);
		}
		
		else
		{
			player.setDragonBalls(player.getDragonBalls()+1);
			
			if(player.getDragonBalls()==7)
				player.callDragon();
		}
		
		
		if(gui!=null)
		gui.onCollectibleFound(collectible);
	}




	public void setGui(GameListener gui) {
		this.gui = gui;
	}
	
}
	