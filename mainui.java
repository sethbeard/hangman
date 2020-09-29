package com.sethbeard.hangman.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// takes care of most of the console input/output
public class mainui {

	private static Scanner userInput;
	private int wins;
	private int losses;
	private HashMap<Integer, String> listOfWords;
	private wordlist wordList;

	public mainui() throws FileNotFoundException, IOException {
		wordList = new wordlist();
		this.listOfWords = wordList.getWordlist();

	}

	public void mainMenu() throws InterruptedException {
		while (true) {
			clearScreen();
			userInput = new Scanner(System.in);
			System.out.println("WELCOME TO HANGMAN!\nWins this Session: " + wins + "\nLoses This Session: " + losses
					+ "\n\n*at any time enter* \n1: to Quit");
			startGame();
		}
	}

// Starts new game with new word. 
	public void newGame(String difficulty) throws InterruptedException {
		clearScreen();
		game newGame = new game(listOfWords, difficulty);
		listOfWords.remove(newGame.getKey());
		String guessedLetters = "";

		while (newGame.getWrongGuesses() < 6) {
			clearScreen();
			printState(newGame.getWrongGuesses());
			System.out.println("\n\n\n");
			System.out.println(newGame.getGuess());
			System.out.println("\n\nLetters Guessed: " + guessedLetters +
			"\nEnter Guess: ");
			String newGuess = userInput.nextLine().toLowerCase();
			if (newGuess.equals(null) || !newGuess.equals("")) {
				char guess = newGuess.charAt(0);
				if (guessedLetters.contains(guess + " ")) {
					System.out.println("Letter " + guess + " has already been guessed.");
					TimeUnit.SECONDS.sleep(1);
					continue;
				}

				guessedLetters += guess + " ";

				if (newGame.userGuess(guess)) {
					quit();
				}

			}

			else
				continue;

			if (win(newGame.getAnswer(), newGame.getGuess())) {
				clearScreen();
				wins++;
				System.out.println("\n\n\n\n\nYOU HAVE WON!!!!!!!!!!\n\n\nWins This Session: " + wins
						+ "\nLoses This Session: " + losses);
				
				startGame();
			}
		}
		clearScreen();
		String str = new String(newGame.getAnswer());
		losses++;
		System.out.println("\n\n\n\n\n**GameOver** :( \n\n\nThe Word Was: " + str + "\n\n\n\nWins this Session: " + wins
				+ "\nLoses This Session: " + losses);
	
		startGame();
	}

//shows progress/amount of tries left
	public void printState(int x) {
		switch (x) {
		case 0:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   \r\n" + " |  \r\n" + " |   \r\n" + "_|_\r\n"
					+ "\n6 tries left\n");

			break;

		case 1:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   0 \r\n" + " |  \r\n" + " |   \r\n" + "_|_\r\n"
					+ "\n5 tries left\n");
			break;
		case 2:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   0 \r\n" + " |  / \r\n" + " |   \r\n" + "_|_\r\n"
					+ "\n4 tries left\n");
			break;
		case 3:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   0 \r\n" + " |  /| \r\n" + " |   \r\n" + "_|_\r\n"
					+ "\n3 tries left\n");
			break;
		case 4:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   0 \r\n" + " |  /|\\\r\n" + " |   \r\n" + "_|_\r\n"
					+ "\n2 tries left\n");
			break;
		case 5:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   0 \r\n" + " |  /|\\\r\n" + " |  /  \r\n" + "_|_\r\n"
					+ "\n1 tries left\n");
			break;
		case 6:
			System.out.println("  ___\r\n" + " |   |\r\n" + " |   0 \r\n" + " |  /|\\\r\n" + " |  / \\ \r\n" + "_|_\r\n"
					+ "\n0 tries left Game Over\n");
			break;

		}
	}

// exits the program or if answer isn't y returns to where they were
	public void quit() {
		System.out.println("\nQuit? y/n");
		String quitting = userInput.nextLine().toLowerCase();
		switch (quitting.toLowerCase()) {
		case "y":
			userInput.close();
			System.out.println("\n\nThank you for playing!");
			System.exit(0);
			;
		default:
			break;
		}
	}

//takes in input for starting new game.  Any answer aside from y will prompt quit. 
	public void startGame() throws InterruptedException {
		System.out.println("\n\nStart a new game? y/n \n");
		String answer = userInput.nextLine();
		switch (answer.toLowerCase()) {
		case "y":
		case "yes":
//selection of difficulty will repeat until a correct answer is given
			while (true) {
				System.out.println("\n\n\n\n\nSelect Difficulty: \n1: Easy \n2: Hard");
				String difficulty = userInput.nextLine();
				if (difficulty.equals("1") || difficulty.equals("2")) {
					newGame(difficulty);
					break;
				}
			}
		default:
			quit();
			break;
		}
	}

	public boolean win(char[] a, char[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}

	public static void clearScreen() {
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

	}

}
