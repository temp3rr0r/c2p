package ua.compilers.FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Static class that reads TTL files and indexes them to the main tripleHashMap dictionary */
public class FileHandler {
	
	/** Locate the TTL file in the "filePath" location and return its value as a String.
	 * Remarks: Reads one line at a time 
	 * @param filePath
	 * @param dict
	 * @return String
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException 
	 */
	
	/** Locate and delete a file using the local file system */
	public static void deleteFile(String fileName) {   	 
		File file = new File(fileName);
		file.delete(); 
	}
	
	/** Check if file exists on disk */
	public static boolean fileExists(String fileName) {
		
		File f = new File(fileName);
		if(f.exists())
			return true;
		else
			return false;
	}
	
	/** Locate the file in the "filePath" location and return its value as a String.
	 * Remarks: Reads one line at a time 
	 * @throws UnsupportedEncodingException */
	public static String readFile(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
	    StringBuilder sb = null;
	    String everything = null;
	    
		if (fileExists(filePath)) {
		    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath),"UTF-8"));
		    try {
		        sb = new StringBuilder();
		        String line = br.readLine();
	
		        while (line != null) {
		            sb.append(line + "\n");
		            line = br.readLine();
		        }
		        everything = sb.toString();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
		        try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	    return everything;
	}
	
	//TODO: maybe add read/write chunk of files methods
	//TODO: maybe store the output path or use system's path variables
	
	/** Write to disc the String "source" as "fileName" in the current executable's path location 
	 * @param source
	 * @param fileName
	 */
	public static void writeFile(String source, String fileName) {
		BufferedWriter writer = null;
	    try {
	    	//create a temporary file
	    	File logFile = new File(fileName);
		
	    	// This will output the full path where the file will be written to...
	    	//System.out.println(logFile.getCanonicalPath());
		
		    writer = new BufferedWriter(new FileWriter(logFile, true));
		    writer.write(source);
		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
		        // Close the writer regardless of what happens...
		        writer.close();
		    } catch (Exception e) {
		    }
		}
	}
}