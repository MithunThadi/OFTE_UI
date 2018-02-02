package com.ofte.file.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Class functionality: The main functionality of this class is based on the
 * trigger condition given by the user command, the files gets triggered and
 * then processed
 * 
 * Methods: public boolean validateTriggerPattern(String triggerPattern, String
 * fileName)
 * 
 *
 */
public class TriggerPatternValidator {

	/**
	 * This method returns the boolean value either true or false based on the
	 * condition that the filename is matched with the given triggerpattern
	 * condition
	 * 
	 * @param triggerPattern
	 * @param fileName
	 * @return validatedResult
	 */
	public boolean validateTriggerPattern(String triggerPattern, String fileName) {
		// Declaring and initialising parameter validatedResult to true
		boolean validatedResult = true;
		// Declaration of parameter fileNameLength
		int fileNameLength = fileName.length();
		// Declaration of parameter index
		int index;
		
		// if loop to check trigger pattern contains * or ?
		if (triggerPattern.contains("*") || triggerPattern.contains("?")) {
			StringBuilder out = new StringBuilder("^");
		    for(int i = 0; i < triggerPattern.length(); ++i) {
		        final char c = triggerPattern.charAt(i);
		        switch(c) {
		            case '*': out.append(".*"); break;
		            case '?': out.append('.'); break;
		            case '.': out.append("\\."); break;
		            case '\\': out.append("\\\\"); break;
		            default: out.append(c);
		        }
		    }
		    System.out.println(fileName.matches(out.toString()));
		    if(!fileName.matches(out.toString())) {
		    	validatedResult = false;
		    }
		}
		 else {
			Pattern pattern = Pattern.compile(triggerPattern);
			// Declaration of parameter matcher and initialising it to null
			Matcher matcher = null;
			// Declaration of parameter length and initialising it to zero
			int length = 0;
			// while loop to check the condition length less than fileNameLength
			while (length < fileNameLength) {
				// Declaration of parameter characterInFileName
				char characterInFileName = fileName.charAt(length);
				// Declaration of parameter character
				String character = Character.toString(characterInFileName);
				matcher = pattern.matcher(character);
				validatedResult = matcher.find();
				// if loop to check validatedResult
				if (validatedResult == false) {
					break;
				}
				// incrementing length
				length++;
			}
		}
		// return statement
		return validatedResult;
	}
}