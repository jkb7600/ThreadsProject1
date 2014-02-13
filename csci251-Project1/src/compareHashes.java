import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class compareHashes implements Runnable{
	private File file;
	private String hash;
	private String pass;
	
	public compareHashes(File file, String hash, String pass){
		this.file = file;
		this.hash = hash;
		this.pass = pass;
	}
	
	private void compare(){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			
			while((line = br.readLine()) != null){
				String[] split = line.split(" ");
				// username
				split[0] = split[0].trim();
				// hexPass
				split[1] = split[1].trim();
				
				// if hex's are =
				if(hash.equals(split[1])){
					print(split[0]);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void print(String username){
		System.out.println(username + " "+ pass);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		compare();
		
	}

}
