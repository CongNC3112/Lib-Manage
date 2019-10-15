package libmanage.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
	private String title;
	private String isbn;
	private List<String> authors;
	private String description;
	private String publicationdate;
	private boolean isBook;
	
	@Override
	public String toString() {
		String authorStr = authors.size() > 1 ? String.join(",", authors) : authors.get(0);
		if (isBook) {
			return "Book [title=" + title + "; isbn=" + isbn + "; authors=" + authorStr + "; description=" + description + "]";
		} else {
			return "Magazine [title=" + title + "; isbn=" + isbn + "; authors=" + authorStr + "; publicationdate=" + publicationdate + "]";
		}		
	}
	
}
