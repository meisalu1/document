package documents.model.data;

import java.util.HashSet;
import java.util.Set;

public class Catalog {
	
	private int id;
	private int level;
	private String name;
	private String desc;
	private String folder;
	private Set<Catalog> subCats = new HashSet<Catalog>();
	private Set<Document> docs = new HashSet<Document>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getFolder() {
		return folder;
	}
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public Set<Catalog> getSubCats() {
		return subCats;
	}
	public void setSubCats(Set<Catalog> subCats) {
		this.subCats = subCats;
	}
	public Set<Document> getDocs() {
		return docs;
	}
	public void setDocs(Set<Document> docs) {
		this.docs = docs;
	}


}
