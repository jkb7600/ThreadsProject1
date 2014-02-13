import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class PasswordCrack {
	

	public static void main(String[] args) throws Throwable{
		if(args.length != 2){
			// notify user of in-correct input
			System.err.println("Please provide a dictionary file along with a database.");
			return;
		}
			// args 0 is dicitonary file
			// plain text file containing one or more lines
			// each line is a potential password
			File dictionaryFile = new File(args[0]);
			
			// args 1 is the database of the password hash db file
			// plain text file containing one or more lines
			// each line contains a user name and a password hash (64 char hex string)
			// the name and hash fields are separated by one or more whitespace chars.
			
			File databaseFile = new File(args[1]);
			LinkedList<Thread> dictionaryThreads = new LinkedList<Thread>();
			LinkedList<Thread> databaseThreads = new LinkedList<Thread>();
			
			// get lines of dictionary file
			BufferedReader br = new BufferedReader(new FileReader(dictionaryFile));
			String line;
			while((line = br.readLine()) != null){
				String[] words = line.split(" ");
				hashPassword temp = new hashPassword(words[0]);
				Thread dictionaryThread = new Thread(temp);
				dictionaryThreads.add(dictionaryThread);
				Thread databaseThread = new Thread(new compareHashes(databaseFile,temp.hash,words[0]));
				databaseThreads.add(databaseThread);
			}
			br.close();
			
			// start database threads
			for(Thread t : databaseThreads){
				t.start();
			}
			
			for(Thread t: dictionaryThreads){
				t.start();
			}
			
			for(int i = 0; i < dictionaryThreads.size(); i ++){
				dictionaryThreads.get(i).join();
				databaseThreads.get(i).join();
			}
			
			
			// Create threads to compute the hash of a diff pass
			
			// Create threads to match a single user from
			// the password hash database against all the dic
			// pass hash's
		
	}// end main

}// end class
