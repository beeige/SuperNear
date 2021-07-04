package SuperNear;

import java.util.ArrayList;
import java.util.Date;

public class Direction {
	
	private Main main = (Main)Main.getPlugin(Main.class);
	
	public Direction(Main main) {
		this.main = main;
	}
	
	private String straight = "§d\u21d1 §7Прямо. \n";
	private String left = "§d\u21d0 §7Налево.\n";
	private String right = "§d\u21d2 §7Направо.\n";
	private String back = "§d\u21d3 §7Назад.\n";
	private String straightLeft = "§d\u21d6 §7Прямо Налево.\n";
	private String straightRight = "§d\u21d7 §7Прямо Направо.\n";
	private String backLeft = "§d\u21d9 §7Назад Налево.\n";
	private String backRight = "§d\u21d8 §7Назад Направо.\n";
	
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
		return new String("§cОшибка! Сообщите администрации\n");		
		}	
	}
}
