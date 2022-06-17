package info3.game.tests;

import java.io.File;

import info3.game.graphics.AwtFont;

public class FontTest {
	public static void main(String[] args) {
		AwtFont font = new AwtFont(6, new File("resources/font7.png"), 10);

		assert font.measureText("A") == 4;
		assert font.measureText("Al") == font.measureText("A") + 1 + font.measureText("l");

		System.out.println("OK !");
	}
}
