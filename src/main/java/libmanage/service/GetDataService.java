package libmanage.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import libmanage.entity.Author;
import libmanage.entity.Paper;

public class GetDataService {
	private static final String PATH_AUTHORS_FILE = "/data/authors.csv";
	private static final String PATH_BOOKS_FILE = "/data/books.csv";
	private static final String PATH_MAGAZINES_FILE = "/data/magazines.csv";
	private static final int COL_NUM_AUTHORS_FILE = 3;
	private static final int COL_NUM_BOOKS_FILE = 4;
	private static final int COL_NUM_MAGAZINES_FILE = 4;
	public static List<Author> authorsList = new ArrayList<Author>();
	public static List<Paper> papersList = new ArrayList<Paper>();
	
	public static List<String[]> parseCSVFile(String filePath) {
		List<String[]> data = new ArrayList<String[]>();
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader csvReader = new BufferedReader(fileReader);
			String row = csvReader.readLine();
			while ((row = csvReader.readLine()) != null && !row.isEmpty()) {
			    String[] columns = row.split(";");
			    data.add(columns);
			}
			csvReader.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
		
	public static List<Author> getAuthorsData() {		
		List<String[]> data = parseCSVFile(PATH_AUTHORS_FILE);		
		for (String[] strArray : data) {
			if (strArray.length == COL_NUM_AUTHORS_FILE) {
				Author authorObj = new Author();
				authorObj.setEmail(strArray[0]);
				authorObj.setFirstname(strArray[1]);
				authorObj.setLastname(strArray[2]);
				authorsList.add(authorObj);
			}				
		}
		return authorsList;
	}
	
	private static List<String> splitAuthorsArray(String authorArray) {
		List<String> authorsList = new ArrayList<String>();
		String comman = ",";
		if (authorArray.indexOf(comman) != -1) {
			String[] authors = authorArray.split(comman);
			authorsList = Arrays.asList(authors);
		} else {
			authorsList.add(authorArray);
		}
		return authorsList;
	}
	
	
	public static List<Paper> getBooksData() {
		List<Paper> tempList = new ArrayList<Paper>();
		List<String[]> data = parseCSVFile(PATH_BOOKS_FILE);
		for (String[] strArray : data) {
			if (strArray.length == COL_NUM_BOOKS_FILE) {
				Paper paperObj = new Paper();
				paperObj.setTitle(strArray[0]);
				paperObj.setIsbn(strArray[1]);
				paperObj.setAuthors(splitAuthorsArray(strArray[2]));
				paperObj.setDescription(strArray[3]);
				paperObj.setBook(true);
				tempList.add(paperObj);
			}
		}
		return tempList;
	}
	
	public static List<Paper> getMagazinesData() {
		List<Paper> tempList = new ArrayList<Paper>();
		List<String[]> data = parseCSVFile(PATH_MAGAZINES_FILE);
		for (String[] strArray : data) {
			if (strArray.length == COL_NUM_MAGAZINES_FILE) {
				Paper paperObj = new Paper();
				paperObj.setTitle(strArray[0]);
				paperObj.setIsbn(strArray[1]);
				paperObj.setAuthors(splitAuthorsArray(strArray[2]));
				paperObj.setPublicationdate(strArray[3]);
				paperObj.setBook(false);
				tempList.add(paperObj);
			}
		}
		return tempList;
	}
	
	public static List<Paper> getPapersData() {
		List<Paper> books = getBooksData();
		List<Paper> magazines = getMagazinesData();
		papersList.addAll(books);
		papersList.addAll(magazines);
		return papersList;
	}
}
