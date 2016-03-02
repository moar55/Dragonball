package dragonball.model.game;

import dragonball.model.player.Player;
import dragonball.model.world.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import dragonball.model.attack.*;
import dragonball.model.character.fighter.*;
import dragonball.model.dragon.Dragon;

public class Game  {
	private Player player;
	private World world;
	private ArrayList <NonPlayableFighter> weakFoes;
	private ArrayList <NonPlayableFighter> strongFoes;
	private ArrayList <Attack> attacks;
	private ArrayList <Dragon> dragons;
	
	public Game() throws IOException{
		this.loadAttacks("Database-Attacks_20309.csv");
		this.loadFoes("Database-Foes_20311.csv");
		this.loadDragons("Database-Dragons_20310.csv");
		this.world=new World();
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
	
	public static void main(String[] args) throws IOException {
		Game x=new Game();
	}
}
	