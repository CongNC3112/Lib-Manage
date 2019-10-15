package libmanage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import libmanage.entity.Author;
import libmanage.entity.Paper;

@RunWith(JUnit4.class)
public class QueryDataServiceTest {
	
	private static Author author;
	private static List<Paper> papers = new ArrayList<Paper>();
	
	@BeforeClass
	public static void beforeClass() {
		author = new Author("email", "firstname", "lastname");
		List<String> listAuthor1 = new ArrayList<String>();
		listAuthor1.add("email");
		listAuthor1.add("email1");
		List<String> listAuthor2 = new ArrayList<String>();
		listAuthor2.add("email2");
		Paper paper1 = new Paper("title1", "isbn1", listAuthor1, "description1", "publicationdate1", true);
		Paper paper2 = new Paper("title2", "isbn2", listAuthor1, "description2", "publicationdate2", false);
		Paper paper3 = new Paper("title3", "isbn3", listAuthor2, "description3", "publicationdate3", false);
		papers.add(paper3);
		papers.add(paper1);
		papers.add(paper2);		
	}
	
	@Test
	public void findByISBN_Null_Empty() {
		Paper paper_null = QueryDataService.findByISBN(null, "isbn");
		assertNull(paper_null);
		
		Paper paper_empty = QueryDataService.findByISBN(new ArrayList<Paper>(), "isbn");
		assertNull(paper_empty);
	}
	
	@Test
	public void findByISBN_NotFound() {
		Paper paper_notfound = QueryDataService.findByISBN(papers, "dummy");
		assertNull(paper_notfound);
	}
	
	@Test
	public void findByISBN_Success() {
		Paper paper_success = QueryDataService.findByISBN(papers, "isbn1");
		assertNotNull(paper_success);
	}
	
	@Test
	public void findAllPapersOfAuthor_Null_Empty() {
		List<Paper> result_null = QueryDataService.findAllPapersOfAuthor(null, author);
		assertTrue(result_null.isEmpty());
		
		List<Paper> result_empty = QueryDataService.findAllPapersOfAuthor(new ArrayList<Paper>(), author);
		assertTrue(result_empty.isEmpty());
	}
	
	@Test
	public void findAllPapersOfAuthor_NotFound() {
		Author dummy_author = new Author("dummy", "dummy", "dummy");
		List<Paper> result_notfound = QueryDataService.findAllPapersOfAuthor(papers, dummy_author);
		assertTrue(result_notfound.isEmpty());
	}
	
	@Test
	public void findAllPapersOfAuthor_Success() {
		List<Paper> result_success = QueryDataService.findAllPapersOfAuthor(papers, author);
		assertEquals(2, result_success.size());
	}
	
	@Test
	public void sortByTitle_Success() {
		QueryDataService.sortByTitle(papers);
		assertEquals("title1", papers.get(0).getTitle());
		assertEquals("title2", papers.get(1).getTitle());
		assertEquals("title3", papers.get(2).getTitle());
	}
}
