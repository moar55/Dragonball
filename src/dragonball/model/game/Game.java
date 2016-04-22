package dragonball.model.game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Cell;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.EmptyCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

public class Game implements PlayerListener,WorldListener,BattleListener ,java.io.Serializable{
	private Player player;
	private World world;
	private ArrayList<NonPlayableFighter> weakFoes;
	private ArrayList<NonPlayableFighter> strongFoes;
	private ArrayList<Attack> attacks;
	private ArrayList<Dragon> dragons;
	private GameState state;
	private GameListener listener;
	private	String savePath = "";
	
	public Game() {
		strongFoes = new ArrayList<>();
		weakFoes = new ArrayList<>();
		attacks = new ArrayList<>();
		dragons = new ArrayList<>();
		
		try{
		loadAttacks("Database-Attacks.csv");
		}
		catch(UnknownAttackTypeException e){
			System.out.println(e.getMessage());
			System.out.println("using auxillary attacks file...");
			try{
			loadAttacks("Database-Attacks-aux.csv");
			}
			catch(Exception c){
				c.printStackTrace();
			}
		}
		catch(MissingFieldException e){
			System.out.println(e.getMessage());
			System.out.println("using auxillary attacks file...");
			try{
			loadAttacks("Database-Attacks-aux.csv");
			}
			catch(Exception c){
				c.printStackTrace();
			}
		}
		
		
		try{loadFoes("Database-Foes.csv");
		
		}
		catch(MissingFieldException e){
			System.out.println(e.getMessage());
			System.out.println("using auxillary foes file...");
			try{
			loadFoes("Database-Foes-aux.csv");
			}
			catch(Exception c){
				c.printStackTrace();
			}
		}
		
		try{loadDragons("Database-Dragons.csv");
		}
		catch(MissingFieldException e){
			System.out.println(e.getMessage());
			System.out.println("using auxillary dragons file...");
			try{
			loadDragons("Database-Dragons-aux.csv");
			}
			catch(Exception c){
				c.printStackTrace();
			}
		}
		player = new Player("dude");
		player.setGame(this);
		world = new World();
		world.generateMap(weakFoes, strongFoes);
		world.setGame(this);
		state=GameState.WORLD;
		
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

	private String[][] loadCSV(String filePath) {
		ArrayList<String[]> lines = new ArrayList<>();

		BufferedReader reader = null;
		String line = null;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			while ((line = reader.readLine()) != null) {
				lines.add(line.split(","));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return lines.toArray(new String[][] {});
	}

	public void loadAttacks(String filePath) throws UnknownAttackTypeException, MissingFieldException {
		String[][] lines = loadCSV(filePath);
		
		for (int i = 0; i < lines.length; i++) {
			Attack attack = null;
			int missingFields = 3-lines[i].length;
			if(missingFields>0)
				throw new MissingFieldException("There are missing fields in the attacks database: "+missingFields+" fields at line "+(i+1)+" in " +filePath, filePath, (i+1),missingFields);
			String attackType = lines[i][0];
			String name = lines[i][1];
			int damage = Integer.parseInt(lines[i][2]);

			if (attackType.equalsIgnoreCase("SA")) {
				attack = new SuperAttack(name, damage);
			} else if (attackType.equalsIgnoreCase("UA")) {
				attack = new UltimateAttack(name, damage);
			} else if (attackType.equalsIgnoreCase("MC")) {
				attack = new MaximumCharge();
			} else if (attackType.equalsIgnoreCase("SS")) {
				attack = new SuperSaiyan();
			}
			else
				throw new UnknownAttackTypeException("Invalid Attack Name: "+ attackType,filePath, (i+1), attackType);
			if (attack != null) {
				attacks.add(attack);
			}
		}
	}

	public void loadFoes(String filePath) throws MissingFieldException {
		String[][] lines = loadCSV(filePath);

		for (int i = 0; i < lines.length; i += 3) {
			
			int missingFields=8-lines[i].length;
			
			if(missingFields>0)
				throw new MissingFieldException("There are missing fields in the foes file "+filePath+ ": "+missingFields+" fields at line "+ (i+1), filePath, (i+1),missingFields);
			
			String name = lines[i][0];
			int level = Integer.parseInt(lines[i][1]);
			int maxHealthPoints = Integer.parseInt(lines[i][2]);
			int blastDamage = Integer.parseInt(lines[i][3]);
			int physicalDamage = Integer.parseInt(lines[i][4]);
			int maxKi = Integer.parseInt(lines[i][5]);
			int maxStamina = Integer.parseInt(lines[i][6]);
			boolean strong = Boolean.parseBoolean(lines[i][7]);
			ArrayList<SuperAttack> superAttacks = new ArrayList<>();
			ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<>();

			for (int j = 0; j < lines[i + 1].length; j++) {
				String attackName = lines[i + 1][j];
				for (Attack attack : attacks) {
					if (attack instanceof SuperAttack && attack.getName().equalsIgnoreCase(attackName)) {
						superAttacks.add((SuperAttack) attack);
						break;
					}
				}
			}

			for (int j = 0; j < lines[i + 2].length; j++) {
				String attackName = lines[i + 2][j];
				for (Attack attack : attacks) {
					if (attack instanceof UltimateAttack && attack.getName().equalsIgnoreCase(attackName)) {
						ultimateAttacks.add((UltimateAttack) attack);
						break;
					}
				}
			}

			NonPlayableFighter foe = new NonPlayableFighter(name, level, maxHealthPoints, blastDamage, physicalDamage,
					maxKi, maxStamina, strong, superAttacks, ultimateAttacks);
			if (strong) {
				strongFoes.add(foe);
			} else {
				weakFoes.add(foe);
			}
		}
	}

	public void loadDragons(String filePath) throws MissingFieldException {
		String[][] lines = loadCSV(filePath);

		for (int i = 0; i < lines.length; i += 3) {
			
			int missingFields = 3-lines[i].length;
			if(missingFields>0)
				throw new MissingFieldException("There are missing fields in the Dragons file "+filePath+" :"+missingFields+" fields at line "+(i+1), filePath, (i+1),missingFields);
					
			String name = lines[i][0];
			int senzuBeans = Integer.parseInt(lines[i][1]);
			int dragonsBalls = Integer.parseInt(lines[i][2]);
			ArrayList	<SuperAttack> superAttacks = new ArrayList<>();
			ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<>();

			for (int j = 0; j < lines[i + 1].length; j++) {
				String attackName = lines[i + 1][j];
				for (Attack attack : attacks) {
					if (attack instanceof SuperAttack && attack.getName().equalsIgnoreCase(attackName)) {
						superAttacks.add((SuperAttack) attack);
						break;
					}
				}
			}

			for (int j = 0; j < lines[i + 2].length; j++) {
				String attackName = lines[i + 2][j];
				for (Attack attack : attacks) {
					if (attack instanceof UltimateAttack && attack.getName().equalsIgnoreCase(attackName)) {
						ultimateAttacks.add((UltimateAttack) attack);
						break;
					}
				}
			}

			Dragon dragon = new Dragon(name, superAttacks, ultimateAttacks, senzuBeans,
					dragonsBalls);
			dragons.add(dragon);
		}
	}

	public GameState getState() {
		return state;
	}	

	@Override
	public void onBattleEvent(BattleEvent e) {
//		if(e.getType()==BattleEventType.STARTED)
//			state=GameState.BATTLE;	
		
		if(e.getType()==BattleEventType.ENDED)
		{
			if(e.getWinner().equals(player.getActiveFighter()))
			{
				NonPlayableFighter temp = (NonPlayableFighter)((Battle)e.getSource()).getFoe();
				player.getActiveFighter().setXp(player.getActiveFighter().getXp()+temp.getLevel()*5);
				player.getActiveFighter().getSuperAttacks().addAll(temp.getSuperAttacks());
				player.getActiveFighter().getUltimateAttacks().addAll(temp.getUltimateAttacks());
				
				if(temp.isStrong()){
					player.setExploredMaps(player.getExploredMaps()+1);
						world=new World();
						world.generateMap(weakFoes, strongFoes);
						world.setGame(this);
				}
				Cell [][] map = world.getMap();
				map[world.getPlayerRow()][world.getPlayerColumn()]=new EmptyCell();
				map[world.getPlayerRow()][world.getPlayerColumn()].setWorld(world);
				
			}
			
			else if(savePath.equals(""))
				{
				world =new World();
		        world.generateMap(weakFoes, strongFoes);
				state=GameState.WORLD;
				world.setGame(this);
				state=GameState.WORLD;
				} 
			
			else
			{
				load(savePath);
			}
			
		}
		
		if(listener!=null)listener.onBattleEvent(e);
		
	}


	@Override
	public void onDragonCalled() {
		Dragon chosen= dragons.get((int)(Math.random()*dragons.size()));
		player.setDragonBalls(0);
		state=GameState.DRAGON;
		if(listener!=null)listener.onDragonCalled(chosen);
		
	}

	@Override
	public void onWishChosen(DragonWish wish) {
		state=GameState.WORLD;

		
	}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
//		Cell [][] map = world.getMap();
//	 map[world.getPlayerRow()][world.getPlayerColumn()]=new EmptyCell();
//	 map[world.getPlayerRow()][world.getPlayerColumn()].setWorld(world);
		Battle fight=new Battle(player.getActiveFighter(),foe);
		fight.setListener(this);
		fight.start();
		state=GameState.BATTLE;
		
	}
	
	public static void main(String[] args) {
		Game x = new Game();
		x.save("save.ser");
		
		for(int i=0 ; i<x.weakFoes.size(); i++){
			System.out.println(x.weakFoes.get(i).getName());
		}
		System.out.println();
		x.weakFoes=null;
		x.strongFoes=null;
		x.load("save.ser");
		for(int i=0 ; i<x.weakFoes.size(); i++){
			System.out.println(x.weakFoes.get(i).getName());
		}
		
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		
		if(collectible==Collectible.SENZU_BEAN)
			player.setSenzuBeans(player.getSenzuBeans()+1);
		
		else if(collectible==Collectible.DRAGON_BALL){
			player.setDragonBalls(player.getDragonBalls()+1);
			if(player.getDragonBalls()==7)
				player.callDragon();
		}
		if(listener!=null)listener.onCollectibleFound(collectible);
	}
	
	
	public void save(String fileName){
		try{
			FileOutputStream saveFile =  new FileOutputStream(fileName);
			ObjectOutputStream outObj=new ObjectOutputStream(saveFile);
			outObj.writeObject(this);
			outObj.close();
			saveFile.close();
			savePath=fileName;
		}
		catch(IOException e){
			System.out.println(e.getMessage());
			savePath="";
		}
	}
	
	
	public void load (String fileName){
		Game loading = null;
		try{
			FileInputStream in = new FileInputStream(fileName);
			ObjectInputStream inObj= new ObjectInputStream(in);
			loading=(Game)inObj.readObject();
			this.player= loading.player;
			player.setGame(this);
			this.world=loading.world;
			world.setGame(this);
			this.weakFoes=loading.weakFoes;
			this.strongFoes=loading.strongFoes;
			this.attacks=loading.attacks;
			this.dragons=loading.dragons;
			this.state=loading.state;
			this.listener=loading.listener;
			inObj.close();
			in.close();
			}
		
		catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		catch(ClassNotFoundException c){
			System.out.println(c.getMessage());
		}
		
	}

	public GameListener getGui() {
		return listener;
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getSavePath() {
		return savePath;
	}
	
	
	
}
