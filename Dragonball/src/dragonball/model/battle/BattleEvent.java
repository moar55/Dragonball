package dragonball.model.battle;

import java.util.EventObject;

import dragonball.model.attack.Attack;
import dragonball.model.cell.Collectible;


public class BattleEvent extends EventObject {
	private BattleEventType type;
	private BattleOpponent currentOponent;
	private BattleOpponent winner;
	private Attack atttack;
	private Collectible collectible;
	
	public BattleEvent(Battle battle, BattleEventType type){
		super(battle);
		this.type=type;
	}
	
	public BattleEvent(Battle battle, BattleEventType type, BattleOpponent winner){
		this(battle,type);
		this.winner=winner;
		
	}
	
	 public BattleEvent(Battle battle, BattleEventType type, Attack attack){
		this(battle,type);
		 this.atttack=attack;
	 }
	 
	 public BattleEvent(Battle battle, BattleEventType type, Collectible collectible){
		 this(battle,type);
		 this.collectible=collectible;
	 }

	public BattleEventType getType() {
		return type;
	}

	public BattleOpponent getCurrentOponent() {
		return currentOponent;
	}

	public BattleOpponent getWinner() {
		return winner;
	}

	public Attack getAtttack() {
		return atttack;
	}

	public Collectible getCollectible() {
		return collectible;
	}
	 
	

}
