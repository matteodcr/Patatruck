package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.Scene;

public class CutTile extends KitchenTile {

	public CutTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean pop(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wizz(AutDirection direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void render(Graphics g) {
		// BufferedImage img = m_images[m_imageIndex];
		g.drawSprite(Sprite.CUT_TILE, 0, 0);
	}

}
