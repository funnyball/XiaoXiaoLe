package yty.xxl;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class App {
	public static XXL x;
	public App(){
		x = new XXL();
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(MyLookAndFeel.JTATTOO_FAST);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		x = new XXL();
	}
}
