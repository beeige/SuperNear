package SuperNear;

import java.util.ArrayList;
import java.util.Date;

public class Direction {
	
	private Main main = (Main)Main.getPlugin(Main.class);
	
	public Direction(Main main) {
		this.main = main;
	}
	
	private String straight = "§e\u21d1 §7Ïðÿìî. \n";
	private String left = "§e\u21d0 §7Íàëåâî.\n";
	private String right = "§e\u21d2 §7Íàïðàâî.\n";
	private String back = "§e\u21d3 §7Íàçàä.\n";
	private String straightLeft = "§e\u21d6 §7Ïðÿìî Íàëåâî.\n";
	private String straightRight = "§e\u21d7 §7Ïðÿìî Íàïðàâî.\n";
	private String backLeft = "§e\u21d9 §7Íàçàä Íàëåâî.\n";
	private String backRight = "§e\u21d8 §7Íàçàä Íàïðàâî.\n";
	
	public String getdirection(int yaw) {
		
		
		switch (yaw) {
		case (0):
			return straight;	
		case(1):
			return straightLeft;
		case(2):
			return left;
		case(3):
			return backLeft;
		case(4):
			return back;
		case(5):
			return backRight;
		case(-1):
			return right;
		case(-2):
			return backRight;
		case(-3):
			return backRight;
		case(-4):
			return back;
		case(-5):
			return left;
		case(6):
			return straightRight;
		case(7):
			return straight;
		case(-6):
			return straightLeft;
		case(-7):
			return straight;
		case(-8):
			return straight;
		case(-9):
			return straightRight;
		case(-10):
			return backRight;
		case(-11):
			return backRight;
		case(-12):
			return back;
		case(-13):
			return backLeft;
		case(-14):
			return straightLeft;
		case(-15):
			return straightLeft;
		
		default:
			ArrayList<String> exception = (ArrayList<String>) main.getConfig().getList("Exception");
			Date data = new Date();
			exception.add(data + " yaw:" + yaw + "");
			main.getConfig().set("Exception", exception);
			main.saveConfig();
		return new String("§cÎøèáêà! Ñîîáùèòå àäìèíèñòðàöèè\n");		
		}	
	}
}
