package dragonball.model.battle;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent currentOponent;
	
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
}
