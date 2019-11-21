/**
 * a console based program that allows one user to play one game of Boggle with a fixed 2D array of char. 
 * @author Chaoneng Quan
 */

package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javafx.scene.control.TextArea;

public class Boggle {
	// instance variables
	public List<String> wordList = new ArrayList<String>();
	public Set<String> found = new TreeSet<String>();
	public Set<String> notFound = new TreeSet<String>();
	public Set<String> restWords = new TreeSet<String>();
	public Set<String> dictionary = new HashSet<String>();
	public int totalScores;
	public DiceTray tray;

	/**
	 * Constructor for static use
	 */
	public Boggle() {
	}

	/**
	 * Constructor for dynamic use
	 */
	public Boggle(TextArea ta, DiceTray dt) {
		this.tray = dt;
		dictionaryToSet();
		scanInput(ta);
		checkUserInput(dt);
	}

	/**
	 * Print out all the text information needed for the user to play the game
	 * 
	 * @param dt the DiceTray
	 */
	public void giveUserPrompt(DiceTray dt) {
		System.out.println("Play one game of Boggle: ");

		for (int r = 0; r < DiceTray.SIZE; r++) {
			for (int c = 0; c < DiceTray.SIZE; c++) {
				System.out.print(" " + dt.getBoard()[r][c]);
			}
			System.out.println();
		}

		System.out.println("Enter words or ZZ to quit:" + "\n");
	}

	/**
	 * Continue to take user input words into a list of word, until user enter "ZZ"
	 */
	public void scanUserInput() {
		Scanner keyboard = new Scanner(System.in);
		while (keyboard.hasNext()) {
			String word = keyboard.next().toLowerCase();
			if (word.equals("zz"))
				break;
			wordList.add(word);
		}
		keyboard.close();
	}

	/**
	 * Scan user input from the javafx text area
	 * 
	 * @param ta The text area to scan text
	 */
	public void scanInput(TextArea ta) {
		Scanner keyboard = new Scanner(ta.getText());
		while (keyboard.hasNext()) {
			String word = keyboard.next().toLowerCase();
			wordList.add(word);
		}
		keyboard.close();
	}

	/**
	 * check every word in the wordList, if found put in the found list, else put in
	 * the notFound list
	 * 
	 * @param dt the DiceTray
	 */
	public void checkUserInput(DiceTray dt) {
		// check user inputs
		for (String str : wordList) {
			if (!dictionary.contains(str)) {
				notFound.add(str);
				continue;
			}
			if (dt.found(str)) {
				found.add(str);
			} else {
				notFound.add(str);
			}
		}

		// calculate scores
		for (String word : found) {
			int wordLength = word.length();
			if (wordLength == 3)
				totalScores += 1;
			if (wordLength == 4)
				totalScores += 1;
			if (wordLength == 5)
				totalScores += 2;
			if (wordLength == 6)
				totalScores += 3;
			if (wordLength == 7)
				totalScores += 4;
			if (wordLength > 7)
				totalScores += 11;
		}

		// find rest words
		for (String word : dictionary) {
			if (dt.found(word))
				restWords.add(word);
			if (found.contains(word))
				restWords.remove(word);
		}
	}

	/**
	 * print out the results
	 */
	public void printUserResult() {
		// print out scores
		System.out.println("\n" + "Score: " + totalScores);
		// print words you found
		System.out.println("\n" + "Words you found: ");
		for (String word : found) {
			System.out.print(word + " ");
		}
		// print out incorrect words
		System.out.println("\n" + "\n" + "Incorrect words:");
		for (String word : notFound) {
			System.out.print(word + " ");
		}
		// print out words you could find
		System.out.println("\n" + "\n" + "You could have found these " + restWords.size() + " more words: ");
		int wordEachLine = 15;
		int wordCount = 0;
		for (String word : restWords) {
			if (wordCount % wordEachLine == 0 && wordCount != 0)
				System.out.println();
			System.out.print(word + " ");
			wordCount++;
		}
		System.out.println();
	}

	/**
	 * return a large string with all outputs
	 * 
	 * @return A large string with all outputs same as printUserResult()
	 */
	public String output() {
		StringBuilder output = new StringBuilder();
		// print out scores
		output.append("\n" + "Score: " + totalScores + "\n");
		// print words you found
		output.append("\n" + "Words you found: " + "\n");
		for (String word : found) {
			output.append(word + " ");
		}
		// print out incorrect words
		output.append("\n" + "\n" + "Incorrect words:" + "\n");
		for (String word : notFound) {
			output.append(word + " ");
		}
		// print out words you could find
		output.append("\n" + "\n" + "You could have found these " + restWords.size() + " more words: " + "\n");
		int wordEachLine = 15;
		int wordCount = 0;
		for (String word : restWords) {
			if (wordCount % wordEachLine == 0 && wordCount != 0)
				output.append("\n");
			output.append(word + " ");
			wordCount++;
		}
		output.append("\n");

		return output.toString();
	}

	/**
	 * Store the BoggleWords.txt into a Set dictionary
	 */
	public void dictionaryToSet() {
		try {
			Scanner scan = new Scanner(new File("BoggleWords.txt"));
			while (scan.hasNextLine()) {
				String word = scan.nextLine();
				dictionary.add(word);
			}
			scan.close();
		} catch (FileNotFoundException e) {
		}

	}

//	/**
//	 * Main method to play the boggle game
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		Boggle bg = new Boggle();
//
//		char[][] fixed = { { 'A', 'B', 'S', 'E' }, { 'I', 'M', 'T', 'N' }, { 'N', 'D', 'E', 'D' },
//				{ 'S', 'S', 'E', 'N' }, };
//		bg.tray = new DiceTray(fixed);
//
//		bg.dictionaryToSet();
//		bg.giveUserPrompt(bg.tray);
//		bg.scanUserInput();
//		bg.checkUserInput(bg.tray);
//		bg.printUserResult();
//		//System.out.println(bg.output());
//	}

}