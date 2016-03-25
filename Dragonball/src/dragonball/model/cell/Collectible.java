package dragonball.model.cell;

public enum Collectible {
	SENZU_BEAN,DRAGON_BALL;
	
	public String toString() {
		if(this==Collectible.DRAGON_BALL)
			return "SENZU_BEAN";
		
		else
			return "DRAGON_BALL";
	};
}
