package documents.model.data;

import java.sql.Timestamp;

public class Status {
	
	private int id;
	private Timestamp begin;
	private Timestamp end;
	private String name;
	private User created_by;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Timestamp getBegin() {
		return begin;
	}
	
	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}
	
	public Timestamp getEnd() {
		return end;
	}
	
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public User getCreated_by() {
		return created_by;
	}
	
	public void setCreated_by(User created_by) {
		this.created_by = created_by;
	}

}
