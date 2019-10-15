package libmanage.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import libmanage.entity.Author;
import libmanage.entity.Paper;

@RunWith(JUnit4.class)
public class GetDataServiceTest {
	
	@Test
	public void parseCSVFile_NotFound() {
		List<String[]> result_notfound = GetDataService.parseCSVFile("dummy/path");
		assertTrue(result_notfound.isEmpty());
	}
	
	@Test 
	public void getAuthorsData_Success() {
		List<Author> result = GetDataService.getAuthorsData();
		assertEquals(6, result.size());
	}
	
	@Test 
	public void getBooksData_Success() {
		List<Paper> result = GetDataService.getBooksData();
		assertEquals(8, result.size());
	}
	
	@Test 
	public void getMagazinesData_Success() {
		List<Paper> result = GetDataService.getMagazinesData();
		assertEquals(6, result.size());
	}
	
}
