import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Search {

	private String result;
	
	public String getResult() {
		return result;
	}

	public Search(int pos,String Content) throws IOException {
		String PreHash;
		File hf = new File(pos + ".txt");
		File pf = new File(Integer.toString(pos-1) + ".txt");
		System.out.println(1);

		if(hf.exists() && !hf.isDirectory()) { 
			BufferedReader br = new BufferedReader(new FileReader(pf));
			PreHash = br.readLine();
			br.close();
			Block hash = new Block(PreHash,Content);
			br = new BufferedReader(new FileReader(hf));
			if(br.readLine().matches(hash.getBlockHash())) {
				this.result = "Valid";
			}else {
				this.result = "Not Valid";
			}
			br.close();
		}else {
            this.result = "Wrong Data";
		} 
		
	}
	
	
}
