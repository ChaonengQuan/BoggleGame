/**
 * Create a randomly chosen dice tray;
 * @author Chaoneng Quan
 */

package model;

import java.util.Random;

public class ShuffleDiceTray {

	// instance variables
	public static final int SIZE = 4;
	private char[][] randomDiceTray = new char[4][4];
	private boolean[][] used = new boolean[4][4];
	private Random random = new Random();
	private char[][] Alldices = { { 'L', 'R', 'Y', 'T', 'T', 'E' }, { 'V', 'T', 'H', 'R', 'W', 'E' },
			{ 'E', 'G', 'H', 'W', 'N', 'E' }, { 'S', 'E', 'O', 'T', 'I', 'S' }, { 'A', 'N', 'A', 'E', 'E', 'G' },
			{ 'I', 'D', 'S', 'Y', 'T', 'T' }, { 'O', 'A', 'T', 'T', 'O', 'W' }, { 'M', 'T', 'O', 'I', 'C', 'U' },
			{ 'A', 'F', 'P', 'K', 'F', 'S' }, { 'X', 'L', 'D', 'E', 'R', 'I' }, { 'H', 'C', 'P', 'O', 'A', 'S' },
			{ 'E', 'N', 'S', 'I', 'E', 'U' }, { 'Y', 'L', 'D', 'E', 'V', 'R' }, { 'Z', 'N', 'R', 'N', 'H', 'L' },
			{ 'N', 'M', 'I', 'H', 'U', 'Q' }, { 'O', 'B', 'B', 'A', 'O', 'J' } };

	/**
	 * Constructor
	 */
	public ShuffleDiceTray() {
		createDiceTray();
	}

	/**
	 * Create a randomly choosed dice tray
	 */
	private void createDiceTray() {
		// populate the used 2d array, which represents the used status of each dice
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				used[i][j] = false;
			}
		}
		// randomly choose dice
		for (char[] dice : Alldices) {
			int x = random.nextInt(4);
			int y = random.nextInt(4);
			while (used[x][y]) {
				x = random.nextInt(4);
				y = random.nextInt(4);
			}
			randomDiceTray[x][y] = dice[random.nextInt(6)];
			used[x][y] = true;
		}
	}

	/**
	 * get the 2d array of dice tray
	 * 
	 * @return 2d array
	 */
	public char[][] getRandomDiceTrat() {
		return randomDiceTray;
	}

	/**
	 * Return a string representation of the 2d array
	 */
	public String toString() {
		String strDiceTray = "\n";
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (randomDiceTray[r][c] == 'Q') {
					strDiceTray += " " + "Qu";
					continue;
				}
				strDiceTray += " " + randomDiceTray[r][c];
			}
			strDiceTray += "\n";
		}
		return strDiceTray;
	}

//	/**
//	 * Main method to print out the 2d array
//	 */
//	public static void main(String[] args) {
//		ShuffleDiceTray sdt = new ShuffleDiceTray();
//		System.out.println(sdt.toString());
//	}

}
