package com.sethbeard.hangman.main;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class game {
	private String word;
	private int wrongGuesses;
	private char[] answer;
	private char[] guess;
	private int key;
	
//constructor for a new game  takes in the word list and the difficulty
	public game(HashMap<Integer, String> wordlist, String difficulty) {
		//sets initial answer for game
	    this.answer=getGameWord(wordlist);
		
	    
	    //checks difficulty.  Easy difficulty is a word less than 5 letters Hard is 5 or greater. 
	    //picks new word until it fits difficulty
		if (difficulty.equals("1")) {
			while(answer.length>5) {
		
		 this.answer=getGameWord(wordlist);
		}
		}
		else {
			while(answer.length<=5) {
		
		 this.answer=getGameWord(wordlist);
		
			}
		}
		//Sets "lives" 
		this.wrongGuesses = 0;
		this.guess = new char[answer.length];
		
		//sets the "guess" variable used to print - in each spot
		for (int i = 0; i < guess.length; i++) {
			guess[i] = '-';
		}

	}

//checks the users guess vs answer returns true if one was entered to quit. 
	public boolean userGuess(char a) throws InterruptedException {

		switch (a) {
		
		
		case '1':
			return true;
		
		default:
			boolean correct = false;
			System.out.println("\nLetter Guessed - " + a);
			for (int i = 0; i < answer.length; i++) {
				if (a == answer[i]) {
					guess[i] = a;
					correct = true;
				}
			}
			if (!correct) {
				wrongGuesses++;
				System.out.println("Incorrect");
				TimeUnit.SECONDS.sleep(1);
			}
			else {
				System.out.println("Correct!");
			}
			return false;
		}
	}

	public String getWord() {
		return word;
	}
	

	public int getWrongGuesses() {
		return wrongGuesses;
	}


	public char[] getAnswer() {
		return answer;
	}

	public char[] getGuess() {
		return guess;
	}


	public int getKey() {
		return key;
	}

private char[] getGameWord(HashMap<Integer,String> wordlist) {

	Random random = new Random();
	int rand = 0;
	while (true) {
		rand = random.nextInt(wordlist.size());
		if (rand > 0)
			break;
	}
	
	return wordlist.get(rand).toString().toCharArray();
	
}
	
	
	

}
