package yty.xxl;

import java.util.Random;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class TuPian extends ImageIcon {
	private TuPian() {
	}

	public static ImageIcon getcolor() {
		int color = new Random().nextInt(6) + 1;
		switch (color) {
		case 1:
			return new ImageIcon("bird.png");
			
		case 2:
			return new ImageIcon("panda.png");
			
		case 3:
			return new ImageIcon("fish.png");
			
		case 4:
			return new ImageIcon("tiger.png");
			
		case 5:
			return new ImageIcon("dragon.png");
		case 6:
			return new ImageIcon("lion.png");
			
		};
		return new ImageIcon("0.png");
	}
	public static ImageIcon getcolor(String s) {
		switch (s) {
		case "bird.png":
			return new ImageIcon("bird.png");
			
		case "panda.png":
			return new ImageIcon("panda.png");
			
		case "fish.png":
			return new ImageIcon("fish.png");
			
		case "tiger.png":
			return new ImageIcon("tiger.png");
			
		case "dragon.png":
			return new ImageIcon("dragon.png");
		case "lion.png":
			return new ImageIcon("lion.png");
			
		};
		return new ImageIcon("0.png");
	}
}
