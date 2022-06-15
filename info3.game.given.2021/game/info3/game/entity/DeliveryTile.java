package info3.game.entity;

import info3.game.graphics.Graphics;
import info3.game.graphics.Sprite;
import info3.game.position.AutCategory;
import info3.game.position.AutDirection;
import info3.game.position.Direction;
import info3.game.scene.Scene;

public class DeliveryTile extends KitchenTile {

	// IList<Item> assiette;
	// Recipe recette;

	public DeliveryTile(Scene parent, int gridX, int gridY, Direction d) {
		super(parent, gridX, gridY, null, d);
		// TODO Auto-generated constructor stub
	}

	boolean wizz(Direction direction) {
		// TODO selon l'automate c'est graphique
		return true;
	}

	boolean pop(Direction direction) {
		// TODO selon l'automate c'est graphique
		return true;
	}

	@Override
	public void render(Graphics g) {
		// BufferedImage img = m_images[m_imageIndex];
		g.drawSprite(Sprite.DELIVERY_TILE, 0, 0);
	}

	boolean recetteReady() {
		// TODO Check si il y a les items nécessaires pour la recette
		// Cette méthode sera appelée à chaque fois qu'un item est déposé
		return true;
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

	/*
	 * TODO void addItem(Item item) { // TODO }
	 * 
	 * TODO Item removeItem() { // TODO }
	 */

}
