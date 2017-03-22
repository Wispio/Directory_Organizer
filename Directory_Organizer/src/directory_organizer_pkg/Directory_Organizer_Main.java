//==============================================================
//
// Title:      Directory_Organizer_Main.java
// Author:     Alexander Nakhleh
// Date:       17 March 2017
// Description:
//   Program gets a list of all files in a directory.
//	 It then moves all of them to folders based on what type 
//	 of file they are (image, video, etc.)
//
//==============================================================
package directory_organizer_pkg;


import java.util.Scanner;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Directory_Organizer_Main{
	//Target Directory names. 
	//Files will be organized to folders with these names.
	static private final String FOLDER_DIR = "1-Folders";
	static private final String IMAGE_DIR = "2-Images";
	static private final String OFFICE_DIR = "3-MS_OfficeFiles";
	static private final String VIDEO_DIR = "4-Videos";
	static private final String MUSIC_DIR = "5-Music";
	static private final String TEXT_CODE = "6-Text/Code";
	static private final String URL_DIR = "7-Links/URL";
	static private final String PACKAGE_DIR = "8-Packages";
	static private final String UNKNOWN_FILEFORMAT = "???";
	
	//Return codes for what is returned by function "getFileType"
	static private final String FOLDER_TYPE = "_folder";
	static private final String IMAGE_TYPE = "_image";
	static private final String OFFICEDOC_TYPE ="_officeDoc";
	static private final String VIDEO_TYPE = "_video";
	static private final String MUSIC_TYPE = "_music";
	static private final String TEXT_CODE_TYPE = "_textCode";
	static private final String URL_TYPE = "_url";
	static private final String PACKAGE_TYPE = "_pkg";
	static private final String UNKNOWN_TYPE = "_unknown";
	
	//File paths of the directory to clean, and the folder where to place the sorted files
	static private String SOURCE_DIR = "/Users/Olesh/Desktop";
	static private String OUTER_DIR = SOURCE_DIR + File.separator + "Cleaned_Files";
	
	
	//Returns the type of file passed to it based on its extension
	static private final String getFileType(File file){
		
		if(file.isDirectory())
			return FOLDER_TYPE;
		
		//Get the file extension
		String fileName = file.toString();
		String extension = fileName.substring
					(fileName.lastIndexOf('.')+1).toLowerCase();
											
		//Return a code based on what the extension was
		switch (extension){
		case "png": case "jpg": case "jpeg": case "bmp": case "gif": 
		case "tiff": case "psd": case "psb": case "ai" : case "cdr":
			return IMAGE_TYPE;
			
		case "doc": case "docx": case "odt": case "ppt": case "pptx":
		case "xls": case "xlsx": case "xltm": case "docx#": case "pdf":
			return OFFICEDOC_TYPE;
			
		case "m4v": case "mp4": case "m4p": case "mpg": case "mpeg":
		case "webm": case "mkv": case "flv": case "mov": case "avi":
		case "swf":
			return VIDEO_TYPE;
			
		case "mp3": case "m4a": case "aiff": case "wav": case "acc":
		case "ogg": case "oga":
			return MUSIC_TYPE;
			
		case "txt": case "cpp": case "java": case "jar": case "h": 
		case "hs": case "rtf":
			return TEXT_CODE_TYPE;
			
		case "zip": case "7z": case "rar":
			return FOLDER_TYPE;
			
		case "webloc": case "htm": case "html":
			return URL_TYPE;
			
		case "dmg":
			return PACKAGE_TYPE;
			
		default:
			return UNKNOWN_TYPE;
		}
		
	}
	
	//TODO: Will write a list of found files,
	//And their transfers to the sorted target folder
	static private void writeLog(String [] args){
		
	}
	
	//TODO: Checks to see what files can be moved
	//For example, .DS_STORE and .localized on the macOS desktop should not be moved
	static private boolean checkIfSortable(){
		//_FOLDERS
		//_IMAGES
		return true;
	}
	
	

	public static void main(String [] args){
		
		//Create an arrayList with a list of files in the SOURCE_DIR
		File source = new File(SOURCE_DIR);
		ArrayList<File> files = new ArrayList<File>(Arrays.asList(source.listFiles()));
		
		//If the directory contains no files, end program
		//Otherwise, list all the files in SOURCE_DIR
		if(files == null){
			System.out.println("No files found in " + SOURCE_DIR + ".");
			System.exit(0);
		}
		else{
			//Print out all files
			for(Iterator<File> it = files.iterator(); it.hasNext(); ){
				File current = (File) it.next();
				System.out.println(current + " | " + getFileType(current));
			}
		}
		
		//Create the target directory where to put the files
		File outerDir = new File(OUTER_DIR);
		
		if(outerDir.mkdir())
			System.out.println("fileGroupFolder created");
		else
			System.out.println("Failed to create fileGroupFolder");
		
		
		//TEMPORARY TEST OF CODE
		//This returns the directory where the file cleaner JAR is
		//What is returned should be stored in SOURCE_DIR
		try {
			System.out.println(new File(Directory_Organizer_Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}


