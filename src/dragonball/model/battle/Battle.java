package dragonball.model.battle;

import java.util.ArrayList;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.WrongTurnException;
import dragonball.model.player.Player;
 
public class Battle implements java.io.Serializable {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;
		
		if(me instanceof Saiyan)
			((Saiyan)me).setTransformed(false);
		
		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}
	
	public ArrayList<Attack> getAssignedAttacks(){
		Fighter temp= (Fighter)attacker;
		ArrayList<Attack> attacks= new ArrayList<>();
		attacks.add(new PhysicalAttack());attacks.addAll(temp.getSuperAttacks());attacks.addAll(temp.getUltimateAttacks());
		return attacks;
	}
	
	public void attack(Attack attack) throws NotEnoughKiException{
		
		if(attacker==me){
		attack.onUse(me, foe, foeBlocking);
		}
		
		else{
			attack.onUse(foe, me, meBlocking);
			
		}
		
		
		
		
		if(listener!=null)
			listener.onBattleEvent(new BattleEvent(this, BattleEventType.ATTACK));
		
		endTurn();
	}
	
	public void block(){
		if(attacker==me)
			meBlocking=true;
		else
			foeBlocking=true;
		
		if(listener!=null)listener.onBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));
		
		endTurn();
	
	}
	
	public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException, WrongTurnException{
		
		if(player.getActiveFighter()!=attacker)
			throw new WrongTurnException( player);
			
		 if(player.getSenzuBeans()>0){
			player.getActiveFighter().setHealthPoints(player.getActiveFighter().getMaxHealthPoints());
			player.getActiveFighter().setStamina(player.getActiveFighter().getMaxStamina());
		    player.setSenzuBeans(player.getSenzuBeans()-1);
		 
		    if(listener!=null)listener.onBattleEvent(new BattleEvent(this, BattleEventType.USE));
		    endTurn();
		}
		 else
			 throw new NotEnoughSenzuBeansException();
		 
	}
	
	public BattleOpponent getDefender(){
		if(attacker==me)
			return foe;
		else
			return me;
	}
	
	public void play() throws NotEnoughKiException{
		int  rand = (int)(Math.random()*2);
		
		if(rand==0)
			block();
		
		else
		{
			rand = (int)(Math.random()*3);
			if(rand==0)
				attack(new PhysicalAttack());
			
			else if(rand==1 && ((NonPlayableFighter)foe).getSuperAttacks()!=null && ((NonPlayableFighter)foe).getSuperAttacks().size()>0){
					NonPlayableFighter temp = (NonPlayableFighter)foe;
					try{
					attack(temp.getSuperAttacks().get((int)(Math.random()*temp.getSuperAttacks().size())));
					}
					catch(NotEnoughKiException e){
						attack(new PhysicalAttack());
					}
				}
			
				else if(rand==2 && ((NonPlayableFighter)foe).getUltimateAttacks()!=null && ((NonPlayableFighter)foe).getUltimateAttacks().size()>0)
				{
					NonPlayableFighter temp = (NonPlayableFighter)foe;
					UltimateAttack ulitmatAttack =temp.getUltimateAttacks().get((int)(Math.random()*temp.getUltimateAttacks().size()));
					while(ulitmatAttack instanceof SuperSaiyan)
						 ulitmatAttack =temp.getUltimateAttacks().get((int)(Math.random()*temp.getUltimateAttacks().size()));

					try{
						attack(ulitmatAttack);
					}
					catch(NotEnoughKiException e){
						attack(new PhysicalAttack());
					}
				}
			
				else
					attack(new PhysicalAttack());
		}
	}
	
	public void endTurn(){
		if(((Fighter)me).getHealthPoints()==0)
		{
			if(listener!=null)listener.onBattleEvent(new BattleEvent(this, BattleEventType.ENDED,foe));
		}
		else
			if(((NonPlayableFighter)foe).getHealthPoints()==0){
				if(listener!=null)listener.onBattleEvent(new BattleEvent(this, BattleEventType.ENDED,me));
			}
		
		else {
			if(attacker==me)
			{
				me.onAttackerTurn();
				foe.onDefenderTurn();
				foeBlocking=false;
			}
			else
			{
				me.onDefenderTurn();
				foe.onAttackerTurn();
				meBlocking=false;
			}
			
			
			switchTurn();
		}
	}
	
	public void switchTurn(){
		attacker=(attacker==me)?foe:me;
		if(listener!=null)listener.onBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}
	
	public void start(){
		if(listener!=null)
			listener.onBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public BattleListener getListner() {
		return listener;
	}

	public void setListener(BattleListener listener) {
		this.listener = listener;
	}
	
	
}
