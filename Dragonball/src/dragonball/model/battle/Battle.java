package dragonball.model.battle;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent currentOpponent;
	
	public Battle(BattleOpponent me, BattleOpponent foe){
		this.me=me;
		this.foe=foe;
		me.setHealthPoints(me.getMaxHealthPoints());
		foe.setHealthPoints(foe.getMaxHealthPoints());
		me.setStamina(me.getMaxStamina());
		foe.setStamina(foe.getMaxStamina());
		me.setKi(0);
		foe.setKi(0);
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getCurrentOpponent() {
		return currentOpponent;
	}
	
	
}
