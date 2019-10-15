package libmanage.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import libmanage.entity.Author;
import libmanage.entity.Paper;

public class QueryDataService {
	private static String ERR_MSG = "There are none book or magazine!!";
	
	public static void showAll(List<Paper> papers) {
		if (papers != null && papers.size() > 0) {
			for (Paper p : papers)
				System.out.println(p.toString());
		} else {
			System.out.println(ERR_MSG);
		}
	}
	
	public static Paper findByISBN(List<Paper> papers, String isbn) {		
		if (papers != null && papers.size() > 0 && isbn != null && !isbn.isEmpty()) {
			Paper paper = papers.stream().filter(p -> p.getIsbn().equals(isbn)).findFirst().orElse(null);;
			return paper;
		}
		return null;
	}
	
	public static List<Paper> findAllPapersOfAuthor(List<Paper> papers, Author author) {
		List<Paper> result = new ArrayList<Paper>();		
		if (papers != null && papers.size() > 0 && author != null && !author.getEmail().isEmpty()) {
			for (Paper paper : papers) {
				List<String> authors = paper.getAuthors();
				for (String tempAuthor : authors) {
					if (tempAuthor.equals(author.getEmail())) {
						result.add(paper);
						break;
					}
				}
			}
		}
		return result;
	}
	
	public static void sortByTitle(List<Paper> papers) {
		if (papers != null && papers.size() > 0) {
			papers.sort(Comparator.comparing(Paper::getTitle));
			showAll(papers);
		} else {
			System.out.println(ERR_MSG);
		}
	}
}
