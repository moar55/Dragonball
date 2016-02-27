package dragonball.model.world;

import java.util.ArrayList;

import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.cell.*;

public class World {
	private Cell [][] map;
	private int playerColumn;
	private int playerRow;
	
	
	public World(){
		this.map=new Cell[10][10];
	}
	public Cell[][] getmap(){
		return map;
	}
	public int getplayerColumn(){
		return playerColumn;
	}
	public int getplayerRow(){
		return playerRow;
	}

	public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter>
	strongFoes){
		
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				map[i][j]=new EmptyCell();
		
		//Random strongFoe at (0,0)
		this.map[0][0]=new FoeCell(strongFoes.get((int)(Math.random()*(strongFoes.size()+1))));
			
		
		
			int x,y; //coordinates
			
			/* Note that the coordinate (9,9) can't be used in any of the assignments.
			 * (9,9) contains the player initially.
			 */
			
				
			//Setting weak foes.
				for(int counter =0;counter<15;){
				
				 x=(int)Math.random()*10;
				 y=(int) Math.random()*10;
				
				if(x!=9 && y!=9 && map[x][y].toString()=="[]"){
					map[x][y]=new FoeCell(weakFoes.get((int)(Math.random()*(weakFoes.size()+1))));
					counter++;
					}
				}
			
				
	int numofSenzu;
	//Generating number of senzubeans
			
		   do{
			 numofSenzu=(int)Math.random()*6;
			 if(numofSenzu<=5 && numofSenzu>=3)
				 break;
			}
			while(true);	
			
	//assigning senzubeans		
			for (int i = 0; i < numofSenzu; ) {
				
				 x=(int)Math.random()*10;
				 y=(int) Math.random()*10;
				 
				 if(x!=9 && y!=9 && map[x][y].toString()=="[]"){
					 map[x][y]=new CollectibleCell(Collectible.SENZU_BEAN);
					 i++;
				 }
			}
			
			
			
			do{
				 x=(int)Math.random()*10;
				 y=(int) Math.random()*10;
				 if(x!=9 && y!=9 && map[x][y].toString()=="[]"){
					 map[x][y]=new CollectibleCell(Collectible.DRAGON_BALL);
					 break;
				 }
			}
			while(true);
			
			
			
	}
	
	public String toString(){
		String x="";
		
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++)
				x=map[i][j]+" ";
			x+="\n";
		}
		return x;
		
	}
	
	
}
