package dragonball.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.DragonBallException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.WrongTurnException;
import dragonball.model.game.Game;
import dragonball.model.player.Player;

public class Q3V4 {
	private int entered = 0;
	private int attacked = 0;
	private int attackedPhysical = 0;

	@Test(timeout = 1000)
	public void WrongTurnExceptionEInheritance() throws Exception {
		assertEquals(
				"WrongTurnException class should extend DragonBallException",
				DragonBallException.class,
				WrongTurnException.class.getSuperclass());
	}

	@Test(timeout = 1000)
	public void testWrongTurnExceptionClassVariables() throws Exception {
		Field f = null;
		boolean thrown = false;
		try {
			f = WrongTurnException.class.getDeclaredField("player");
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		assertFalse(
				"there should be \"player\" instance variable in class WrongTurnException",
				thrown);
		assertEquals(
				"\"player\" instance variable in class WrongTurnException should be of type Player",
				f.getType(), Player.class);

	}

	@Test(timeout = 1000)
	public void testWrongTurnExceptionClassVariablesAccessibility()
			throws Exception {

		Field f = WrongTurnException.class.getDeclaredField("player");
		assertEquals(
				"\"player\" instance variable in class WrongTurnException should not be accessed outside that class",
				2, f.getModifiers());

	}

	@Test(timeout = 1000)
	public void testWrongTurnExceptionClassREADVariables() throws Exception {
		Method[] methods = WrongTurnException.class.getDeclaredMethods();

		assertTrue(
				"The \"player\" instance variable in class WrongTurnException is a READ variable.",
				containsMethodName(methods, "getPlayer"));

		try {
			Method m = WrongTurnException.class.getDeclaredMethod("getPlayer");
			assertTrue(
					"incorrect return type for \"getPlayer\" method in WrongTurnException class.",
					m.getReturnType().equals(Player.class));
		} catch (Exception e) {
			fail("Missing \"getPlayer\" method in WrongTurnException class which takes no input parameters.");
		}

		assertFalse(
				"The \"player\" instance variable in class WrongTurnException is a READ ONLY variable.",
				containsMethodName(methods, "setPlayer"));

	}

	@Test(timeout = 1000)
	public void testWrongTurnExceptionFirstConstructor() throws Exception {
		Class<WrongTurnException> aClass = WrongTurnException.class;
		boolean thrown = false;
		try {
			aClass.getConstructor(new Class[] { Player.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 1 parameter in WrongTurnException class.",
				thrown);
		Player p = new Player("testPlayer");
		WrongTurnException n = new WrongTurnException(p);
		Field var = WrongTurnException.class.getDeclaredField("player");
		var.setAccessible(true);
		assertTrue("Constructor with 1 parameter should set the message of the exception, the message of the exception should include the player's name", n.getMessage().contains("testPlayer"));
		assertEquals("Constructor with 1 parameter should set the player variable",p, var.get(n));
	}

	@Test(timeout = 1000)
	public void testWrongTurnExceptionSecondConstructor()
			throws Exception {
		Class<WrongTurnException> aClass = WrongTurnException.class;
		boolean thrown = false;
		try {
			aClass.getConstructor(new Class[] { String.class, Player.class });
		} catch (NoSuchMethodException e) {
			thrown = true;
		}
		assertFalse(
				"Missing constructor with 2 parameters in WrongTurnException class.",
				thrown);
		String testMessage = "test"+(int) (Math.random()*10000)+"testPlayer";
		Player p = new Player("testPlayer");
		WrongTurnException n = new WrongTurnException(testMessage,p);
		Field var = WrongTurnException.class.getDeclaredField("player");
		var.setAccessible(true);
		assertTrue("Constructor with 2 parameters should set the message of the exception ", n.getMessage().contains(testMessage));
		assertTrue("The message of the exception should include the player's name", n.getMessage().contains("testPlayer"));
		assertEquals("Constructor with 2 parameters should set the player variable",p,  var.get(n));
	}

	@Test(timeout = 1000)
	public void testThrow_WrongTurnException_OnUse() throws Exception {

		new Game();

		NonPlayableFighter foe = new NonPlayableFighter("fighter", 1, 1, 10,
				1250, 50, 40, false, null, null);

		ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
		superAttacks1.add(new SuperAttack("Kamehameha", 250));
		foe.setSuperAttacks(superAttacks1);

		ArrayList<UltimateAttack> ultimateAttacks1 = new ArrayList<UltimateAttack>();
		ultimateAttacks1.add(new UltimateAttack("Super Kamehameha", 450));
		ultimateAttacks1.add(new SuperSaiyan());
		foe.setUltimateAttacks(ultimateAttacks1);

		Saiyan me = new Saiyan("saiyan");

		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new MaximumCharge());
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		me.setSuperAttacks(superAttacks);

		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new SuperSaiyan());
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		me.setUltimateAttacks(ultimateAttacks);

		Player player = new Player("Menah");
		ArrayList<PlayableFighter> fighters = new ArrayList<PlayableFighter>();
		fighters.add(me);
		player.setFighters(fighters);
		player.setActiveFighter(me);
		player.setSenzuBeans(2);

		Battle b = new Battle(me, foe);

		boolean thrown = false;
		try {
			b.use(player, Collectible.SENZU_BEAN);
		} catch (WrongTurnException e) {
			fail("A player should be able to use senzu beans in his/her turn.");
		}

		try {
			if (b.getAttacker() == foe) {

				b.use(player, Collectible.SENZU_BEAN);

			} else {

				b.endTurn();
				if (b.getAttacker() == foe) {

					b.use(player, Collectible.SENZU_BEAN);

				} else
					fail("End turn should end the turn and switch the players.");
			}
		} catch (WrongTurnException e) {
			thrown = true;
		}

		assertTrue(
				"When the player attempts to use a senzu bean while it is not their turn in the battle a WrongTurnException shold be thrown.",
				thrown);

	}

	@Test(timeout = 1000)
	public void testThrownVariables_WrongTurnException_OnUse() throws Exception {

		new Game();

		NonPlayableFighter foe = new NonPlayableFighter("fighter", 1, 1, 10,
				1250, 50, 40, false, null, null);

		ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
		superAttacks1.add(new SuperAttack("Kamehameha", 250));
		foe.setSuperAttacks(superAttacks1);

		ArrayList<UltimateAttack> ultimateAttacks1 = new ArrayList<UltimateAttack>();
		ultimateAttacks1.add(new UltimateAttack("Super Kamehameha", 450));
		ultimateAttacks1.add(new SuperSaiyan());
		foe.setUltimateAttacks(ultimateAttacks1);

		Saiyan me = new Saiyan("saiyan");

		ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
		superAttacks.add(new MaximumCharge());
		superAttacks.add(new SuperAttack("Kamehameha", 250));
		me.setSuperAttacks(superAttacks);

		ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
		ultimateAttacks.add(new SuperSaiyan());
		ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 500));
		me.setUltimateAttacks(ultimateAttacks);

		String playerName = "Menah_" + Math.random() * 10000;
		Player player = new Player(playerName);
		ArrayList<PlayableFighter> fighters = new ArrayList<PlayableFighter>();
		fighters.add(me);
		player.setFighters(fighters);
		player.setActiveFighter(me);
		player.setSenzuBeans(2);

		Battle b = new Battle(me, foe);

		boolean thrown = false;
		try {
			b.use(player, Collectible.SENZU_BEAN);
		} catch (WrongTurnException e) {
			fail("A player should be able to use senzu beans in his/her turn.");
		}

		try {
			if (b.getAttacker() == foe) {

				b.use(player, Collectible.SENZU_BEAN);

			} else {

				b.endTurn();
				if (b.getAttacker() == foe) {

					b.use(player, Collectible.SENZU_BEAN);

				} else
					fail("End turn should end the turn and switch the players.");
			}
		} catch (WrongTurnException e) {
			Field var = WrongTurnException.class.getDeclaredField("player");
			var.setAccessible(true);
			

			Player p = (Player)var.get(e);
			assertEquals(
					"The variable player in the WrongTurnException should be set correctly whenever the exception is thrown.",
					player, p);
			assertTrue(
					"The message of the WrongTurnException should include the player's name whenever the exception is thrown.",
					e.getMessage().contains(player.getName()));

			thrown = true;
		}

		assertTrue(
				"When the player attempts to use a senzu bean while it is not their turn in the battle a WrongTurnException shold be thrown.",
				thrown);

	}

	@Test(timeout = 1000)
	public void testPlay_Handles_NotEnoughKi() throws Exception {

		for (int hgg = 0; hgg < 100; hgg++) {

			Saiyan me = new Saiyan("saiyan");
			ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
			superAttacks.add(new MaximumCharge());
			superAttacks.add(new SuperAttack("Kamehameha", 20));
			me.setSuperAttacks(superAttacks);

			ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
			ultimateAttacks.add(new SuperSaiyan());
			ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 5));
			me.setUltimateAttacks(ultimateAttacks);

			NonPlayableFighter foe = new NonPlayableFighter("fighter", 1, 1000,
					10, 10, 50, 40, false, null, null);
			ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
			superAttacks1.add(new SuperAttack("Kamehameha", 50));
			foe.setSuperAttacks(superAttacks1);

			ArrayList<UltimateAttack> ultimateAttacks1 = new ArrayList<UltimateAttack>();
			ultimateAttacks1.add(new UltimateAttack("Super Kamehameha", 40));
			ultimateAttacks1.add(new SuperSaiyan());
			foe.setUltimateAttacks(ultimateAttacks1);

			Battle b = new Battle(me, foe);

			me.setKi(10);
			foe.setKi(10);
			try {

				b.attack(me.getSuperAttacks().get(1));
				if (b.getAttacker() != foe) {
					b.endTurn();
					if (b.getAttacker() != foe)
						fail("End turn should end the turn and switch the players.");
				}
				b.play();
				if (b.getAttacker() == foe) {
					b.endTurn();
					if (b.getAttacker() == foe)
						fail("End turn should end the turn and switch the players.");
				}
				b.attack(me.getUltimateAttacks().get(0));

			} catch (Exception e) {

				e.printStackTrace();
				if(e instanceof NotEnoughKiException)
				fail("NotEnoughKiException should not be thrown if the user tries to use a super attack attack while there is enough Ki");

			}

			ArrayList<SuperAttack> superAttacks2 = new ArrayList<SuperAttack>();
			SuperAttack sAttack = new SuperAttack("Kamehameha", 250) {
				public void onUse(BattleOpponent attacker,
						BattleOpponent defender, boolean defenderBlocking)
						throws NotEnoughKiException {
					entered++;
					attacked++;
					throw new NotEnoughKiException(5, 0);
				}
			};
			superAttacks2.add(sAttack);
			foe.setSuperAttacks(superAttacks2);

			foe.setUltimateAttacks(new ArrayList<UltimateAttack>());

			b = new Battle(me, foe) {

				public void attack(Attack attack) throws NotEnoughKiException {

					entered++;
					if (!(attack instanceof PhysicalAttack)) {// should this be
																// modified to
																// "If super"?
						attacked++;
						throw new NotEnoughKiException(1, 0);
					}

				}

				public void block() {

					entered++;

				}

			};

			if (b.getAttacker() != foe) {

				b.endTurn();
				if (b.getAttacker() != foe)
					fail("End turn should end the turn and switch the players.");

			}

			foe.setKi(0);

			try {

				while (attacked == 0) {
					b.play();
					if (entered == 0)
						fail("Play method should choose and perform an action randomly.");
					if (entered > 1000)
						fail("One of the random actions of the play method should be to perform a super attack.");
				}

			} catch (Exception e) {

				e.printStackTrace();
				if(e instanceof NotEnoughKiException)
					fail("Play method should handle NotEnoughKiException thrown when attacking without the required ki.");
				fail("An exception occured."
						+ e.getMessage());

			}

			foe.setSuperAttacks(new ArrayList<SuperAttack>());

			ArrayList<UltimateAttack> ultimateAttacks2 = new ArrayList<UltimateAttack>();
			ultimateAttacks2.add(new UltimateAttack("Super Kamehameha", 450) {
				public void onUse(BattleOpponent attacker,
						BattleOpponent defender, boolean defenderBlocking)
						throws NotEnoughKiException {
					entered++;
					attacked++;
					throw new NotEnoughKiException(5, 0);
				}
			});
			ultimateAttacks2.add(new SuperSaiyan() {
				public void onUse(BattleOpponent attacker,
						BattleOpponent defender, boolean defenderBlocking)
						throws NotEnoughKiException {
					entered++;
					attacked++;
					throw new NotEnoughKiException(5, 0);
				}
			});
			foe.setUltimateAttacks(ultimateAttacks2);

			b = new Battle(me, foe) {

				public void attack(Attack attack) throws NotEnoughKiException {

					entered++;
					if (!(attack instanceof PhysicalAttack)) {
						attacked++;
						throw new NotEnoughKiException(1, 0);
					}

				}

				public void block() {

					entered++;

				}

			};

			if (b.getAttacker() != foe) {

				b.endTurn();
				if (b.getAttacker() != foe)
					fail("End turn should end the turn and switch the players.");

			}

			foe.setKi(1);

			try {

				while (attacked == 0) {
					b.play();
					if (entered == 0)
						fail("Play method should choose and perform an action randomly.");
					if (entered > 1000)
						fail("One of the random actions of the play method should be to perform a super attack.");
				}

			}  catch (Exception e) {

				e.printStackTrace();
				if(e instanceof NotEnoughKiException)
					fail("Play method should handle NotEnoughKiException thrown when attacking without the required ki.");
				fail("An exception occured."
						+ e.getMessage());

			}

		}
	}

	@Test
	// (timeout = 1000)
	public void testPlay_PhysicalAttack_NotEnoughKi() throws Exception {

		for (int hgg = 0; hgg < 100; hgg++) {

			Saiyan me = new Saiyan("saiyan");
			ArrayList<SuperAttack> superAttacks = new ArrayList<SuperAttack>();
			superAttacks.add(new MaximumCharge());
			superAttacks.add(new SuperAttack("Kamehameha", 20));
			me.setSuperAttacks(superAttacks);

			ArrayList<UltimateAttack> ultimateAttacks = new ArrayList<UltimateAttack>();
			ultimateAttacks.add(new SuperSaiyan());
			ultimateAttacks.add(new UltimateAttack("Spirit Bomb", 5));
			me.setUltimateAttacks(ultimateAttacks);

			NonPlayableFighter foe = new NonPlayableFighter("fighter", 1, 1000,
					10, 10, 50, 40, false, null, null);
			ArrayList<SuperAttack> superAttacks1 = new ArrayList<SuperAttack>();
			superAttacks1.add(new SuperAttack("Kamehameha", 50));
			foe.setSuperAttacks(superAttacks1);

			ArrayList<UltimateAttack> ultimateAttacks1 = new ArrayList<UltimateAttack>();
			ultimateAttacks1.add(new UltimateAttack("Super Kamehameha", 40));
			ultimateAttacks1.add(new SuperSaiyan());
			foe.setUltimateAttacks(ultimateAttacks1);

			Battle b = new Battle(me, foe);

			me.setKi(10);
			foe.setKi(10);
			try {

				b.attack(me.getSuperAttacks().get(1));
				if (b.getAttacker() != foe) {
					b.endTurn();
					if (b.getAttacker() != foe)
						fail("End turn should end the turn and switch the players.");
				}
				b.play();
				if (b.getAttacker() == foe) {
					b.endTurn();
					if (b.getAttacker() == foe)
						fail("End turn should end the turn and switch the players.");
				}
				b.attack(me.getUltimateAttacks().get(0));

			} catch (Exception e) {

				e.printStackTrace();
				if(e instanceof NotEnoughKiException)

				fail("NotEnoughKiException should NOT be thrown if the user tries to use a super attack attack while there is enough Ki");

			}

			ArrayList<SuperAttack> superAttacks2 = new ArrayList<SuperAttack>();
			SuperAttack sAttack = new SuperAttack("Kamehameha", 250) {
				public void onUse(BattleOpponent attacker,
						BattleOpponent defender, boolean defenderBlocking)
						throws NotEnoughKiException {
					entered++;
					attacked++;
					throw new NotEnoughKiException(5, 0);
				}
			};
			superAttacks2.add(sAttack);
			foe.setSuperAttacks(superAttacks2);

			foe.setUltimateAttacks(new ArrayList<UltimateAttack>());

			b = new Battle(me, foe) {

				public void attack(Attack attack) throws NotEnoughKiException {

					entered++;
					if (!(attack instanceof PhysicalAttack)) {// should this be
																// modified to
																// "If super"?
						attacked++;
						throw new NotEnoughKiException(1, 0);
					}
					if (attacked > 0 && attack instanceof PhysicalAttack) {
						attackedPhysical++;
						try {
							super.attack(attack);
						} catch (Exception e) {
							fail("An exception occured while attacking."
									+ e.getMessage());
						}
					}

				}

				public void block() {

					entered++;

				}

			};

			if (b.getAttacker() != foe) {

				b.endTurn();
				if (b.getAttacker() != foe)
					fail("End turn should end the turn and switch the players.");

			}

			foe.setKi(0);
			int hpBefore = me.getHealthPoints();

			try {

				while (attacked == 0) {
					b.play();
					if (entered == 0)
						fail("Play method should choose and perform an action randomly.");
					if (entered > 1000)
						fail("One of the random actions of the play method should be to perform a super attack.");
				}

			} catch (Exception e) {

				e.printStackTrace();
				if(e instanceof NotEnoughKiException)
					fail("Play method should handle NotEnoughKiException thrown when attacking without the required ki.");
				fail("An exception occured."
						+ e.getMessage());

			}

			assertTrue(
					"When the foe is trying to perform an attack without having the required ki, he/she should perform a physical attack instead.",
					attackedPhysical > 0);
			assertEquals(
					"When me attacks the foe that is not blocking with physical attack , it should reduce points from his health points according to the game rules",
					hpBefore - 50 - foe.getPhysicalDamage(),
					me.getHealthPoints());

			entered = 0;
			attacked = 0;
			attackedPhysical = 0;

			foe.setSuperAttacks(new ArrayList<SuperAttack>());

			ArrayList<UltimateAttack> ultimateAttacks2 = new ArrayList<UltimateAttack>();
			ultimateAttacks2.add(new UltimateAttack("Super Kamehameha", 450) {
				public void onUse(BattleOpponent attacker,
						BattleOpponent defender, boolean defenderBlocking)
						throws NotEnoughKiException {
					entered++;
					attacked++;
					throw new NotEnoughKiException(5, 0);
				}
			});
			ultimateAttacks2.add(new SuperSaiyan() {
				public void onUse(BattleOpponent attacker,
						BattleOpponent defender, boolean defenderBlocking)
						throws NotEnoughKiException {
					entered++;
					attacked++;
					throw new NotEnoughKiException(5, 0);
				}
			});
			foe.setUltimateAttacks(ultimateAttacks2);

			b = new Battle(me, foe) {

				public void attack(Attack attack) throws NotEnoughKiException {

					entered++;
					if (!(attack instanceof PhysicalAttack)) {// should this be
																// modified to
																// "If super"?
						attacked++;
						throw new NotEnoughKiException(1, 0);
					}
					if (attacked > 0 && attack instanceof PhysicalAttack) {
						attackedPhysical++;
						try {
							super.attack(attack);
						} catch (Exception e) {
							fail("An exception occured while attacking."
									+ e.getMessage());
						}
					}

				}

				public void block() {

					entered++;

				}

			};

			if (b.getAttacker() != foe) {

				b.endTurn();
				if (b.getAttacker() != foe)
					fail("End turn should end the turn and switch the players.");

			}

			foe.setKi(1);
			hpBefore = me.getHealthPoints();

			try {

				while (attacked == 0) {
					b.play();
					if (entered == 0)
						fail("Play method should choose and perform an action randomly.");
					if (entered > 1000)
						fail("One of the random actions of the play method should be to perform a super attack.");
				}

			}catch (Exception e) {

				e.printStackTrace();
				if(e instanceof NotEnoughKiException)
					fail("Play method should handle NotEnoughKiException thrown when attacking without the required ki.");
				fail("An exception occured."
						+ e.getMessage());

			}
			
			assertTrue(
					"When the foe is trying to perform an attack without having the required ki, he/she should perform a physical attack instead.",
					attackedPhysical > 0);
			assertEquals(
					"When me attacks the foe that is not blocking with physical attack , it should reduce points from his health points according to the game rules",
					hpBefore - 50 - foe.getPhysicalDamage(),
					me.getHealthPoints());

			entered = 0;
			attacked = 0;
			attackedPhysical = 0;
		}

	}

	public static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

}
