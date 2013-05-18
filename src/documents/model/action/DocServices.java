package documents.model.action;

import documents.form.SearchForm;
import documents.model.data.Document;

public interface DocServices {
	
	public Document[] search(SearchForm form);

}
