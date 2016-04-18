package dragonball.model.cell;

public enum Collectible {
	SENZU_BEAN, DRAGON_BALL;
	
	public String toString () {
		if(this==Collectible.SENZU_BEAN)
			return "SENZU_BEAN";
		else
			return "DRAGON_BALL";
	}
}
