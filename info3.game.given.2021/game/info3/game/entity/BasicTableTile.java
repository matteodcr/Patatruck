package info3.game.entity;

import info3.game.content.Assembly;
import info3.game.content.Item;
import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.scene.Scene;

public class BasicTableTile extends KitchenTile {
	Assembly assembly;

	public BasicTableTile(Scene parent, int gridX, int gridY, AutDirection d) {
		super(parent, gridX, gridY, null, d);
		this.assembly = new Assembly();
		automaton = parentScene.setupAutomaton("Table");
		current_state = automaton.initial;
	}

	@Override
	public boolean pop(AutDirection direction) {// poser
		Item item_player = ((KitchenScene) this.parentScene).getCook().item;
		if (item_player == null) {
			return false;
		} else {
			this.assembly.addItem(item_player);
			item_player = null;
			return true;
		}

	}

	@Override
	public boolean wizz(AutDirection direction) {// rendre au player
		Item item_player = ((KitchenScene) this.parentScene).getCook().item;
		if (item_player != null) {
			return false;
		} else {
			if (this.assembly.getItems().size() == 1) {
				item_player = this.assembly.getItems().get(0);
				this.assembly.emptyAssembly();
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		switch (m_direction) {
		case E:
			g.drawSprite(Sprite.BASICTABLE_E, 0, 0);
			break;
		case N:
			g.drawSprite(Sprite.BASICTABLE_N, 0, 0);
			break;
		case S:
			g.drawSprite(Sprite.BASICTABLE_S, 0, 0);
			break;
		case W:
			g.drawSprite(Sprite.BASICTABLE_W, 0, 0);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean gwait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean jump(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean explode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean protect(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean turn(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gthrow(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean myDir(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean closest(AutCategory category, AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

}
