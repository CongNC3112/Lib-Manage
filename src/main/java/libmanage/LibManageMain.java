package libmanage;

import java.util.List;

import libmanage.entity.Author;
import libmanage.entity.Paper;
import libmanage.service.GetDataService;
import libmanage.service.QueryDataService;

public class LibManageMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Author> authors = GetDataService.getAuthorsData();
		List<Paper> papers = GetDataService.getPapersData();

		// Print out all details of all books and magazines
		QueryDataService.showAll(papers);
		System.out.println("\n\n" + "__________" + "\n\n");
		// Find and print out the details of a book or magazine by searching with an ISBN
		QueryDataService.findByISBN(papers, "dummy");
		System.out.println("\n\n" + "__________" + "\n\n");
		QueryDataService.findByISBN(papers, "3214-5698-7412");
		System.out.println("\n\n" + "__________" + "\n\n");
		// Find and print out the details of a book or magazine for an author
		QueryDataService.findAllPapersOfAuthor(papers, authors.get(1));
		System.out.println("\n\n" + "__________" + "\n\n");
		Author tempAuthor = new Author("email", "firstname", "lastname");
		QueryDataService.findAllPapersOfAuthor(papers, tempAuthor);
		System.out.println("\n\n" + "__________" + "\n\n");
		// Sort all books and magazine by title and print out the result
		QueryDataService.sortByTitle(papers);
	}

}
