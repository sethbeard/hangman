package com.sethbeard.hangman.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class wordlist {

	private HashMap<Integer, String> wordlist;

	public wordlist() {
	
		this.wordlist = new HashMap<Integer, String>();
		try {
			InputStream in = getClass().getResourceAsStream("wordlist.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String st;
			int count = 0;

			while ((st = br.readLine()) != null) {
				wordlist.put(count, st);
				count++;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public HashMap<Integer, String> getWordlist() {
		return wordlist;
	}

	
}