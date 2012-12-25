package slug.soc.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class WordGenerator {

	private static WordGenerator instance = null;

	private File nounsFile = new File("nouns.txt");
	private File verbsFile = new File("verbs.txt");
	
	private Scanner scanner;
	
	protected WordGenerator(){
		
	}
	
	public static WordGenerator getInstance(){
		if(instance == null){
			instance = new WordGenerator();
		}
		return instance;
	}
	
	private int countLines(File file){
		int n = 0;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(scanner.hasNext()){
			n++;
			scanner.next();
		}
		scanner.close();
		return n;
	}
	
	private String getRandomWordFromFile(File file){
		String word = null;
		int n = countLines(file);
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int q = RandomProvider.getInstance().nextInt(n) + 1;
		for(int i = 0; i < q ; i++){
			if(scanner.hasNext()){
				word = scanner.nextLine();
			}
		}
		scanner.close();
		return word;
	}
	
	public String getRandomAdjective(){
		return getRandomWordFromFile(verbsFile);
	}
	
	public String getRandomNoun(){
		return getRandomWordFromFile(nounsFile);
	}
}
