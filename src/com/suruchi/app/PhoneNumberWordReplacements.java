package com.suruchi.app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneNumberWordReplacements {
	// Phone number to letters mapping
	private static final Map<Character, String> PHONE_LETTERS = new HashMap<>();
	static {
		PHONE_LETTERS.put('2', "ABC");
		PHONE_LETTERS.put('3', "DEF");
		PHONE_LETTERS.put('4', "GHI");
		PHONE_LETTERS.put('5', "JKL");
		PHONE_LETTERS.put('6', "MNO");
		PHONE_LETTERS.put('7', "PQRS");
		PHONE_LETTERS.put('8', "TUV");
		PHONE_LETTERS.put('9', "WXYZ");
	}

	private static List<String> loadDictionary(String filePath) throws IOException {
		List<String> dictionary = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				dictionary.add(line.trim().toUpperCase());
			}
		}
		return dictionary;
	}

	private static void findWordReplacements(String phoneNumber, List<String> dictionary, List<String> result, StringBuilder currentWord, int index) {
		if (index == phoneNumber.length()) {
			if(dictionary.contains(currentWord.toString()))
				result.add(currentWord.toString());
			return;
		}

		char digit = phoneNumber.charAt(index);
		String letters = PHONE_LETTERS.getOrDefault(digit, String.valueOf(digit));

		for (char letter : letters.toCharArray()) {
			currentWord.append(letter);
			System.out.println(currentWord);
			findWordReplacements(phoneNumber, dictionary, result, currentWord, index + 1);
			System.out.println("delete this "+currentWord);
			currentWord.deleteCharAt(currentWord.length() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		// Default dictionary file
		String dictionaryFile = "resources/dictionary.txt";
		List<String> dictionary = loadDictionary(dictionaryFile);

		// Parse command-line arguments
		List<String> phoneNumbers = new ArrayList<>();
		if (args.length > 0) {
			for (String arg : args) {
				phoneNumbers.add(arg.trim());
			}
		} else {
			String numberfile = "resources/phone.txt";
			try (BufferedReader reader = new BufferedReader(new FileReader(numberfile))) {
				String line;
				while ((line = reader.readLine()) != null) {
					phoneNumbers.add(line.trim());               
				}
			}
			// to be included while adding the solution to spring 
			// code should read either from command line or system input by user
			//			try (Scanner scanner = new Scanner(System.in)) {
			//				while (scanner.hasNextLine()) {
			//					phoneNumbers.add(scanner.nextLine().trim());
			//				}
			//			}
		}

		for (String phoneNumber : phoneNumbers) {
			// Remove punctuation and whitespace from the phone number
			phoneNumber = phoneNumber.replaceAll("[^\\w.]", "").toUpperCase();

			// Find all word replacements for the phone number
			List<String> wordReplacements = new ArrayList<>();
			
			String[] phoneSplit = phoneNumber.split("\\.");
			
			for (String phoneVal : phoneSplit) {
				findWordReplacements(phoneVal, dictionary, wordReplacements, new StringBuilder(), 0);
			}
			
			

			if (!wordReplacements.isEmpty()) {
				System.out.println(String.join("-", wordReplacements));
			} else {
				System.out.println("No word replacements found for " + phoneNumber + ".");
			}
		}
	}

//	// Phone number to letters mapping
//	private static final Map<Character, String> PHONE_LETTERS = new HashMap<>();
//	static {
//		PHONE_LETTERS.put('2', "ABC");
//		PHONE_LETTERS.put('3', "DEF");
//		PHONE_LETTERS.put('4', "GHI");
//		PHONE_LETTERS.put('5', "JKL");
//		PHONE_LETTERS.put('6', "MNO");
//		PHONE_LETTERS.put('7', "PQRS");
//		PHONE_LETTERS.put('8', "TUV");
//		PHONE_LETTERS.put('9', "WXYZ");
//	}
//
//	private static List<String> loadDictionary(String filePath) throws IOException {
//		List<String> dictionary = new ArrayList<>();
//		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				dictionary.add(line.trim().toUpperCase());
//			}
//		}
//		return dictionary;
//	}
//
//	private static void findWordReplacements(String phoneNumber, List<String> dictionary, List<String> result, StringBuilder currentWord, int index) {
//		if (index == phoneNumber.length()) {
//			if(dictionary.contains(currentWord.toString()))
//				result.add(currentWord.toString());
//			continue;
//		}
//
//		char digit = phoneNumber.charAt(index);
//		String letters = PHONE_LETTERS.getOrDefault(digit, String.valueOf(digit));
//
//		for (char letter : letters.toCharArray()) {
//			currentWord.append(letter);
//			System.out.println(currentWord);
//			findWordReplacements(phoneNumber, dictionary, result, currentWord, index + 1);
//			System.out.println("delete this "+currentWord);
//			currentWord.deleteCharAt(currentWord.length() - 1);
//		}
//	}
//
//	public static void main(String[] args) throws IOException {
//		// Default dictionary file
//		String dictionaryFile = "resources/dictionary.txt";
//		List<String> dictionary = loadDictionary(dictionaryFile);
//
//		// Parse command-line arguments
//		List<String> phoneNumbers = new ArrayList<>();
//		if (args.length > 0) {
//			for (String arg : args) {
//				phoneNumbers.add(arg.trim());
//			}
//		} else {
//			String numberfile = "resources/phone.txt";
//			try (BufferedReader reader = new BufferedReader(new FileReader(numberfile))) {
//				String line;
//				while ((line = reader.readLine()) != null) {
//					phoneNumbers.add(line.trim());               
//				}
//			}
//			// to be included while adding the solution to spring 
//			// code should read either from command line or system input by user
//			//			try (Scanner scanner = new Scanner(System.in)) {
//			//				while (scanner.hasNextLine()) {
//			//					phoneNumbers.add(scanner.nextLine().trim());
//			//				}
//			//			}
//		}
//
//		for (String phoneNumber : phoneNumbers) {
//			// Remove punctuation and whitespace from the phone number
//			phoneNumber = phoneNumber.replaceAll("[^\\w.]", "").toUpperCase();
//
//			// Find all word replacements for the phone number
//			List<String> wordReplacements = new ArrayList<>();
//			
//			String[] phoneSplit = phoneNumber.split("\\.");
//			
//			for (String phoneVal : phoneSplit) {
//				findWordReplacements(phoneVal, dictionary, wordReplacements, new StringBuilder(), 0);
//			}
//			
//			
//
//			if (!wordReplacements.isEmpty()) {
//				System.out.println(String.join("-", wordReplacements));
//			} else {
//				System.out.println("No word replacements found for " + phoneNumber + ".");
//			}
//		}
//	}
}
