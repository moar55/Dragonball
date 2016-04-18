package dragonball.model.character;

public abstract class Character implements java.io.Serializable {
	private String name;

	public Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
