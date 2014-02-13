import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class hashPassword implements Runnable{
	
	protected String hash;
	private String password;
	
	public hashPassword(String password){
		this.password = password;
	}
	
	public void hashPass() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] data = password.getBytes("UTF-8");
		for(int i = 0; i < 100000; i ++){
			md.update(data);
			data = md.digest();
		}
		// Set the hash
		hash = new String(data);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			hashPass();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
