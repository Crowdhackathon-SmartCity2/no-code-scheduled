import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		try {
			@SuppressWarnings("unused")
			Server testt = new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
