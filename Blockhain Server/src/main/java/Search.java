import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Search {

	private String result;
	private String validation;
	
	public String getValidation() {
		return validation;
	}

	public String getResult() {
		return result;
	}

	public Search(int pos,String Content) throws IOException {
		String PreHash;
		File hf = new File(pos + ".txt");
		File pf = new File(Integer.toString(pos-1) + ".txt");
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
	
	public Search(String hash, int queryID) throws NumberFormatException, IOException {
	
		String response = "Not Valid";
		String term = GiveTerm(queryID);
		File f = new File("Users/",hash + ".txt");
		if(f.exists() && !f.isDirectory()) { 
			BufferedReader br = new BufferedReader(new FileReader(f));
			int count = Integer.parseInt(br.readLine());
			int maxcol = Integer.parseInt(br.readLine());
			String[][] Arr = new String[count][maxcol];
			for (int i=0;i<count;i++) {
				int col = Integer.parseInt(br.readLine());
				Arr[i][0] = Integer.toString(col);
				for(int z=1;z<maxcol;z++) {
					Arr[i][z] = br.readLine();
					if(Arr[i][z].matches(term)) {
						response = "Valid";
					}
				}
			}
			br.close();
			this.validation = response;
		}else {
            this.result = "Wrong Data";
		} 

		
		
		
	}
	
	
	
	private String GiveTerm(int ID) {

		String term = "";
		if(ID == 1) {
			term = "Car";
		}
		
		return term;
		
	}
	
	
	
	
	
	
}
