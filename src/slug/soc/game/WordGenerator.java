package slug.soc.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class WordGenerator {

	private static WordGenerator instance = null;

	private File nounsFile = new File("nouns.txt");
	private File adjectivesFile = new File("verbs.txt");
	
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
	
	public String getRandomAdjective(){
		String word = null;
		int n = countLines(adjectivesFile);
		try {
			scanner = new Scanner(adjectivesFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < RandomProvider.getInstance().nextInt(n) + 1; i++){
			if(scanner.hasNext()){
				word = scanner.nextLine();
			}
		}
		scanner.close();
		return word;
	}
}
