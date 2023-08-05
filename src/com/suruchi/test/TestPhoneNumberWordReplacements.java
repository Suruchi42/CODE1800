package com.suruchi.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestPhoneNumberWordReplacements {

	
	public static void main(String[] args) {
		try {
			callApp();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void callApp() throws FileNotFoundException, IOException{
		List<String> phoneNumbers = new ArrayList<>();
		String numberfile = "resources/phone.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(numberfile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				phoneNumbers.add(line.trim());               
			}
		}
	}

}
