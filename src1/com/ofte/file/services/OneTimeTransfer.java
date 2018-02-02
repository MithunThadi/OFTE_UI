package com.ofte.file.services;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Map;

public class OneTimeTransfer {

	public void transfer(Map<String, String> metaDataMap) {
		LinkedList<String> processFilesList = null;
		try {
			System.out.println("enterde in one time transfer");
			TriggerPatternValidator triggerPatternValidator = new TriggerPatternValidator();
			LinkedList<String> filesList = new LinkedList<String>();
			String[] filesInDirectory;
			File file = new File(metaDataMap.get("sourceDirectory"));
			filesInDirectory = file.list();
			System.out.println(filesInDirectory.length);
			for (int i = 0; i < filesInDirectory.length; i++) {
				System.out.println((!filesInDirectory[i].equalsIgnoreCase(""))
						+ " "
						+ (triggerPatternValidator.validateTriggerPattern(
								metaDataMap.get("triggerPattern"),
								filesInDirectory[i]) + " "
								+ filesInDirectory[i]));
				// if loop to check the triggerPattern condition before adding
				// filesList
				if ((!filesInDirectory[i].equalsIgnoreCase(""))
						&& (triggerPatternValidator.validateTriggerPattern(
								metaDataMap.get("triggerPattern"),
								filesInDirectory[i]))) {
					// Adding filesInDirectory to filesList
					filesList.add(filesInDirectory[i]);

				}
			}

			// Creating an object for ProcessFiles class
			ProcessFiles processFiles = new ProcessFiles();
			// Invoking processFiles class to process the files in
			// processFileList

			processFilesList = processFiles.processFileList(filesList,
					metaDataMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// clear the processFilesList
		processFilesList.clear();
		System.out.println("files transfered sucessfully");
	}

}
