	package dragonball.model.world;

import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.player.Player;

public class World implements CellListener ,java.io.Serializable{
	public static final int MAP_SIZE = 10;
	public static final int NUM_WEAK_FOES = 15;
	public static final int NUM_MIN_SENZU_BEANS = 3;
	public static final int NUM_MAX_SENZU_BEANS = 5;
	public static final int NUM_DRAGON_BALLS = 1;
	private transient WorldListener game;

	private Cell[][] map;
	private int playerRow;
	private int playerColumn;

	public World() {
		map = new Cell[MAP_SIZE][MAP_SIZE];
	}

	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}

	// a helper method to get a random foe
	private NonPlayableFighter getRandomFoe(ArrayList<NonPlayableFighter> foes) {
		int i = new Random().nextInt(foes.size());
		return foes.get(i);
	}

	public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter> strongFoes) {
		// place the boss in position 0,0
		map[0][0] = new FoeCell(getRandomFoe(strongFoes));

		// place an empty cell in place of the player in position 9,9
		playerRow = playerColumn = MAP_SIZE - 1;
		map[playerRow][playerColumn] = new EmptyCell();

		// place weak foes
		for (int i = NUM_WEAK_FOES; i > 0;) {
			// generate a random row and column
			int row = new Random().nextInt(MAP_SIZE);
			int column = new Random().nextInt(MAP_SIZE);

			// only place the foe if the cell is free
			if (map[row][column] == null) {
				map[row][column] = new FoeCell(getRandomFoe(weakFoes));
				i--;
			}
		}

		// place senzu beans (random between 3 and 5)
		int numSenzuBeans = NUM_MIN_SENZU_BEANS + new Random().nextInt(NUM_MAX_SENZU_BEANS - NUM_MIN_SENZU_BEANS + 1);
		for (int i = numSenzuBeans; i > 0;) {
			// generate a random row and column
			int row = new Random().nextInt(MAP_SIZE);
			int column = new Random().nextInt(MAP_SIZE);

			// only place the senzu bean if the cell is free
			if (map[row][column] == null) {
				map[row][column] = new CollectibleCell(Collectible.SENZU_BEAN);
				i--;
			}
		}

		for (int i = NUM_DRAGON_BALLS; i > 0;) {
			// generate a random row and column
			int row = new Random().nextInt(MAP_SIZE);
			int column = new Random().nextInt(MAP_SIZE);

			// only place the dragon ball if the cell is free
			if (map[row][column] == null) {
				map[row][column] = new CollectibleCell(Collectible.DRAGON_BALL);
				i--;
			}
		}

		// place empty cells in remaining free cells
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == null) {
					map[i][j] = new EmptyCell();
				}
			}
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j].setWorld(this);
			}
		}
	}

	@Override
	public String toString() {
		String toString = "";

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (i == playerRow && j == playerColumn) {
					toString += "[x]"; // place an [x] in place of the player
				} else {
					toString += map[i][j];
				}
			}
			toString += "\n";
		}

		return toString.substring(0, toString.length() - 1);
	}

	public WorldListener getGame() {
		return game;
	}

	public void setGame(WorldListener game) {
		this.game = game;
	}
	
	public void resetPlayerPosition(){
		playerColumn=9;
		playerRow=9;
	}
	
	public void moveUp() {
		if(playerRow>0){
			playerRow--;
			map[playerRow][playerColumn].onStep();
		}
		
		else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
	}
	
	public void moveDown(){
		if(playerRow<9){
			playerRow++;
			map[playerRow][playerColumn].onStep();
		}
		else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
		
		
	}
	
	public void moveRight(){
		if(playerColumn<9){
			playerColumn++;
			map[playerRow][playerColumn].onStep();
		}
		
		else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
	}
	public void moveLeft(){
		if(playerColumn>0){
			playerColumn--;
			map[playerRow][playerColumn].onStep();
		}
		else
			throw new MapIndexOutOfBoundsException(playerRow, playerColumn); 
	}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
		if(game!=null)game.onFoeEncountered(foe);
		
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		map[playerRow][playerColumn]=new EmptyCell();
		map[playerRow][playerColumn].setWorld(this);
		if(game!=null)game.onCollectibleFound(collectible);
	}
	
	
}
