package com.mayuran19.jaxbexample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateXML {
	public static void main(String[] args) throws Exception {
		ValidateXML validateXML = new ValidateXML();
		validateXML.validate();
	}

	public void validate() throws Exception {
		Pattern pattern = Pattern.compile("\n+|\t+|\r+");
		File fileDir = new File(
				"/home/mayuran/Dropbox/workspace/java/JaxbExample/src/main/resources/xml/sample.xml");
		BufferedReader in = null;
		BufferedReader backUpIn = null;
		char[] buffer = new char[20];
		char[] readonlyBuffer = new char[20];
		char[] cacheBuffer = new char[20];
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileDir), "UTF8"));
			backUpIn = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileDir), "UTF8"));
			int chunkCount = 0;
			while (in.read(readonlyBuffer) != -1) {
				chunkCount = chunkCount + 1;
				boolean isError = false;
				String str = new String(buffer);
				do {
					isError = false;
					try {
						Matcher matcher = pattern.matcher(str);
						while (matcher.find()) {

							int position = matcher.start();
							int leftNonSpecialPos = position - 1;
							int rightNonSpecialPos = position + 1;
							char leftNonSpcialChar = buffer[leftNonSpecialPos];
							char rightNonSpcialChar = buffer[rightNonSpecialPos];
							while (true) {
								if (leftNonSpcialChar == '\n'
										|| leftNonSpcialChar == '\r'
										|| leftNonSpcialChar == '\t') {
									leftNonSpecialPos = leftNonSpecialPos - 1;
									leftNonSpcialChar = buffer[leftNonSpecialPos];
								} else {
									break;
								}
							}
							while (true) {
								if (rightNonSpcialChar == '\n'
										|| rightNonSpcialChar == '\r'
										|| rightNonSpcialChar == '\t') {
									rightNonSpecialPos = rightNonSpecialPos + 1;
									rightNonSpcialChar = buffer[rightNonSpecialPos];
								} else {
									break;
								}
							}
							if (leftNonSpcialChar == '>'
									&& rightNonSpcialChar == '<') {
								continue;
							} else {
								System.out.println("invalid");
							}
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						isError = true;
						if (chunkCount - 1 > 0) {
							char[] newarray = new char[buffer.length * 3];
							System.arraycopy(cacheBuffer, 0, newarray, 0,
									cacheBuffer.length);
							System.arraycopy(buffer, 0, newarray,
									cacheBuffer.length - 1, buffer.length);
							char[] nextReadArray = new char[buffer.length];
							in.read(nextReadArray);
							System.arraycopy(nextReadArray, 0, newarray,
									cacheBuffer.length + buffer.length - 1,
									nextReadArray.length);
							str = new String(newarray);
							buffer = newarray;
						} else {
							char[] newarray = new char[buffer.length * 2];
							System.arraycopy(buffer, 0, newarray, 0,
									buffer.length);
							char[] nextReadArray = new char[buffer.length];
							in.read(nextReadArray);
							System.arraycopy(nextReadArray, 0, newarray,
									buffer.length, buffer.length);
							str = new String(newarray);
							buffer = newarray;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} while (isError);
				cacheBuffer = Arrays.copyOf(buffer, buffer.length);
			}
		} finally {
			in.close();
			backUpIn.close();
		}
	}
}
