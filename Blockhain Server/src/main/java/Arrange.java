
public class Arrange {
	
	private Object[] Obj;
	
	public Arrange(String Key,String Paramiters) {
		System.out.println("ww");
	}
	
	public Arrange(Object[] oldString,String Key, String Paramiters) {
		Object[] temp = oldString;
		temp[SelectPos(Key)] = Paramiters;
		this.Obj = temp;
	}

	public Object[] getObj() {
		return Obj;
	}
	
	private int SelectPos(String key) {
		int response = 15;
		if(key.matches("fname")) {
			response = 0;
		}else if (key.matches("lname")) {
			response = 1;
		}else if (key.matches("height")) {
			response = 2;
		}else if (key.matches("race")) {
			response = 3;
		}else if (key.matches("eyec")) {
			response = 4;
		}else if (key.matches("fathersfn")) {
			response = 5;
		}else if (key.matches("mothersfn")) {
			response = 6;
		}else if (key.matches("datebirth")) {
			response = 7;
		}else if (key.matches("apolitiriongrade")) {
			response = 8;
		}else if (key.matches("bc")) {
			response = 9;
		}else if (key.matches("bcgrade")) {
			response = 10;
		}else if (key.matches("foreignlang")) {
			response = 11;
		}else if (key.matches("drivinlg")) {
			response = 12;
		}else if (key.matches("drivinlgdate")) {
			response = 13;
		}else if (key.matches("propertynum")) {
			response = 14;
		}
		return response;
		

	}
	

}
