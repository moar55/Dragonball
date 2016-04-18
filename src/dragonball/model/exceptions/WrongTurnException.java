package dragonball.model.exceptions;

import dragonball.model.player.Player;

public class WrongTurnException extends  DragonBallException {

	private Player player;

	public Player getPlayer() {
		return player;
	} 
	
	public WrongTurnException(Player player) {
		super(player.getName()+"can't use senzu beans in the opponent's turn");
		this.player=player;
	}
	
	public WrongTurnException (String message,Player player){
		super(message);
		this.player=player;
	}
	
	
}
